package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.user;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.interceptor.SessionAware;
import org.eclipse.persistence.sessions.DatabaseRecord;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.CFMovie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Friend;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.MovieUserRating;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.UserComment;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.UserCommentRating;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.CFMovies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Friends;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.MovieUserRatings;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Movies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.UserCommentRatings;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.UserComments;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.interceptors.SecurityInterceptor;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.CineBookUtils;

import com.opensymphony.xwork2.Preparable;

public class UserActions extends BaseAction implements SessionAware, Preparable {

	private static final long serialVersionUID = -6015571339222836373L;
	private User user;
	private User friend;
	private Integer friendID;
	private String commentText;
	private Map<String, Object> session;
	private Collection<UserComment> comments;
	private Collection<User> friends;
	private String commentsOrderByDate;
	private Integer commentID;
	private Integer commentRating;
	private Integer movieID;
	private Integer movieRating;

	public String viewFriends() throws Exception {
		friends = Friends.getFriends(user.getId());
		return SUCCESS;
	}

	public String friendPage() throws Exception {
		if (friendID == null) {
			return ERROR;
		}
		Friend f = Friends.getFriendship(user.getId(), friendID);
		if (f != null) {
			friend = f.getUser1().getId().equals(user.getId()) ? f.getUser2() : f.getUser1();
			comments = UserComments.getCommentsPostedToUser(friendID,
					commentsOrderByDate);
			return SUCCESS;
		}
		addActionError("You are not friends with the indicated user.");
		return ERROR;
	}

	public String postCommentToUserPage() throws Exception {
		if (friendID == null || CineBookUtils.isEmptyString(commentText)) {
			return INPUT;
		}
		Friend friend = Friends.getFriendship(user.getId(), friendID);
		if (friend != null) {
			UserComments.save(
					commentText,
					user,
					friend.getUser1().getId().equals(user.getId()) ? friend.getUser2() : friend
							.getUser1(), new Date());
			return SUCCESS;
		}
		return ERROR;
	}

	public String rateComment() throws Exception {
		if (commentID != null && commentRating != null) {
			UserComment uc = UserComments.getCommentByID(commentID);
			if (uc != null) {
				UserCommentRating ucr = UserCommentRatings.getUserRatingsForComment(user.getId(), commentID);
				if (ucr != null) {
					ucr.setRating(commentRating);
				} else {
					ucr = new UserCommentRating();
					ucr.setRating(commentRating);
					ucr.setUser(user);
					ucr.setUserComment(uc);
				}
				UserCommentRatings.save(ucr);
			} else {
				addActionError("You have tried to rate an unexistent comment!");
				return ERROR;
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String rateMovie() throws Exception {
		if(movieID != null && movieRating != null) {
			Movie movie = Movies.getMovieById(movieID);
			if(movie != null) {
				MovieUserRating mr = MovieUserRatings.getUserRatingForMovie(user.getId(), movieID);
				if(mr != null) {
					mr.setRating(movieRating);
				} else {
					mr = new MovieUserRating();
					mr.setRating(movieRating);
					mr.setUser(user);
					mr.setMovie(movie);
				}
				MovieUserRatings.save(mr);
				updateMovieRatingTable(user.getId(), movieID);
			} else {
				addActionError("You have tried to rate an unexistent movie");
				return ERROR;
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	private void updateMovieRatingTable(Integer userID, Integer movieID) throws Exception {
		Vector<DatabaseRecord> results = MovieUserRatings.getUserRatingPairs(userID, movieID);
		for(DatabaseRecord db : results) {
			Integer otherMovieID = (Integer) db.get("movie_id");
			Integer ratingDifference = ((Long)db.get("ratingDifference")).intValue();
			CFMovie cfm = CFMovies.getCFMovieEntry(movieID, otherMovieID);
			if(cfm != null) {
				cfm.setCount(cfm.getCount() + 1);
				cfm.setSum(cfm.getSum() + ratingDifference);
				CFMovies.save(cfm);
				if(movieID != otherMovieID) {
					cfm = CFMovies.getCFMovieEntry(otherMovieID, movieID);
					cfm.setCount(cfm.getCount() + 1);
					cfm.setSum(cfm.getSum() - ratingDifference);
					CFMovies.save(cfm);
				}
			} else {
				cfm = new CFMovie();
				cfm.setMovie1(Movies.getMovieById(movieID));
				cfm.setMovie2(Movies.getMovieById(otherMovieID));
				cfm.setCount(1);
				cfm.setSum(ratingDifference);
				CFMovies.save(cfm);
				if(movieID != otherMovieID) {
					cfm = new CFMovie();
					cfm.setMovie1(Movies.getMovieById(otherMovieID));
					cfm.setMovie2(Movies.getMovieById(movieID));
					cfm.setCount(1);
					cfm.setSum(-ratingDifference);
					CFMovies.save(cfm);
				}
			}
		}
	}

	@Override
	public void prepare() throws Exception {
		if (user == null) {
			user = (User) session.get(SecurityInterceptor.USER_OBJECT);
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setFriendID(String friendID) {
		this.friendID = new Integer(friendID);
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Collection<User> getFriends() {
		return friends;
	}

	public User getFriend() {
		return friend;
	}

	public Collection<UserComment> getComments() {
		return comments;
	}

	public String getCommentsOrderByDate() {
		return commentsOrderByDate;
	}

	public void setCommentsOrderByDate(String commentsOrderByDate) {
		this.commentsOrderByDate = commentsOrderByDate;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public void setCommentRating(Integer commentRating) {
		this.commentRating = commentRating;
	}

	public void setMovieID(Integer movieID) {
		this.movieID = movieID;
	}

	public Integer getMovieID() {
		return movieID;
	}

	public void setMovieRating(Integer movieRating) {
		this.movieRating = movieRating;
	}

	public Integer getFriendID() {
		return friendID;
	}

	public void setFriendID(Integer friendID) {
		this.friendID = friendID;
	}
}

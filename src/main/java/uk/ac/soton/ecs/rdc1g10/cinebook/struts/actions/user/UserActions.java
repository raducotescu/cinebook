package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.user;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Friend;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.UserComment;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Friends;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.UserComments;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.interceptors.SecurityInterceptor;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.StringUtils;

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
	
	public String viewFriends() throws Exception {
		friends = Friends.getFriends(user.getId());
		return SUCCESS;
	}
	
	public String friendPage() throws Exception {
		if(friendID == null) {
			return ERROR;
		}
		Friend f = Friends.getFriendship(user.getId(), friendID);
		if(f != null) {
			friend = f.getUser1().equals(user) ? f.getUser2() : f.getUser1();
			comments = UserComments.getCommentsPostedToUser(friendID, commentsOrderByDate);
			return SUCCESS;
		}
		addActionError("You are not friends with the indicated user.");
		return ERROR;
	}

	public String postCommentToUserPage() throws Exception {
		if(friendID == null || StringUtils.isEmpty(commentText)) {
			return INPUT;
		}
		Friend friend = Friends.getFriendship(user.getId(), friendID);
		if (friend != null) {
			UserComments.save(
					commentText,
					user,
					friend.getUser1().equals(user) ? friend.getUser2() : friend
							.getUser1(), new Date());
			return SUCCESS;
		}
		return ERROR;
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

	public Integer getFriendID() {
		return friendID;
	}
}

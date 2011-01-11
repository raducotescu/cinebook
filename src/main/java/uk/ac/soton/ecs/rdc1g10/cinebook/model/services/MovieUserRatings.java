package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import java.util.Vector;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.MovieUserRating;

public class MovieUserRatings {
	public static MovieUserRating getUserRatingForMovie(Integer userID, Integer movieID) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("user").get("id").equal(userID).and(b.get("movie").get("id").equal(movieID));
		return (MovieUserRating) Database.readObject(MovieUserRating.class,	e);
	}

	public static void save(MovieUserRating mr) throws Exception {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		if(mr.getId() == null) {
			uow.registerObject(mr);
		} else {
			MovieUserRating ucTmp = getMovieUserRatingById(mr.getId());
			MovieUserRating clone = (MovieUserRating) uow.registerObject(ucTmp);
			clone.setRating(mr.getRating());
		}
		uow.commit();
	}

	public static MovieUserRating getMovieUserRatingById(Integer id) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("id").equal(id);
		return (MovieUserRating) Database.readObject(MovieUserRating.class, e);
	}
	
	public static Vector<DatabaseRecord> getUserRatingPairs(Integer userID, Integer movieID) {
		DataReadQuery dq = new DataReadQuery();
		dq.setSQLString("SELECT DISTINCT r1.movie_id , (r2.rating - r1.rating) as ratingDifference FROM MovieUserRating r1, MovieUserRating r2 WHERE r1.user_id=#userID AND r2.movie_id=#movieID AND r2.user_id=#userID;");
		dq.addArgument("userID");
		dq.addArgument("movieID");
		Vector<Integer> values = new Vector<Integer>();
		values.add(userID);
		values.add(movieID);
		return Database.read(dq, values);
	}
}

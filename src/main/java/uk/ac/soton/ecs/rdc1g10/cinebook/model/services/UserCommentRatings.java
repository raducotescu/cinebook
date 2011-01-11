package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.UserCommentRating;

public class UserCommentRatings {
	public static UserCommentRating getUserRatingsForComment(Integer userID, Integer commentID) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("user").get("id").equal(userID).and(b.get("userComment").get("id").equal(commentID));
		return (UserCommentRating) Database.readObject(UserCommentRating.class, e);
	}

	public static void save(UserCommentRating ucr) throws Exception {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		if(ucr.getId() == null) {
			uow.registerObject(ucr);
		} else {
			UserCommentRating ucTmp = getUserCommentRatingById(ucr.getId());
			UserCommentRating clone = (UserCommentRating) uow.registerObject(ucTmp);
			clone.setRating(ucr.getRating());
		}
		uow.commit();	
	}

	private static UserCommentRating getUserCommentRatingById(Integer id) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("id").equal(id);
		return (UserCommentRating) Database.readObject(UserCommentRating.class, e);
	}
}

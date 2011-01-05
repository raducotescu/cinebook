package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import java.util.Collection;
import java.util.Date;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.UserComment;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.StringUtils;

public class UserComments {
	public static void save(String commentText, User postedBy, User postedTo, Date datePosted) {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		UserComment uc = new UserComment();
		uc.setCommentText(commentText);
		uc.setPostedby(postedBy);
		uc.setPostedto(postedTo);
		uc.setDatePosted(datePosted);
		uc.setRating(0);
		uow.registerObject(uc);
		uow.commit();
	}

	@SuppressWarnings("unchecked")
	public static Collection<UserComment> getCommentsPostedToUser(int userID, String orderByDate)
			throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("postedto").get("id").equal(userID);
		ReadAllQuery rq = new ReadAllQuery(UserComment.class, e);
		if(StringUtils.isEmpty(orderByDate) || orderByDate.equals("asc")) {
			rq.addAscendingOrdering("datePosted");
		} else {
			rq.addDescendingOrdering("datePosted");
		}
		return (Collection<UserComment>) Database.read(rq);
	}
}

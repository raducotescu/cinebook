package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.UserComment;


public class UserComments {
	public static void save(String commentText, User postedBy, User postedTo) {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		UserComment uc = new UserComment();
		uc.setCommentText(commentText);
		uc.setPostedby(postedBy);
		uc.setPostedto(postedTo);
		uc.setRating(0);
		uow.registerObject(uc);
		uow.commit();
	}
}

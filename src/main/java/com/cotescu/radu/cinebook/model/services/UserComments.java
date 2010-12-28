package com.cotescu.radu.cinebook.model.services;

import org.eclipse.persistence.sessions.UnitOfWork;

import com.cotescu.radu.cinebook.model.EclipseLinkSession;
import com.cotescu.radu.cinebook.model.backend.User;
import com.cotescu.radu.cinebook.model.backend.UserComment;

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

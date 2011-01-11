package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import java.util.Collection;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Booking;

public class Bookings {
	@SuppressWarnings("unchecked")
	public static Collection<Booking> getBookingsForUser(Integer userID) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("user").get("id").equal(userID);
		return (Collection<Booking>)Database.read(Booking.class, e);
	}
	
	public static void save(Booking b) {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		uow.registerObject(b);
		uow.commit();
	}
}

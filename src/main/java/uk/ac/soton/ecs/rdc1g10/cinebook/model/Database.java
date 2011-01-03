package uk.ac.soton.ecs.rdc1g10.cinebook.model;

import java.util.Collection;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.Session;

/**
 * This class contains general purpose read methods.
 * @author Radu Cotescu
 *
 */
public class Database {
	private static Session session;
	
	static {
		session = EclipseLinkSession.getEclipseLinkSession();
	}
	
	public static final Object readObject(Class<?> c, Expression e) throws Exception {
		return session.readObject(c, e);
	}
	
	public static final Object readObject(Object o) throws Exception {
		return session.readObject(o);
	}
	
	public static final Collection<?> readAll(Class<?> c) throws Exception {
		return session.readAllObjects(c);
	}
	
	public static final Collection<?> read(Class<?> c, Expression e) throws Exception {
		return session.readAllObjects(c, e);
	}
	
	public static final Collection<?> read(DatabaseQuery q) throws Exception {
		return (Collection<?>) session.executeQuery(q);
	}
}
package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.queries.ReportQuery;
import org.eclipse.persistence.queries.ReportQueryResult;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Schedule;

public class ScheduleEntries {
	@SuppressWarnings("unchecked")
	public static Collection<Movie> getCurrentSchedule() throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("startTime").greaterThanEqual(new Date());
		ReportQuery rq = new ReportQuery(Schedule.class, e);
		rq.addAttribute("movie", b.get("movie"));
		rq.addGrouping(b.get("movie").get("id"));
		Collection<ReportQueryResult> result = (Collection<ReportQueryResult>) Database.read(rq);
		Collection<Movie> m = new ArrayList<Movie>(result.size());
		for(ReportQueryResult r : result) {
			m.add((Movie)r.get("movie"));
		}
		return m;
	}
	
	public static Schedule getScheduleEntryById(Integer id) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("id").equal(id);
		return (Schedule) Database.readObject(Schedule.class, e);
	}
	
	public static Collection<Schedule> getScheduleEntriesForMovie(Integer movieID) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("movie").get("id").equal(movieID);
		return (Collection<Schedule>) Database.read(Schedule.class, e);
	}
	
	public static void save(Schedule s) throws Exception {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		if(s.getId() == null) {
			uow.registerObject(s);
		} else {
			Schedule tmp = getScheduleEntryById(s.getId());
			Schedule clone = (Schedule) uow.registerObject(tmp);
			ExpressionBuilder b = new ExpressionBuilder();
			Expression e = b.get("id").equal(s.getMovie().getId());
			Movie movie = (Movie) uow.readObject(Movie.class, e);
			clone.setMovie(movie);
			clone.setStartTime(s.getStartTime());
			clone.setTheatre(s.getTheatre());
		}
		uow.commit();
	}
}

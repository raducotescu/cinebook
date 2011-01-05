package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import java.util.Collection;
import java.util.Date;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Schedule;

public class ScheduleEntries {
	@SuppressWarnings("unchecked")
	public static Collection<Schedule> getCurrentSchedule() throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("startTime").greaterThanEqual(new Date());
		return (Collection<Schedule>) Database.read(Schedule.class, e);
	}
}

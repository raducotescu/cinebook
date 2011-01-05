package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;

public class Movies {
	public static Movie getMovieById(int id) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("id").equal(id);
		return (Movie)Database.readObject(Movie.class, e);
	}
}

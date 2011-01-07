package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;

public class Movies {
	public static Movie getMovieById(int id) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("id").equal(id);
		return (Movie)Database.readObject(Movie.class, e);
	}
	
	public static Integer save(Movie movie) throws Exception {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		Integer movieID = null;
		if(movie.getId() == null) {
			movie = (Movie) uow.registerObject(movie);
			movieID = movie.getId();
		} else {
			Movie tmpMovie = getMovieById(movie.getId());
			Movie clone = (Movie) uow.registerObject(tmpMovie);
			clone.setCast(movie.getCast());
			clone.setDescription(movie.getDescription());
			clone.setDirector(movie.getDirector());
			clone.setDuration(movie.getDuration());
			clone.setGenre(movie.getGenre());
			clone.setId(movie.getId());
			clone.setPoster(movie.getPoster());
			clone.setRating(movie.getRating());
			clone.setTitle(movie.getTitle());
			clone.setUserRating(movie.getUserRating());
			clone.setYear(movie.getYear());
			movieID = movie.getId();
		}
		return movieID;
	}
}

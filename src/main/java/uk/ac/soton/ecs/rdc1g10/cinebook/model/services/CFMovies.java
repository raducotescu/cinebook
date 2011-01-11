package uk.ac.soton.ecs.rdc1g10.cinebook.model.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.UnitOfWork;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.CFMovie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;

public class CFMovies {
	public static CFMovie getCFMovieEntry(Integer movie1_ID, Integer movie2_ID) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("movie1").get("id").equal(movie1_ID).and(b.get("movie2").get("id").equal(movie2_ID));
		return (CFMovie)Database.readObject(CFMovie.class, e);
	}

	public static void save(CFMovie cfm) throws Exception {
		UnitOfWork uow = EclipseLinkSession.getUnitOfWork();
		CFMovie check = getCFMovieEntry(cfm.getMovie1().getId(), cfm.getMovie2().getId());
		if(check == null) {
			uow.registerObject(cfm);
		} else {
			CFMovie cfmTmp = getCFMovieEntry(cfm.getMovie1().getId(), cfm.getMovie2().getId());
			CFMovie clone = (CFMovie) uow.registerObject(cfmTmp);
			clone.setCount(cfm.getCount());
			clone.setSum(cfm.getSum());
		}
		uow.commit();
	}
	
	public static Collection<Movie> getRecommendations(Integer movieID) throws Exception {
		DataReadQuery dq = new DataReadQuery();
		dq.setSQLString("SELECT movie2, (sum/count) AS average FROM CFMovies WHERE count > 2 AND movie1=#movieID AND movie1<>movie2 ORDER BY average DESC LIMIT 3;");
		dq.addArgument("movieID");
		Vector<Integer> values = new Vector<Integer>();
		values.add(movieID);
		Vector<DatabaseRecord> result = Database.read(dq, values);
		Collection<Movie> recommendations = new ArrayList<Movie>(result.size());
		for(DatabaseRecord r: result) {
			recommendations.add(Movies.getMovieById((Integer) r.get("movie2")));
		}
		return recommendations;
	}
}

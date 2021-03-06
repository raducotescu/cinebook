package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.movies;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.CFMovies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Movies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.ScheduleEntries;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class MoviesActions extends BaseAction implements ModelDriven<Movie>,
		SessionAware, Preparable {

	private static final long serialVersionUID = 3088856416865240679L;
	private Movie movie;
	private Integer movieID;
	private Map<String, Object> session;
	private boolean movieInDatabase;
	private Collection<Movie> recommendations;
	private Collection<Movie> scheduledMovies;

	@Override
	public Movie getModel() {
		return movie;
	}

	public String movieDetails() throws Exception {
		if (movieID != null) {
			movie = Movies.getMovieById(movieID);
			if (movie != null) {
				recommendations = CFMovies.getRecommendations(movieID);
				return SUCCESS;
			}
		}
		addActionError("The movie you requested details for does not exist.");
		return ERROR;
	}

	public String currentSchedule() throws Exception {
		scheduledMovies = ScheduleEntries.getCurrentSchedule();
		return SUCCESS;
	}

	public Collection<Movie> getScheduledMovies() {
		return scheduledMovies;
	}

	public Integer getMovieID() {
		return movieID;
	}

	public void setMovieID(Integer movieID) {
		this.movieID = movieID;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void prepare() throws Exception {
		if (movieID == null) {
			movie = new Movie();
		} else {
			movie = Movies.getMovieById(movieID);
		}
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public boolean isMovieInDatabase() {
		return movieInDatabase;
	}

	public Collection<Movie> getRecommendations() {
		return recommendations;
	}
}

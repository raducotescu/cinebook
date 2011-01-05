package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.movies;

import java.util.Collection;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Schedule;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Movies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.ScheduleEntries;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class MoviesActions extends BaseAction implements ModelDriven<Movie>{

	private static final long serialVersionUID = 3088856416865240679L;
	private Collection<Schedule> scheduleEntries;
	private Movie movie;
	private Integer movieID;
	
	@Override
	public Movie getModel() {
		return movie;
	}
	
	public String movieDetails() throws Exception {
		movie = Movies.getMovieById(movieID);
		if(movie != null) {
			return SUCCESS;
		}
		addActionError("The movie you requested details for does not exist.");
		return ERROR;
	}
	
	public String currentSchedule() throws Exception {
		scheduleEntries = ScheduleEntries.getCurrentSchedule();
		return SUCCESS;
	}

	public Collection<Schedule> getScheduleEntries() {
		return scheduleEntries;
	}

	public void setMovieID(Integer movieID) {
		this.movieID = movieID;
	}
}

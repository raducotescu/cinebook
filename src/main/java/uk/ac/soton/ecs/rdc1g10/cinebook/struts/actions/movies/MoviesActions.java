package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.movies;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Schedule;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Movies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.ScheduleEntries;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.annotations.RequiresAuthentication;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.interceptors.SecurityInterceptor;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.CineBookUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class MoviesActions extends BaseAction implements ModelDriven<Movie>, SessionAware, Preparable, ServletContextAware {

	private static final long serialVersionUID = 3088856416865240679L;
	private Collection<Schedule> scheduleEntries;
	private Movie movie;
	private Integer movieID;
	private Map<String, Object> session;
	private File poster;
	private String posterContentType;
	private String posterFileName;
	private ServletContext context;
	
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
	
	@RequiresAuthentication
	public String editMovie() throws Exception {
		if(((User)session.get(SecurityInterceptor.USER_OBJECT)).getRole() < 1) {
			addActionError("Your authentication level denies you this action");
			return ERROR;
		}
		if(movie == null) {
			addActionError("The movie you are trying to edit does not exist");
			return ERROR;
		}
		if(movieID == null) {
			return INPUT;
		}
		processMoviePoster();
		movieID = Movies.save(movie);
		return SUCCESS;
	}

	public Collection<Schedule> getScheduleEntries() {
		return scheduleEntries;
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
		if(movieID == null) {
			movie = new Movie();
		}
		else {
			movie = Movies.getMovieById(movieID);
		}	
	}
	
	private void processMoviePoster() throws NoSuchAlgorithmException, IOException {
		String token = CineBookUtils.getMD5Hash(movieID.toString());
		String uploadPath = context.getRealPath("posters");
		File uploadedFile = new File(uploadPath, token);
		FileUtils.copyFile(poster, uploadedFile);
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public void setPoster(File poster) {
		this.poster = poster;
	}

	public void setPosterContentType(String posterContentType) {
		this.posterContentType = posterContentType;
	}

	public void setPosterFileName(String posterFileName) {
		this.posterFileName = posterFileName;
	}
}

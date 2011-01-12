package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.movies;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.Database;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Schedule;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.Movies;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.ScheduleEntries;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.annotations.RequiresAuthentication;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.interceptors.SecurityInterceptor;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.CineBookFileUtils;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.CineBookUtils;

@RequiresAuthentication
public class AdminMoviesActions extends MoviesActions implements
		ServletContextAware {

	private static final long serialVersionUID = -2576338521077588478L;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private ServletContext context;
	private String startTime;
	private Integer theatre;
	private Collection<Movie> movies;
	private Schedule s;
	private Integer scheduleEntryId;
	
	@SuppressWarnings("unchecked")
	public String editSchedule() throws Exception {
		if (((User) getSession().get(SecurityInterceptor.USER_OBJECT))
				.getRole() < 1) {
			addActionError("Your authentication level denies you this action!");
			return ERROR;
		}
		if(scheduleEntryId != null) {
			s = ScheduleEntries.getScheduleEntryById(scheduleEntryId);
			this.setMovieID(s.getMovie().getId());
			theatre = s.getTheatre();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			startTime = sdf.format(s.getStartTime());
			scheduleEntryId = s.getId();
		}
		movies = (Collection<Movie>) Database.readAll(Movie.class);
		return SUCCESS;
	}
	
	public String saveMovieScheduleEntry() throws Exception {
		Movie movie = Movies.getMovieById(this.getMovieID());
		if(scheduleEntryId != null) {
			s = ScheduleEntries.getScheduleEntryById(scheduleEntryId);
			s.setMovie(movie);
			s.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime));
			s.setTheatre(theatre);
			ScheduleEntries.save(s);
		} else {
			s = new Schedule();
			s.setMovie(movie);
			s.setStartTime(new SimpleDateFormat("yyyy-MM-dd H:m").parse(startTime));
			s.setTheatre(theatre);
			ScheduleEntries.save(s);
		}
		return SUCCESS;
	}

	public String addMovie() throws Exception {
		User usr;
		if((usr = (User) getSession().get(SecurityInterceptor.USER_OBJECT)) != null) {
			if(usr.getRole() > 0) {
				return SUCCESS;
			}
		}
		addActionError("You do not have sufficient privileges for this action");
		return ERROR;
	}
	
	public String editMovie() throws Exception {
		if (((User) getSession().get(SecurityInterceptor.USER_OBJECT))
				.getRole() < 1) {
			addActionError("Your authentication level denies you this action!");
			return ERROR;
		}
		if (getModel() == null || getMovieID() == null) {
			addActionError("You are trying to edit an inexistent movie!");
			return ERROR;
		}
		return SUCCESS;
	}

	public String saveMovie() throws Exception {
		if (((User) getSession().get(SecurityInterceptor.USER_OBJECT))
				.getRole() < 1) {
			addActionError("Your authentication level denies you this action");
			return ERROR;
		}
		if (getModel() == null) {
			addActionError("The movie you are trying to edit does not exist");
			return ERROR;
		}
		if (picture != null) {
			String token = CineBookUtils.getMD5Hash(CineBookFileUtils.getBytesFromFile(picture));
			if (getModel().getPoster() != null|| !token.equals(getModel().getPoster())) {
				processMoviePoster(token);
			}
		}
		this.setMovieID(Movies.save(getModel()));
		getSession().remove("movie");
		return SUCCESS;
	}

	private void processMoviePoster(String token)
			throws NoSuchAlgorithmException, IOException {
		getModel().setPoster(token + "." + CineBookUtils.getExtension(pictureFileName));
		String uploadPath = getContext().getRealPath("images" + File.separator + "posters");
		File uploadedFile = new File(uploadPath, token + "." + CineBookUtils.getExtension(pictureFileName));
		FileUtils.copyFile(picture, uploadedFile);
	}
	
	public ServletContext getContext() {
		return context;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public File getPicture() {
		return picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setTheatre(Integer theatre) {
		this.theatre = theatre;
	}

	public Collection<Movie> getMovies() {
		return movies;
	}

	public void setScheduleEntryId(Integer scheduleEntryId) {
		this.scheduleEntryId = scheduleEntryId;
	}

	public String getStartTime() {
		return startTime;
	}

	public Integer getTheatre() {
		return theatre;
	}

	public Integer getScheduleEntryId() {
		return scheduleEntryId;
	}

}

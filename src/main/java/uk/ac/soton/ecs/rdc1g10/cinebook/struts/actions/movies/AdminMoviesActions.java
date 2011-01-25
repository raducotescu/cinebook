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

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@RequiresAuthentication
@Validations
public class AdminMoviesActions extends MoviesActions implements ServletContextAware {

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
	@Override
	public void prepare() throws Exception {
		super.prepare();
		movies = (Collection<Movie>) Database.readAll(Movie.class);
	}
	
//	public void validate() {
//		if(getModel().getPoster() == null && picture == null) {
//			addFieldError("picture", "Please add a poster file for the movie.");
//		}
//	}
	
	public String editSchedule() throws Exception {
		if (((User) getSession().get(SecurityInterceptor.USER_OBJECT)).getRole() < 1) {
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
		return SUCCESS;
	}
	
	@Validations(
		requiredFields={@RequiredFieldValidator(fieldName="theatre", message="Please enter a theatre.", type=ValidatorType.SIMPLE)}
	)
	public String saveMovieScheduleEntry() throws Exception {
		Movie movie = Movies.getMovieById(this.getMovieID());
		if(scheduleEntryId != null) {
			s = ScheduleEntries.getScheduleEntryById(scheduleEntryId);
		} else {
			s = new Schedule();
		}
		s.setMovie(movie);
		s.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime));
		s.setTheatre(theatre);
		ScheduleEntries.save(s);
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
		if (((User) getSession().get(SecurityInterceptor.USER_OBJECT)).getRole() < 1) {
			addActionError("Your authentication level denies you this action!");
			return ERROR;
		}
		if (getModel() == null || getMovieID() == null) {
			addActionError("You are trying to edit an inexistent movie!");
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Validations( requiredStrings={
			@RequiredStringValidator(fieldName="title", message="Please enter the title.", type=ValidatorType.SIMPLE),
			@RequiredStringValidator(fieldName="cast", message="Please enter the cast.", type=ValidatorType.SIMPLE),
			@RequiredStringValidator(fieldName="director", message="Please enter the director's name.", type=ValidatorType.SIMPLE),
			@RequiredStringValidator(fieldName="genre", message="Please enter a genre.", type=ValidatorType.SIMPLE),
			@RequiredStringValidator(fieldName="rating", message="Please enter a rating.", type=ValidatorType.SIMPLE),
			@RequiredStringValidator(fieldName="description", message="Please enter a description.", type=ValidatorType.SIMPLE)
			},
			requiredFields={
			@RequiredFieldValidator(fieldName="year", message="Please enter a year.", type=ValidatorType.SIMPLE),
			@RequiredFieldValidator(fieldName="duration", message="Please enter a duration.", type=ValidatorType.SIMPLE)
			},
			intRangeFields={
			@IntRangeFieldValidator(fieldName="year", min="1900", max="2012", message="The year has to be between ${min} and ${max}" )
			}
	)
	public String saveMovie() throws Exception {
		if (((User) getSession().get(SecurityInterceptor.USER_OBJECT)).getRole() < 1) {
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

	private void processMoviePoster(String token) throws NoSuchAlgorithmException, IOException {
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

package uk.ac.soton.ecs.rdc1g10.cinebook.model.backend;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  ###  Generated by EclipseLink Project EclipseLink Workbench 2.1.2 - Fri Jan 07 19:07:11 GMT 2011.  ###
 */

public class Movie {

	private String cast;
	private Collection<MovieComment> comments;
	private String description;
	private String director;
	private Integer duration;
	private String genre;
	private Integer id;
	private String poster;
	private String rating;
	private String title;
	private float userRating;
	private Integer year;

public Movie() {
	this.comments = new ArrayList<MovieComment>();
}

public void addToComments(MovieComment aMovieComment) {
	this.comments.add(aMovieComment);
}

public String getCast() {
	return this.cast;
}

protected Collection<MovieComment> getComments() {
	return this.comments;
}

public String getDescription() {
	return this.description;
}

public String getDirector() {
	return this.director;
}

public Integer getDuration() {
	return this.duration;
}

public String getGenre() {
	return this.genre;
}

public Integer getId() {
	return this.id;
}

public String getPoster() {
	return this.poster;
}

public String getRating() {
	return this.rating;
}

public String getTitle() {
	return this.title;
}

public float getUserRating() {
	return this.userRating;
}

public Integer getYear() {
	return this.year;
}

public void removeFromComments(MovieComment aMovieComment) {
	this.comments.remove(aMovieComment);
}

public void setCast(String cast) {
	this.cast = cast;
}

protected void setComments(Collection<MovieComment> comments) {
	this.comments = comments;
}

public void setDescription(String description) {
	this.description = description;
}

public void setDirector(String director) {
	this.director = director;
}

public void setDuration(Integer duration) {
	this.duration = duration;
}

public void setGenre(String genre) {
	this.genre = genre;
}

public void setId(Integer id) {
	this.id = id;
}

public void setPoster(String poster) {
	this.poster = poster;
}

public void setRating(String rating) {
	this.rating = rating;
}

public void setTitle(String title) {
	this.title = title;
}

public void setUserRating(float userRating) {
	this.userRating = userRating;
}

public void setYear(Integer year) {
	this.year = year;
}

}

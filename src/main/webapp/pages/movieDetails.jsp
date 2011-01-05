<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="header.jsp">
	<jsp:param value="Movie Details" name="title" />
</jsp:include>
<div class="movie">
	<div class="movie-title">${model.title}</div>
	<div class="moviePoster"><img src="${model.posterLocation}" /></div>
	<div class="movieCast"><strong>With:</strong> ${model.cast}</div>
	<div class="movieDirector"><strong>Directed by:</strong> ${model.director}</div>
	<div class="movieGenre"><strong>Genre:</strong> ${model.genre}</div>
	<div class="movieRating"><strong>Rating:</strong> ${model.rating}</div>
	<div class="movieYear"><strong>Year:</strong> ${model.year}</div>
	<div class="movieDuration"><strong>Duration:</strong> ${model.duration} minutes</div>
</div>
<jsp:include page="footer.jsp" />
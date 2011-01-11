<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Book tickets" name="title"/>
</jsp:include>
<s:if test="entriesForMovie.size > 0">
	<h2>Book tickets</h2>
	<s:form action="completeBooking" namespace="/user" method="POST">
		<s:select name="scheduleID" list="entriesForMovie" listKey="id" listValue="%{sdf.format(startTime)}" />
		<s:select name="seats" list="#{'1':'1', '2':'2', '3':'3', '4':'4'}" />
		<s:submit />
	</s:form>
	<div class="movieSingle">
	<s:set scope="page" var="movie" value="%{entriesForMovie[0].movie}" />
	<div class="movieTitle">${movie.title}</div>
	<div class="moviePoster"><img src="${pageContext.request.contextPath}/images/posters/${movie.poster}" /></div>
	<div class="movieCast"><strong>With:</strong> ${movie.cast}</div>
	<div class="movieDirector"><strong>Directed by:</strong> ${movie.director}</div>
	<div class="movieGenre"><strong>Genre:</strong> ${movie.genre}</div>
	<div class="movieRating"><strong>Rating:</strong> ${movie.rating}</div>
	<div class="movieYear"><strong>Year:</strong> ${movie.year}</div>
	<div class="movieDuration"><strong>Duration:</strong> ${movie.duration} minutes</div>
	<div class="movieUserRating"><strong>Users' rating:</strong> ${movie.overallRating}</div>
	<div class="movieDescription"><strong>Description:</strong> ${movie.description}</div>
	</div>
</s:if>
<jsp:include page="footer.jsp" />
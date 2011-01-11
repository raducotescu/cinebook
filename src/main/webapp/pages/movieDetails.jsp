<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="header.jsp">
	<jsp:param value="Movie Details" name="title" />
</jsp:include>
<div class="movieSingle">
	<div class="movieTitle">${model.title}</div>
	<div class="moviePoster"><img src="${pageContext.request.contextPath}/images/posters/${model.poster}" /></div>
	<div class="movieCast"><strong>With:</strong> ${model.cast}</div>
	<div class="movieDirector"><strong>Directed by:</strong> ${model.director}</div>
	<div class="movieGenre"><strong>Genre:</strong> ${model.genre}</div>
	<div class="movieRating"><strong>Rating:</strong> ${model.rating}</div>
	<div class="movieYear"><strong>Year:</strong> ${model.year}</div>
	<div class="movieDuration"><strong>Duration:</strong> ${model.duration} minutes</div>
	<div class="movieUserRating"><strong>Users' rating:</strong> ${model.overallRating}</div>
	<div class="movieDescription"><strong>Description:</strong> ${model.description}</div>
	<s:if test="#session.user != null">
		<strong>Your rating:</strong>
		<s:form namespace="/user" action="rateMovie" method="POST">
			<input type="radio" id="movieRating-1" name="movieRating" value="1">1</input>
			<input type="radio" id="movieRating-2" name="movieRating" value="2">2</input>
			<input type="radio" id="movieRating-3" name="movieRating" value="3">3</input>
			<input type="radio" id="movieRating-4" name="movieRating" value="4">4</input>
			<input type="radio" id="movieRating-5" name="movieRating" value="5">5</input>
			<input type="hidden" id="movieID" name="movieID" value="${model.id}" />
			<s:submit />
		</s:form>
		<s:url action="book" namespace="/user" var="book">
			<s:param name="movieID">${model.id}</s:param>
		</s:url>
		<s:a href="%{book}"><strong>Book tickets</strong></s:a>
	</s:if>
	<s:if test="#session.user.role > 0">
		<s:url action="edit" namespace="/movies" var="editMovie">
			<s:param name="movieID">${model.id }</s:param>
		</s:url>
		<s:a href="%{editMovie}">Edit Movie</s:a>
	</s:if>
	<s:if test="recommendations.size > 0">
		<div class="movieRecommendations">
			<h3>You might also be interested of:</h3>
			<ul>
				<s:iterator value="recommendations">
					<s:url action="movieDetails" namespace="/movies" var="movieLink">
						<s:param name="movieID" value="id"/>
					</s:url>
					<li><s:a href="%{movieLink}">${title}</s:a></li>
				</s:iterator>
			</ul>
		</div>
	</s:if>
	<s:if test="movie.comments.size > 0">
		<h3>Users' comments:</h3>
		<s:iterator value="movie.comments">
			<div class="movieUserComment" id="comment-${id}">
				<span class="userDetails">${user.firstName} ${user.lastName} said:</span>
				<span class="commentText">${commentText}</span>
			</div>
		</s:iterator>
	</s:if>
</div>
<jsp:include page="footer.jsp" />

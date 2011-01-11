<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:if test="scheduledMovies.size > 0">
	<div id="moviesList">
	<s:iterator value="scheduledMovies">
		<div class="movie">
		<s:a href="%{movieLink}">
			<div class="movieTitle">
				<s:url action="movieDetails" namespace="/movies" var="movieLink">
					<s:param name="movieID" value="id"/>
				</s:url>
				${title}
			</div>
			<div class="moviePoster">
				<img src="${pageContext.request.contextPath}/images/posters/${poster}" />
			</div>
			<div class="movieButtons"></div>
			</s:a>
		</div>
	</s:iterator>
	</div>
</s:if>
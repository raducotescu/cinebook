<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:if test="scheduleEntries.size > 0">
	<div id="moviesList">
	<s:iterator value="scheduleEntries">
		<div class="movie">
			<div class="movie-title">
				<s:url action="movieDetails" namespace="/movies" var="movieLink">
					<s:param name="movieID" value="movie.id"/>
				</s:url>
				<s:a href="%{movieLink}">${movie.title}</s:a>
			</div>
			<div class="moviePoster">
				<img src="${movie.posterLocation}" />
			</div>
			<div class="movieButtons"></div>
		</div>
	</s:iterator>
	</div>
</s:if>
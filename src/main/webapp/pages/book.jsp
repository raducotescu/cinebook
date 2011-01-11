<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Book tickets" name="title"/>
</jsp:include>
<s:if test="entriesForMovie.size > 0">
	<h2>Book tickets</h2>
	<div class="movieSingle">
	<s:set scope="page" var="movie" value="%{entriesForMovie[0].movie}" />
	<div class="movieTitle"><strong>Movie:</strong> ${movie.title}</div>
	</div>

	<s:form action="completeBooking" namespace="/user" method="POST">
		<s:select name="scheduleID" list="entriesForMovie" listKey="id" listValue="%{sdf.format(startTime)}" />
		<s:select name="seats" list="#{'1':'1', '2':'2', '3':'3', '4':'4'}" />
		<s:submit />
	</s:form>
</s:if>
<jsp:include page="footer.jsp" />

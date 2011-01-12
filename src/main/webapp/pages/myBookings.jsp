<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="My Bookings" name="title"/>
</jsp:include>
<s:if test="bookings.size > 0">
<h2>Bookings</h2>
<s:iterator value="bookings">
	<strong>Date:</strong> <s:date name="scheduleEntry.startTime" format="yyyy-MM-dd HH:mm" />
	<strong>Seats:</strong> ${seats}
	<s:url namespace="/movies" action="movieDetails" var="movieDetails">
		<s:param name="movieID">${scheduleEntry.movie.id}</s:param>
	</s:url>
	<strong>Movie:</strong> <s:a href="%{movieDetails}">${scheduleEntry.movie.title}</s:a>
	<hr />
</s:iterator>
</s:if>
<s:else>
	<h2>You have no current bookings.</h2>
</s:else>
<jsp:include page="footer.jsp" />

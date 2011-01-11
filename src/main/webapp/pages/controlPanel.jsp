<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param name="title" value="Control Panel" />
</jsp:include>
<h2>Administrator Control Panel</h2>
<p><s:a action="add" namespace="/movies">Add Movie</s:a></p>
<p><s:a action="editSchedule" namespace="/movies">Schedule Movie</s:a></p>
<jsp:include page="footer.jsp" />
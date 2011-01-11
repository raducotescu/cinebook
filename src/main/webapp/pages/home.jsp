<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<jsp:include page="header.jsp">
	<jsp:param value="Welcome" name="title"/>
</jsp:include>
<h2>Current Schedule</h2>
<s:url var="currentSchedule" action="currentSchedule" namespace="/movies" />
<sj:div href="%{currentSchedule}" id="currentSchedule"/>
<jsp:include page="footer.jsp" />
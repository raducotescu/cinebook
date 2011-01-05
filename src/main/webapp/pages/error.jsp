<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Error" name="title"/>
</jsp:include>
		<h2>An error has been encountered.</h2>
		<div id="actionError">
			<s:actionerror />
		</div>
<jsp:include page="footer.jsp" />
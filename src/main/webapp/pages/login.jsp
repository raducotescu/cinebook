<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Sign In" name="title"/>
</jsp:include>

<jsp:include page="signInForm.jsp">
	<jsp:param value="Please sign in!" name="headerContent"/>
</jsp:include>

<jsp:include page="footer.jsp" />
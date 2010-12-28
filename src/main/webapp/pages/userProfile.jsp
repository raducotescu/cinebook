<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Profile for" name="title"/>
</jsp:include>

<s:form action="postCommentToUserPage" namespace="/user" method="POST">
	<s:textfield name="friendID" id="friendID" />
	<s:textarea name="commentText" id="commentText" />
	<s:submit /> 
</s:form>

<jsp:include page="footer.jsp" />
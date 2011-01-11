<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Error" name="title"/>
</jsp:include>
		<h2>An error has been encountered.</h2>
		<div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all">
			<p>
			<span style="float: left; margin: 0 7px 50px 0;" class="ui-icon ui-icon-alert"></span> 
			<s:actionerror/>
			</p>
		</div>
<jsp:include page="footer.jsp" />
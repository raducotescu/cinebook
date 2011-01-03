<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="#session.user.firstName" name="title"/>
</jsp:include>
<s:if test="friends!=null">
	<ul>
		<s:iterator value="friends">
			<li>
				${firstName} ${lastName}
			</li>
		</s:iterator>
	</ul>
</s:if>
<jsp:include page="footer.jsp" />
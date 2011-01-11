<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="My Friends" name="title"/>
</jsp:include>
<s:if test="friends!=null">
	<h2>These are your friends:</h2>
	<div class="friendsList">
	<ul>
		<s:iterator value="friends">
			<li>
				<s:url action="friend" var="friendpage" namespace="/user">
					<s:param name="friendID">${id}</s:param>
				</s:url>
				<s:a href="%{friendpage}">${firstName} ${lastName}</s:a>
			</li>
		</s:iterator>
	</ul>
	</div>
</s:if>
<jsp:include page="footer.jsp" />
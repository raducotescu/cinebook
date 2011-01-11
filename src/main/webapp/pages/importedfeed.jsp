<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Movie business birthdays" name="title"/>
</jsp:include>
<h2>People born today</h2>
<s:if test="importedFeedEntries.size > 0">
	${importedFeed.title}
	<ul>
		<s:iterator value="importedFeedEntries">
			<li><a href="${link}" target="_blank">${title}</a> - ${description.value}</li>
		</s:iterator>
	</ul>
</s:if>
<jsp:include page="footer.jsp" />
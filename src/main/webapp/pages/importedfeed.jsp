<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:if test="importedFeedEntries.size > 0">
	${importedFeed.title}
	<ul>
		<s:iterator value="importedFeedEntries">
			<li><a href="${link}">${title}</a> - ${description.value}</li>
		</s:iterator>
	</ul>
</s:if>
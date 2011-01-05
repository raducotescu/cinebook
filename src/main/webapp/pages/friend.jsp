<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="User page" name="title" />
</jsp:include>
<h2>${friend.firstName} ${friend.lastName}'s profile page</h2>
<jsp:include page="usercommentform.jsp" />
<s:if test="comments.size > 0">
	<h3>Comments posted for ${friend.firstName}</h3>
	<ul>
		<s:iterator value="comments">
			<div id="comment-${id}">
			
				<p>${postedby.firstName} ${postedby.lastName}
				said on <s:date name="datePosted" format="MMMM dd, yyyy"/> at
				<s:date name="datePosted" format="H:m"/></p>
				<div id="commentText">
					${commentText}
				</div>
				<p>Rating: ${rating}</p>
			</div>
		</s:iterator>
	</ul>
</s:if>
<jsp:include page="footer.jsp" />
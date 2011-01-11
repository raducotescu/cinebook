<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="header.jsp">
	<jsp:param value="User page" name="title" />
</jsp:include>
<h2>${friend.firstName} ${friend.lastName}'s profile page</h2>
<jsp:include page="usercommentform.jsp" />
<s:if test="comments.size > 0">
	<h3>Comments posted for ${friend.firstName}</h3>
	<ul>
		<hr />
		<s:iterator value="comments">
			<div id="comment-${id}">
				<p>${postedby.firstName} ${postedby.lastName} said on <s:date name="datePosted" format="MMMM dd, yyyy" /> at <s:date name="datePosted" format="H:m" /></p>
				<div id="commentText">${commentText}</div>
				<p>Rating: ${overallRating}</p>
				<s:form action="rateComment" namespace="/user" method="POST">
					<input type="radio" id="commentRating-1" name="commentRating" value="1">1</input>
					<input type="radio" id="commentRating-2" name="commentRating" value="2">2</input>
					<input type="radio" id="commentRating-3" name="commentRating" value="3">3</input>
					<input type="radio" id="commentRating-4" name="commentRating" value="4">4</input>
					<input type="radio" id="commentRating-5" name="commentRating" value="5">5</input>
					<input type="hidden" id="commentID" name="commentID" value="${id}" />
					<input type="hidden" name="friendID" value="${friend.id}" />
					<s:submit />
				</s:form>
			</div>
			<hr />
		</s:iterator>
	</ul>
</s:if>
<jsp:include page="footer.jsp" />

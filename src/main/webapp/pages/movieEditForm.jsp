<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param value="Edit Movie" name="title"/>
</jsp:include>
<h2>Edit Movie</h2>
<s:form id="movieEditForm" action="save" namespace="/movies" method="POST" enctype="multipart/form-data">
	<s:hidden id="movieID" name="movieID" />
	<s:textfield id="title" name="title" label="Title" />
	<s:textfield id="year" name="year" label="Year" />
	<s:textfield id="cast" name="cast" label="Cast"/>
	<s:textfield id="director" name="director" label="Director"/>
	<s:textfield id="genre" name="genre" label="Genre"/>
	<s:textfield id="rating" name="rating" label="Rating"/>
	<s:textfield id="duration" name="duration" label="Duration"/>
	<s:file id="picture" label="Poster" name="picture" />
	<s:if test="model.poster != null">
		<img src="${pageContext.request.contextPath}/images/posters/${poster}" />
	</s:if>
	<s:textfield id="poster" name="poster" cssStyle="display:none" />
	<s:textarea cols="80" rows="5" id="description" name="description" label="Description" />
	<s:submit id="submit" value="Submit" />
</s:form>
<jsp:include page="footer.jsp" />
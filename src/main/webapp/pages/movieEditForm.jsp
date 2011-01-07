<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form id="movieEditForm" action="add" namespace="/movies" method="POST" enctype="multipart/form-data">
	<s:textfield id="title" name="title" label="Title" />
	<s:textfield id="year" name="year" label="Year" />
	<s:textfield id="cast" name="cast" label="Cast"/>
	<s:textfield id="director" name="director" label="Director"/>
	<s:textfield id="genre" name="genre" label="Genre"/>
	<s:textfield id="rating" name="rating" label="Rating"/>
	<s:textfield id="duration" name="duration" label="Duration"/>
	<s:file id="poster" label="Poster" name="poster" />
	<s:textarea id="description" name="description" label="Description" />
	<s:submit id="submit" value="Submit" />
</s:form>
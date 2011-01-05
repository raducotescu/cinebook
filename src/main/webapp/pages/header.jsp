<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${param.title} - CineBook</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<s:url var="appcontext" value="/" />
</head>
<body>
<div id="wrapper">
	<div id="header">
		<s:url var="index" action="index" namespace="/" />
		<s:url var="home" action="home" namespace="/" />
			<div id="logo">
				<s:a href="%{index}"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo"/></s:a>
			</div>
			<div id="userControls">
				<ul>
						<s:if test="#session.user==null">
					    	<s:url var="register" action="new" namespace="/user" />
					<li>
					  		<s:a id="register" href="%{register}" title="Register">Create account</s:a>
					</li>
						</s:if>
						<s:else>
							<s:url var="update" action="profile" namespace="/user" />
					    	<s:url var="signOut" action="signOut" namespace="/" />
					<li>
							<s:a href="%{home}" title="Home">Home</s:a>
					</li>
					<li>
							<s:a href="%{update}" title="Your profile">Hello, ${session.user.firstName}</s:a>
					</li>
					<li>
					    	<s:a href="%{signOut}" title="Sign Out">Sign Out</s:a>
					</li>
						</s:else>
				</ul>
			</div>
	</div>
	<div id="content">
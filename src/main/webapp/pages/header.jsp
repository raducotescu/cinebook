<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${param.title} - CineBook</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/anytime.css" />
<sj:head compressed="true" jqueryui="true" loadFromGoogle="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uar-utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/anytime.js"></script>
<s:url var="appcontext" value="/" />
</head>
<body>
<div id="wrapper">
	<div id="header">
		<s:url var="index" action="index" namespace="/" />
			<div id="logo">
				<s:a href="%{index}"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo"/></s:a>
			</div>
			<div id="userControls">
				<ul>
						<s:if test="#session.user==null">
					    	<s:url var="signIn" action="signIn" namespace="/" />
					<li>
					  		<s:a id="signIn" href="%{signIn}" title="Sign In">Sign In</s:a>
					</li>
					<li>
						<s:a action="importedFeed" namespace="/feeds">Birthdays</s:a>
					</li>
						</s:if>
						<s:else>
					    	<s:url var="signOut" action="signOut" namespace="/" />
					<li>
							<s:url action="myPage" namespace="/user" var="usrpg">
								<s:param name="userID">${session.user.id}</s:param>
							</s:url>
							<s:a href="%{usrpg}">Hello, ${session.user.firstName}</s:a>
					</li>
					<li>
						<s:a action="importedFeed" namespace="/feeds">Birthdays</s:a>
					</li>
					<li>
						<s:a action="friends" namespace="/user">Your friends</s:a>
					</li>
					<li>
						<s:a action="myBookings" namespace="/user">Your bookings</s:a>
					</li>
					<li>
							<s:if test="#session.user.role == 1">
							<s:a action="controlPanel" namespace="/admin" title="Control Panel">Control Panel</s:a>
							</s:if>
					    	<s:a href="%{signOut}" title="Sign Out">Sign Out</s:a>
					</li>
						</s:else>
				</ul>
			</div>
	</div>
	<div id="content">
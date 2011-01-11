<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div id="signInForm">
	<h2>${param.headerContent}</h2>
	<s:if test="hasActionErrors()">
		<div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all">
			<p>
			<span style="float: left; margin: 0 7px 50px 0;" class="ui-icon ui-icon-alert"></span> 
			<s:actionerror/>
			</p>
		</div>
	</s:if>
	<s:form action="signIn" namespace="/" method="POST" cssClass="signIn">
	    <s:textfield  label="Email" id="username" name="username"/>
	    <s:password label="Password" id="password" name="password" />
	    <s:submit value="Sign In"/>
	</s:form>
	<script type="text/javascript">
	$('document').ready(function() {$('input:submit', '#signInForm').button().click(function(e) {if(checkInputs()) {$('#signInForm form').submit();}});
		$('#usernameNeeded').dialog({autoOpen: false, draggable: false, modal: true, resizable: false, height: 120,	close: function(event, ui) { $('#username').focus(); }});
		$('#passwordNeeded').dialog({autoOpen: false, draggable: false,	modal: true, resizable: false, height: 120,	close: function(event, ui) { $('#password').focus(); }});
		function checkInputs() {
			if($('#username').val().isEmpty()) { 
				$('#usernameNeeded').dialog('open');
				return false;
			}
			else if ($('#password').val().isEmpty()) {
				$('#passwordNeeded').dialog('open');
				return false;
			}
			return true;
		}
		$('#username, #password').keyup(function(e) {var code = (e.keyCode ? e.keyCode : e.which); if(code == 13) {	if(checkInputs()) {	$('#signInForm form').submit(); }}});
	});
	</script>
	<div id="usernameNeeded" title="Error" style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all"> 
			<p><span style="float: left; margin: 0 7px 50px 0;" class="ui-icon ui-icon-alert"></span> 
			Please enter your email address. This represents your username.</p>
	</div>
	<div id="passwordNeeded" title="Error" style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all"> 
			<p><span style="float: left; margin: 0 7px 50px 0;" class="ui-icon ui-icon-alert"></span> 
			Please enter your password.</p>
	</div>
</div>

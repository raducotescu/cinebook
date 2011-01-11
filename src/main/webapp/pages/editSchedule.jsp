<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="header.jsp">
	<jsp:param name="title" value="Edit Schedule" />
</jsp:include>
<s:form action="saveMovieScheduleEntry" namespace="/movies" method="POST">
	<s:hidden name="scheduleEntryId" />
	<s:select list="movies" name="movieID" label="Movie" listKey="id" listValue="title" />
	<s:textfield label="Theatre" name="theatre"  />
	<s:textfield id="startTime" name="startTime" label="Scheduled time" />
	<s:submit />
</s:form>
<script type="text/javascript">$("#startTime").AnyTime_picker({ format: "%Y-%m-%d %H:%i", hideInput: true, placement: "inline" } );</script>
<jsp:include page="footer.jsp" />

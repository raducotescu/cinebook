<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form action="postComment" namespace="/user" method="POST">
	<s:hidden name="friendID" id="friendID" />
	<s:hidden name="userID" id="userID" />
	<s:textarea name="commentText" id="commentText" />
	<script type="text/javascript">
		CKEDITOR.replace('commentText',
			{
				toolbar :
					[
					['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
					['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
					['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
				    ['Link','Unlink','Anchor'],
				    ['Image', 'HorizontalRule','Smiley','SpecialChar'],
				    '/',
				    ['Styles','Format','Font','FontSize'],
				    ['TextColor','BGColor'],
				    ['Maximize', 'ShowBlocks']
					]
			});
	</script>
	<s:submit /> 
</s:form>
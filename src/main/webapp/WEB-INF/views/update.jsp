<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



 <form:form method="PUT" action="update" modelAttribute="note" > 
		<table>
			<tr>
				<td><form:label path="noteId">Note Id</form:label></td>
				<td><form:input path="noteId" value="${note.noteId}" /></td>
			</tr>
			<tr>
				<td><form:label path="noteTitle">Note Title</form:label></td>
				<td><form:input path="noteTitle" value="${note.noteTitle}" /></td>
			</tr>
			<tr>
				<td><form:label path="noteContent">Note Content</form:label></td>
				<td><form:input path="noteContent" value="${note.noteContent}" /></td>
			</tr>
			<tr>
				<td><form:label path="noteStatus">Note Status</form:label></td>
				<td><form:input path="noteStatus" value="${note.noteStatus}"/></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Update Note" /></td>
			</tr>
		</table>
 	</form:form> 
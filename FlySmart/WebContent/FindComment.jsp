<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Comments From UserName</title>
</head>
<body>
	<form action="findcomment" method="post">
		<h1>Search for Comments by UserName</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="commentCreate"><a href="commentcreate">Create Comment</a></div>
	<br/>
	<h1>Matching Comment</h1>
        <table border="1">
            <tr>
                <th>CommentId</th>
                <th>Content</th>
                <th>CarrierId</th>
                <th>UserName</th>
                <th>Rating</th>
                <th>Created Time</th>
                <th>Delete Comment</th>
                <th>Update Comment</th>
                
            </tr>
            <c:forEach items="${Comments}" var="comment" >
                <tr>
                    <td><c:out value="${comment.getCommentId()}" /></td>
                    <td><c:out value="${comment.getContent() }" /></td>
                    <td><a href="findcarriercomment?carrier=<c:out value="${comment.getCarrierId()}"/>"><c:out value="${comment.getCarrierId()}"/></a></td>
                    <td><c:out value="${comment.getUserName()}" /></td>
                    <td><c:out value="${comment.getRating()}" /></td>
                    <td><c:out value="${comment.getCreatedTime()}" /></td>
                    <td><a href="commentdelete?commentid=<c:out value="${comment.getCommentId()}"/>">Delete</a></td>
                    <td><a href="commentupdate?commentid=<c:out value="${comment.getCommentId()}"/>">Update</a></td>
                   </tr>
            </c:forEach>
       </table>
</body>
</html>

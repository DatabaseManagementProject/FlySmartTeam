<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Comments From CarrierId</title>
</head>
<body>
	<form action="findcarriercomment" method="post">
		<h1>Search for Comments by CarrierId</h1>
		<p>
			<label for="carrier">Carrier</label>
			<input id="carrier" name="carrier" value="${fn:escapeXml(param.carrier)}">
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
                    <td><c:out value="${comment.getCarrierId()}" /></td>
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

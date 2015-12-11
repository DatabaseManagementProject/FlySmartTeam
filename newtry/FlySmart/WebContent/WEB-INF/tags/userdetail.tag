<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@tag import="flight.model.User" %>
<%@attribute name="user" required="true" type="flight.model.User"%>

First Name: ${user.firstName} <br/>
Last Name: ${user.lastName} <br/>
Phone: ${user.phone}<br/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:userpage userName="${user.userName}">
  <p>
    <t:userdetail user="${user}"/>
  </p>
</t:userpage>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/2/2020
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>
<body>

<h1 class="nameFont1">Add role</h1>

<c:if test="${sessionAdd != null}" >
    <p class="addStatus">${sessionAdd}</p>
    <p class="addStatus">${sessionAdd}</p>
    <c:set var="sessionAddd"  scope="session" />
    <c:remove var="sessionAdd"/>
</c:if>

<div class="formDesign">
    <form action="addRole" method="post">


        <div class="form-group">
            <label >Choose User Role:</label>
            <select  name="role">
                <option value="admin">Admin</option>
                <option value="teacher">Teacher</option>
                <option value="parent">parent</option>
            </select>
        </div>

        <input type="submit" name="" value="Add New User" />
    </form>
</div>
</body>
</html>

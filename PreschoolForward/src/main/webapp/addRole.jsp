<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/2/2020
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<body>

<h1 class="nameFont1">User Register</h1>
<div class="formDesign">
    <form action="addUserServlet" method="post">

        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="first_name" class="form-control" placeholder="First Name"/>
        </div>
        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="last_name" class="form-control" placeholder="Last Name"/>
        </div>
        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" class="form-control" placeholder="username"/>
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" class="form-control" placeholder=""/>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" class="form-control" placeholder="youremail@somemail.com"/>
        </div>

        <div class="form-group">
            <label>Phone :</label>
            <input type="text" name="phone" class="form-control" placeholder="0000000000"/>
        </div>
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

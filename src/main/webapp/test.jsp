<%--
  Created by IntelliJ IDEA.
  User: supar
  Date: 4/11/2020
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="utf-8">
    <title>Q&A</title>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>

<h1>Welcome to Flashcards!</h1>
    <div id="qanda"></div>
        <c:forEach var="qa" items="${questionAnswer}">
            <p id="question"> Question: ${qa.question}</p>
            <button onclick="myFunction()">View Answer</button>
            <p id="answer"></p>
           <!-- <p id="answer"> Answer: ${qa.answer}</p>-->

        <button onClick="window.location.reload();">Next Question</button>


        <script>
            function myFunction() {
                document.getElementById("answer").innerHTML = "Answer: ${qa.answer}";
            }
        </script>
        </c:forEach>
    </div>
<footer>
    <div class="footerLeft">
        <p>create by</p>
        <p>Jacob Doney, Joel Swanson, Katya Mullendore, Suparin Fhlug</p>
    </div>
    <!--
        <div class="footerRight">
            <p>Flashcard Team Project</p>
            <p>Instructor Paula Waite</p>
        </div>-->
</footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PDF Reader</title>
</head>
<body>
    <h1>PDF Reader</h1>

    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" required />
        <button type="submit">Upload</button>
    </form>

    <c:if test="${not empty message}">
        <p style="color:red;">${message}</p>
    </c:if>

    <ul>
        <c:forEach items="${pdfs}" var="pdf">
            <li><a href="/view/${pdf.id}">${pdf.name}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
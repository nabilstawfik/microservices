<%-- 
    Document   : admin
    Created on : Aug 11, 2017, 2:42:01 PM
    Author     : nabil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page session="true"%>
<html>
    <head>
        <title>List items</title>
    </head>
    <body>
        <c:import url="../includes/header.jsp"></c:import>
            <h1>List items:</h1>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="msg">${success}</div>
        </c:if>
        <table class="simpletablestyle">
            <thead>
            <th align="left">Name</th>
            <th align="left">Price</th>
            <th></th>
        </thead>
        <c:forEach var="item" items="${items}">
            <tr>
                <td width="50%">
                    ${item.name}
                </td>
                <td width="30%">
                    ${item.price}
                </td>
                <td>
                    <a href="/show-item?itemId=${item.id}">Show</a> | <a href="/delete-item?itemId=${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br/>
    <a href="/add-item">Add new item</a>
    <c:import url="../includes/footer.jsp"></c:import>
</body>
</html>
<%-- 
    Document   : admin
    Created on : Aug 8, 2017, 2:42:01 PM
    Author     : nabil
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page session="true"%>
<html>
    <head>
        <title>Show item</title>
    </head>
    <body>
        <c:import url="../includes/header.jsp"></c:import>
            <h1>Item details:</h1>

            <div><b>Name:</b> ${item.name}</div>
        <div><b>price:</b> ${item.price}</div>
        <br/><br/>
        
        <c:import url="../includes/footer.jsp"></c:import>
    </body>
</html>
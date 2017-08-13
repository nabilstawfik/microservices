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
        <title>Add new item</title>
    </head>
    <body>
        <c:import url="../includes/header.jsp"></c:import>
            <h1>Item details:</h1>
        <form:form modelAttribute="itemDto" action="add-item" method="post" cssClass="form">
            <table>
                <tr>
                    <td>
                        <form:label path="name">Name</form:label>
                        </td>
                        <td>
                        <form:input path="name" name="name" id="name" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="price">Price</form:label>
                        </td>
                        <td>
                        <form:input path="price" name="price" id="price" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <form:button id="addItem" name="addItem">Add Item</form:button>
                        </td>
                    </tr>
                    <tr></tr>

                </table>
                <br/>
                
        </form:form>
        <c:import url="../includes/footer.jsp"></c:import>
    </body>
</html>
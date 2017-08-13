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
        <title>Registration</title>
    </head>
    <body>
        <c:import url="includes/header.jsp"></c:import>
        <div class="container">
            <div class="content">
                <form:form id="regForm" modelAttribute="userDto" action="register" method="post">
                    <table>
                        <tr>
                            <td>
                                <form:label path="email">Email</form:label>
                                </td>
                                <td>
                                <form:input path="email" name="email" id="email" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="password">Password</form:label>
                                </td>
                                <td>
                                <form:password path="password" name="password" id="password" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="oldPassword">Confirm Password</form:label>
                                </td>
                                <td>
                                <form:password path="oldPassword" name="oldPassword" id="oldPassword" />
                            </td>
                        </tr>

                        <tr>
                            <td></td>
                            <td>
                                <form:button id="register" name="register">Register</form:button>
                                </td>
                            </tr>
                        </table>
                </form:form>
            </div>
        </div>
        <c:import url="includes/footer.jsp"></c:import>
    </body>
</html>
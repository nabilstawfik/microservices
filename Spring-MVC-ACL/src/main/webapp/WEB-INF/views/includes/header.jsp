<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="all"/>
<div id="container">

    <header>
        <!-- csrt for log out-->
        <form action="<c:url value='/logout' />" method="post" id="logoutForm" class="invisible">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <div class="floatL">
            
        </div>
        <security:authorize access="isAuthenticated()">
            <div class="floatR">
                Welcome: ${pageContext.request.userPrincipal.principal.user.email} | <a href="javascript:document.getElementById('logoutForm').submit();"> Logout</a> | <a href="/list-items">Manage items</a>
            </div>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <div class="floatR">
                <a href="/register">Register</a> | 
                <a href="/login">Login</a>
            </div>
        </security:authorize>
        <div style="clear:both"></div>
        <hr/>
    </header>
    <section id="aside">
        <ul>
            <security:authorize access="isAuthenticated()">
                <li><a href="/list-items">Manage items</a></li>
                <li><a href="/add-item">Add item</a></li>
                <li><a href="javascript:document.getElementById('logoutForm').submit();"> Logout</a></li>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <li>
                    <a href="/register">Register</a>
                </li>
                <li>
                    <a href="/login">Login</a>
                </li>
            </security:authorize>
        </ul>
    </section>
    <section id="content">


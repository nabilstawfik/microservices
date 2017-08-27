<%-- 
    Document   : home
    Created on : Aug 25, 2017, 1:57:48 PM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>

        <title>Welcome !</title>
    </head>
    <body>
        <c:import url="includes/header.jsp"></c:import>
            <form  method="post">
                <table width="75%">
                    <tr>
                        <td width="25%">HTTP(s) URL:</td>
                        <td><input type='text' name='pageURL'></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Fetch Analytics"/>
                        <c:if test="${not empty errorMsg}">
                            <div class="error">${errorMsg}</div>
                        </c:if>
                    </td>
                </tr>
                <tr></tr>

            </table>
            <br/>
        </form>
        <br/>
        <c:if test="${not empty pageAnalysisDto}">
            <h1>Analytics for URL: <a href="${pageURL}">${pageURL}</a></h1>
            <div>
                <div><h2>Document Basic Info:</h2></div>
                <table class="simpletablestyle">
                    <thead>
                        <tr class="header">
                            <th width="30%">Name</th>
                            <th>Value</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>Html Version </td>
                        <td>${pageAnalysisDto.htmlVersion}</td>
                    </tr>
                    <tr>
                        <td width="30%">Page Title </td>
                        <td width="70%">${pageAnalysisDto.pageTitle}</td>
                    </tr>
                    <tr>
                        <td width="30%">Contains Login Form</td>
                        <td width="70%">${pageAnalysisDto.containLoginForm}</td>
                    </tr>
                </table>
            </div>

            <div>
                <br/><div><h2>Headings Group by Heading Level:</h2></div>
                <table class="simpletablestyle">
                    <thead>
                        <tr class="header">
                            <th width="30%">Heading Level</th>
                            <th>Number of Headings</th>
                        </tr>
                    </thead>
                    <c:forEach items="${pageAnalysisDto.headings}" var="heading">
                        <tr>
                            <td width="30%">${heading.key} </td>
                            <td>${heading.value}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>

            <div>
                <br/><div><h2>Hypermedia Link Groups:</h2></div>
                <table class="simpletablestyle">
                    <thead>
                        <tr class="header">
                            <th width="30%">Link Group</th>
                            <th>Number of Hypermedia Links</th>
                        </tr>
                    </thead>
                    <c:forEach items="${pageAnalysisDto.hyperMediaLinksGroupedByLinkGroup}" var="mediaLinks">
                        <tr>
                            <td>${mediaLinks.key.toString().toLowerCase()}</td>
                            <td>
                                ${mediaLinks.value}<br/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div>
                <br/><div><h2>Valid Hypermedia Links:</h2></div>
                <table class="simpletablestyle">
                    <thead>
                        <tr class="header">
                            <th width="15%">Status Code</th>
                            <th width="50%">URL</th>
                            <th>Response Description</th>
                        </tr>
                    </thead>
                    <c:forEach items="${pageAnalysisDto.hyperMediaLinksGroupedByLinkResponseStatusCode[true]}" var="hyperMediaLink">
                        <tr>
                            <td>${hyperMediaLink.responseStatusCode}</td>
                            <td>${hyperMediaLink.url}</td>
                            <td>${hyperMediaLink.responseDescription}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div>
                <br/><div><h2>Invalid Hypermedia Links:</h2></div>
                <table class="simpletablestyle">
                    <thead>
                        <tr class="header">
                            <th width="15%">Status Code</th>
                            <th width="50%">URL</th>
                            <th>Response Description</th>
                        </tr>
                    </thead>
                    <c:forEach items="${pageAnalysisDto.hyperMediaLinksGroupedByLinkResponseStatusCode.get(false)}" var="hyperMediaLink">
                        <tr>
                            <td >${hyperMediaLink.responseStatusCode}</td>
                            <td >${hyperMediaLink.url}</td>
                            <td>${hyperMediaLink.responseDescription}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
        <c:import url="includes/footer.jsp"></c:import>
    </body>

</html>
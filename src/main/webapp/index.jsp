<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="head.jsp"></jsp:include>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="navigation.jsp"></jsp:include>
        <div class="content">
            <div class="subcontent">
                <fieldset>
                    <legend>Database Searches</legend>
                    <form action="${pageContext.request.contextPath}/search" method="get">
                        <div class="fullwidth">
                            <input type="radio" id="materials" name="tables" value="materials" required>
                            <label for="materials">materials</label><br>
                            <input type="radio" id="periods" name="tables" value="periods" required>
                            <label for="periods">periods</label><br>
                            <input type="radio" id="prices" name="tables" value="prices" required>
                            <label for="prices">prices</label>
                            <input type="radio" id="services" name="tables" value="services" required>
                            <label for="services">services</label>
                            <label for="searchItem">Enter the ID to retrieve:</label>
                            <input type="text" id="searchItem" name="searchItem" value="" pattern="^[0-9]*">
                        </div>
                        <button type="submit" name="submit" value="search">Search</button>
                        <button type="submit" name="submit" value="viewAll">View All</button>
                    </form>
                </fieldset>
            </div>
            <div style="margin: auto; width: 75%;">
                <c:if test="${sessionScope.processed}">
                    <c:forEach var="result" items="${results}">
                            <c:out value="${result.toString()}"></c:out><br>
                    </c:forEach>
                </c:if>
            </div>
            <div class="subcontent"">
            <fieldset>
                <legend>API Call Examples</legend>
                <div>
                    <p>Services</p>
                    <hr>
                    <table>
                        <tbody>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/servicesV1/"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve all Services</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/servicesV2/3"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve Service with ID 3</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;">
                                    <form action="${pageContext.request.contextPath}/comPosting/servicesV3/Sample%20Service/A%20sample%20Service%20object" method="POST">
                                        <button style="width: 100px;">POST</button>
                                    </form>
                                </td>
                                <td>Add a new Service</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;">
                                    <form action="${pageContext.request.contextPath}/routing" method="post">
                                        <input type="text" name="targetUrl" value="${pageContext.request.contextPath}/comPosting/servicesV2/alter/1/Igniting/Burning%20some%20materials" hidden>
                                        <button style="width: 100px;">PUT</button>
                                    </form>
                                </td>
                                <td>Checking edit operation</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;">
                                    <form action="${pageContext.request.contextPath}/routing" method="post">
                                        <input type="text" name="targetUrl" value="${pageContext.request.contextPath}/comPosting/servicesV2/drop/1" hidden>
                                        <button style="width: 100px;">DELETE</button>
                                    </form>
                                </td>
                                <td>Checking delete operation</td>
                            </tr>
                        </tbody>
                    </table>
                    <p>Materials</p>
                    <hr>
                    <table>
                        <tbody>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/materialsV1/"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve all Materials</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/materialsV2/3"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve Material with ID 5</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;">
                                    <form action="${pageContext.request.contextPath}/comPosting/materialsV3/Sample%20Material/A%20sample%20Material%20object" method="POST">
                                        <button style="width: 100px;">POST</button>
                                    </form></a>
                                </td>
                                <td>Add a new Material</td>
                            </tr>
                        </tbody>
                    </table>
                    <p>Prices</p>
                    <hr>
                    <table>
                        <tbody>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/pricesV1/"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve all Prices</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/pricesV2/2"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve Price with ID 2</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;">
                                    <form action="${pageContext.request.contextPath}/comPosting/pricesV3/9%2E99/A%20sample%20Price%20object" method="POST">
                                        <button style="width: 100px;">POST</button>
                                    </form></a>
                                </td>
                                <td>Add a new Price</td>
                            </tr>
                        </tbody>
                    </table>
                    <p>Periods</p>
                    <hr>
                    <table>
                        <tbody>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/periodsV1/"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve all Periods</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;"><a href="${pageContext.request.contextPath}/comPosting/periodsV2/2"><button style="width: 100px;">GET</button></a></td>
                                <td>Retrieve Period with ID 2</td>
                            </tr>
                            <tr style="height: 30px;">
                                <td style="vertical-align: top;">
                                    <form action="${pageContext.request.contextPath}/comPosting/periodsV3/90/Day" method="POST">
                                        <button style="width: 100px;">POST</button>
                                    </form></a>
                                </td>
                                <td>Add a new Period</td>
                            </tr>
                        </tbody>
                    </table>


                </div>
            </fieldset>
        </div>
    <c:set var="processed" value="false" scope="session"></c:set>
    </div>
</body>
</>
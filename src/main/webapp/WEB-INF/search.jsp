<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Water BnB</title>
</head>
<body>
 	<div class="header">
		<h3>Current Listings</h3>
	</div>
		<c:if test="${currentUser.equals(currentUser)}">
		<form id="logoutForm" method="POST" action="/logout">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<input id="submit" type="submit" value="Logout!" />
    	</form>
    	</c:if>
	<div class="searchForm">
		<form method="GET" action="/search/address" >
			<label>Find Your Result:
			
			<input name="address" name="search"/></label>
			
			<input class="searchbtn" type="submit" value="Search"/>
		</form>
	</div>
	<div>
		<table>
		  <tr>
		    <th>Address</th>
		    <th>Pool Size</th>
		    <th>Cost per Night</th>
		    <th>Details</th>
		  </tr>
		  <c:forEach items="${listings }" var="listing" varStatus="loop">
		  <tr>
		    <td><c:out value="${listing.address }"/></td>
		    <td><c:out value="${listing.poolSize }"/></td>
		    <td><c:out value="${listing.price }"/></td>
		    <td><a href="/pools/${listing.id }">See More</a></td>
		  </tr>
		  </c:forEach>
		</table>
	</div>
</body>
</html>
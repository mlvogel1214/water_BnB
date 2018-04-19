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
		<a href="/host/dashboard">Home</a>
	</div>
		<form id="logoutForm" method="POST" action="/logout">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<input id="submit" type="submit" value="Logout!" />
    	</form>
		<p>Review of: <c:out value="${listing.address }"/></p>
	<div>
		<form:form method="POST" action="/add/review/${listing.id }/${currentUser.id }" modelAttribute="new">
        	<p>
            	<form:errors path="comment"/>
            	<form:textarea rows="4" cols="50" path="comment"/>
        	</p>
        	<p>
        		<form:label path="rating">Rating:</form:label>
            	<form:select name="rating" path="rating">
        			<form:option value="1" >1</form:option>
        			<form:option value="2" >2</form:option>
        			<form:option value="3" >3</form:option>
        			<form:option value="4" >4</form:option>
        			<form:option value="5" >5</form:option>
        		</form:select>
        	</p>
        	<input type="submit" value="Submit Review"/>
    	</form:form>
	</div>
</body>
</html>
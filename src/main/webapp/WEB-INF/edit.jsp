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
		<form:form method="POST" action="/edit/listing/${listing.id }" modelAttribute="new">
        	<p>
            	<form:label path="address">Address:</form:label>
            	<form:errors path="address"/>
            	<form:input path="address" value="${listing.address }"/>
        	</p>
        	<p>
            	<form:label path="description">Description:</form:label><br>
            	<form:errors path="description"/>
            	<form:textarea rows="4" cols="50" path="description" value="${listing.description }"/>
        	</p>
        	<p>
            	<form:label path="price">Cost Per Night:</form:label>
				<form:errors path="price"/>
				<form:input type="number" path="price" value="${listing.price }"/>
        	</p>
        	<p>
        		<form:label path="poolSize">Pool Size:</form:label>
            	<form:select name="size" path="poolSize">
            		<form:option value="${listing.poolSize }" ><c:out value="${listing.poolSize }"/></form:option>
        			<form:option value="Small" >Small</form:option>
        			<form:option value="Medium" >Medium</form:option>
        			<form:option value="Large" >Large</form:option>
        		</form:select>
        	</p>
        	<input type="submit" value="Edit Listing"/>
    	</form:form>
	<div>
		Reviews (<c:out value="${reviews.size() }"/>)
		<c:forEach items="${reviews }" var="review">
			<p><c:out value="${review.user.firstName }"/></p>
				<p><c:out value="${review.rating }"/></p>
				<p><c:out value="${review.comment }"/></p>
			----------------------------------------------------
		</c:forEach>
	</div>
</body>
</html>
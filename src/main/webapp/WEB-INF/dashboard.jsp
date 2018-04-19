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
		<form id="logoutForm" method="POST" action="/logout">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<input id="submit" type="submit" value="Logout!" />
    	</form>
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
		    <c:if test="${currentUser != listing.getUser() }">
		    <td><c:out value="${ratingAvg}"/> /5</td>
		    </c:if>
		    <c:if test="${currentUser.id == listing.getUser().getId() }">
		    
		    <td><a href="/edit/listing/${listing.id }">
		    <c:out value="${listing.getReviews()[0].getRating()}"/> /5 - Edit</a></td>
		    </c:if>
		  </tr>
		  </c:forEach>
		</table>
	</div>
	<div>
		<form:form method="POST" action="/add/listing/${currentUser.id }" modelAttribute="new">
        	<p>
            	<form:label path="address">Address:</form:label>
            	<form:errors path="address"/>
            	<form:input path="address"/>
        	</p>
        	<p>
            	<form:label path="description">Description:</form:label>
            	<form:errors path="description"/>
            	<form:input path="description"/>
        	</p>
        	<p>
            	<form:label path="price">Cost Per Night:</form:label>
				<form:errors path="price"/>
				<form:input type="number" path="price"/>
        	</p>
        	<p>
        		<form:label path="poolSize">Pool Size:</form:label>
            	<form:select name="size" path="poolSize">
        			<form:option value="Small" >Small</form:option>
        			<form:option value="Medium" >Medium</form:option>
        			<form:option value="Large" >Large</form:option>
        		</form:select>
        	</p>
        	<input type="submit" value="Add Listing"/>
    	</form:form>
	</div>
	<div class="errs">
    		<% if(request.getAttribute("errs") != null) { %>
	  		<fieldset>
	  			<legend>Errors</legend>
	  			<c:forEach items="${errs}" var="err">
					<p><c:out value="${err.getDefaultMessage()}"/></p>
				</c:forEach>
	  		</fieldset>
	  	<% } %>
    </div>
</body>
</html>
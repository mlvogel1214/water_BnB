<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	<div class="listingInfo">
		<p><c:out value="${listing.address}"/></p>
		<p><c:out value="${listing.description }"/></p>
	</div>
	<div class="listingUser">
		<p>Email: <c:out value="${listing.user.username}"/></p>
		<p>Name: <c:out value="${listing.user.firstName} ${listing.user.lastName }"/></p>
		<p>Pool Size: <c:out value="${listing.poolSize}"/></p>
		<p>Cost: <c:out value="${listing.price}"/></p>
	</div>
	<div>
		<span>Reviews: <c:out value="${ratingAvg}"/> /5 </span><span class="reviewLink"><a href="/pools/${listing.id }/review">Leave a Review</a></span>
		
	</div>
	<div class="reviewBox">
		<c:forEach items="${reviews }" var="review">
			<p><c:out value="${review.user.firstName}"/></p>
			<h4>Rating: <c:out value="${review.rating }"/> /5</h4>
			
			<p><c:out value="${review.comment }"/></p>
			------------------------------------------------------------------------
		</c:forEach>
	</div>
</body>
</html>
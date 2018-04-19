<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Water BnB</title>
</head>
<body>
	<a href="/login">Sign In/ Sign Up</a>
	<div class="searchForm">
		<h3>Find places to swim and sleep on water bnb!</h3>
		<form method="GET" action="/search/address" >
			<label>Find Your Result:
			
			<input name="address" name="search"/></label>
			
			<input class="searchbtn" type="submit" value="Search"/>
		</form>
	</div>

</body>
</html>
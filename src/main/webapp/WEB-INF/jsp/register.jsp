<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- Begin Taglibs -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- End Taglibs -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>

	<div>
		<h1>Sign Up</h1>
	</div>

	<div>
		<form:form class="form" modelAttribute="registerForm" role="form">

			<div>
				<form:label path="username">username</form:label>
				<form:input path="username" type="text" placeholder="Your username" />
				<p>Enter a unique username. It will also be your login username.</p>
			</div>

			<div>
				<form:label path="email">email</form:label>
				<form:input path="email" type="email" placeholder="Your email" />
				<p>Enter your email address. We will not spam you.</p>
			</div>

			<div>
				<form:label path="fullname">full name</form:label>
				<form:input path="fullname" type="text" placeholder="Your full name" />
				<p>Enter your full name.</p>
			</div>

			<div>
				<form:label path="password">password</form:label>
				<form:input path="password" type="password" placeholder="Your password" />
				<p>Enter your password.</p>
			</div>
			
			<div>
			<button type="submit">register</button>
			<button type="reset">clear</button>
			</div>

		</form:form>
	</div>

</body>
</html>
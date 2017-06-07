<%@include file="include/header.jsp"%>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">
			<spring:message code="registerForm.form.title" />:
		</h3>
	</div>

	<div class="panel-body">
		<form:form class="form" modelAttribute="registerForm" role="form">

			<div class="form-group">
				<spring:message code="registerForm.form.username.placeholder"
					var="namePlaceholder" />
				<form:label path="username">
					<spring:message code="registerForm.form.username" />:
				</form:label>
				<form:input path="username" class="form-control"
					placeholder="${namePlaceholder}" />
				<form:errors cssClass="error" path="username"></form:errors>
			</div>

			<div class="form-group">
				<spring:message code="registerForm.form.email.placeholder"
					var="emailPlaceholder" />
				<form:label path="email">
					<spring:message code="registerForm.form.email" />:
				</form:label>
				<form:input path="email" class="form-control" type="email"
					placeholder="${emailPlaceholder}" />
				<form:errors cssClass="error" path="email"></form:errors>
			</div>

			<div class="form-group">
				<spring:message code="registerForm.form.fullname.placeholder"
					var="fullnamePlaceholder" />
				<form:label path="fullname">
					<spring:message code="registerForm.form.fullname" />:
				</form:label>
				<form:input path="fullname" class="form-control"
					placeholder="${fullnamePlaceholder}" />
				<form:errors cssClass="error" path="fullname"></form:errors>
			</div>

			<div class="form-group">
				<spring:message code="registerForm.form.password.placeholder"
					var="passwordPlaceholder" />
				<form:label path="password">
					<spring:message code="registerForm.form.password" />:
				</form:label>
				<form:input path="password" type="password" class="form-control"
					placeholder="${passwordPlaceholder}" />
				<form:errors cssClass="error" path="password"></form:errors>
			</div>

			<div>
				<button class="btn btn-primary" type="submit">
					<spring:message code="registerForm.form.submit" />
				</button>
				<button class="btn btn-default" type="reset">
					<spring:message code="registerForm.form.reset" />
				</button>
			</div>

		</form:form>
	</div>
</div>
</div>
<%@include file="include/footer.jsp"%>
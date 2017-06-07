<%@include file="include/header.jsp"%>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Chart:</h3>
	</div>

	<div class="panel-body">
		<form:form action="/member/chart" class="form" modelAttribute="chart" role="form">

			<div class="form-group">
				<form:label path="symbol">Symbol:</form:label>
				<form:input path="symbol" class="form-control"
					placeholder="Symbol" />
				<form:errors cssClass="error" path="symbol"></form:errors>
			</div>
			
			<div>
				<button class="btn btn-primary" type="submit">Show Chart</button>
				<button class="btn btn-default" type="reset">Clear</button>
			</div>

		</form:form>
	</div>
</div>
</div>
<%@include file="include/footer.jsp"%>
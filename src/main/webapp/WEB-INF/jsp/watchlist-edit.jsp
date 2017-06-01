<%@include file="include/header.jsp"%>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Edit Watchlist:</h3>
	</div>

	<div class="panel-body">
		<form:form action="/admin/watchlist/edit" class="form" modelAttribute="watchlistForm" role="form">

			<div class="form-group">
				<form:label path="symbol">Symbol:</form:label>
				<form:input path="symbol" class="form-control"
					placeholder="Symbol" />
				<form:errors cssClass="error" path="symbol"></form:errors>
			</div>

			<div class="form-group">
				<form:label path="active">Active:</form:label>
				<form:input path="active" class="form-control" type="text"
					placeholder="Y or N" />
				<form:errors cssClass="error" path="active"></form:errors>
			</div>
			
			<div>
				<button class="btn btn-primary" type="submit">Update</button>
				<button class="btn btn-default" type="reset">Clear</button>
			</div>

		</form:form>
	</div>
</div>
<%@include file="include/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.to {
	width: 110px;
	text-align: center;
}
</style>
<form class="card">
	<div class="card-header bg-light">
		<h5 class="mb-0">로그인</h5>
	</div>
	<div class="card-body p-0">
		<div class="border border-top-0 border-200 d-flex">
			<a class="btn btn-primary to">ID: </a> <input
				class="form-control border-0 rounded-0 outline-none px-x1"
				id="username" type="text">
		</div>
		<button type="button" id="login" class="btn btn-primary">Login</button>
	</div>
</form>
<hr>
<form>
	<div class="card-header bg-light">
		<h5 class="mb-0">알람 전달</h5>
	</div>

	<div class="card-body p-0">
		<div class="border border-top-0 border-200 d-flex">
			<a class="btn btn-primary to">Receiver ID: </a> <input
				class="form-control border-0 rounded-0 outline-none px-x1"
				id="receiver" type="text">
		</div>
		<button type="button" id="cool" class="btn btn-primary">울어라!</button>
	</div>
</form>



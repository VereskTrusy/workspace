document.calForm.addEventListener("submit", (e)=>{
	e.preventDefault();
	
	let form = e.target;
	
	let url = form.action;
	let method = form.method;
	
	let header = {
		"contentType" = "application/json",
		"Accept":"applicaton/json"
	};
	
	let target = {
		leftOp : form.leftOp.value;
		rightOp : form.rightOp;
		operator : form.operator;
		
	};
	lwet body = JSON.stringify(target);
	
	fetch(url, {
		method : method,
		headers : header,
		body:body
	})
	.then(resp=>resp.json())
	.then(cv=>console.log(cv))
	;
});
function clickHandler(event){
	event.preventDefault();
	let leftOp = document.querySelector("[name='leftOp']");
	let rightOp = document.querySelector("[name='rightOp']");
	let btn = document.querySelector("[name='btn']");
	
	let result = 0;
	
	let url = btn.dataset.url;
	let method = "post";
	let headers = {
		"accept" : "application/json"
	};
	let option = {
		method : method,
		headers : headers
	};
	
	fetch(url, option)
	.then(resp=>{
		return resp.json();
	})
	.then((data)=>{	
		
		console.log(data);
	})
	.catch(err=>{
		console.log(err);
	});
	
	
}
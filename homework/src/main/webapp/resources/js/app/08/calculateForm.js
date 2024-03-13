/**
 * 
 */
// page loaded 이벤트
document.addEventListener("DOMContentLoaded", (e)=>{
	//console.log(e.target); // document 객체
	
	let cal = document.querySelector("#cal");
	
	// 2. "=" 버튼 이벤트 선언하기
	cal.addEventListener("click", (e) => {
		// console.log(e.target); // "=" 버튼 엘리먼트
		e.preventDefault();
		let form = document.frm;
		let leftOp = form.leftOp; // 숫자1
		let rightOp = form.leftOp; // 숫자2
		//console.log(leftOp);
		let operator = form.operator; // 연산자
		
		let lov = leftOp.value; // 1
		let rov = rightOp.value; // 2
		let os = operator.value; // MULTIPLY
		
		url = form.action;
		let method = form.method ?? "get";
		
		let headers = {
			"content-type" : "text/plain",
			"Accept" : "application/json"
		};
		
		let body = new FormData(form);
		
		let options = {
			method : method,
			headers : headers,
			body : body
		};
		
		fetch(url, options)
		.then((resp) => {
			if(resp.ok){
				return resp.text();
			}else{
				throw new Error(`처리 실패 상태코드 : ${resp.status}`);
			}
		})
		.then((text)=>{
			resultArea.innerText = text;
		})
		.catch((error)=>{
			console.log(error);
		})
	});
});


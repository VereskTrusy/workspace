/*document.btsForm.addEventListener("submit", function({target}){ // target 이라는 속성으로 e.target을 받아 올 수 있다.
	// Form 의 submit 이벤트 멈추기
	e.preventDefault();
	
	
	// Select Box Value 값 가져오기
	let memValue = window["btsForm"].member.value;
	
	// 비동기 전송 정보 만들기 
	let form = window["btsForm"]; // form 요소 접근
	let url = form.action; // http://localhost/WebStudy01/bts
	let method = form.method;
	let headers = {
		"content-type" : form.enctype, // 보낼 컨텐츠 설정
		"accept" : "text/plain" // 받을 컨텐츠 설정
	};
	let fd = new FormData(form); //  폼데이터 키, 값
	let queryString = new URLSearchParams(fd).toString(); // 직렬화
	let body = queryString; 
	let option = {
		method : method,
		headers : headers,
		body : body
	};
	console.log(queryString);
	fetch(url, option)
	.then((resp)=>{
		return resp.text()
	})
	.then((data)=>{
		window['test'].value = data;
		//window['area'].innerHTML = data;
	})
	.catch()
});*/

	document.btsForm.addEventListener("submit", function(e){
		e.preventDefault();
		// 비동기 전송 정보 만들기 
		let form = e.target; // form 요소 접근
		let url = form.action; // http://localhost/WebStudy01/bts
		let method = form.method;
		let headers = {
			"content-type" : form.enctype, // 보낼 컨텐츠 설정
			"accept" : "text/html" // 받을 컨텐츠 설정
		};
		let formData = new FormData(form); //  폼데이터 키, 값
		let queryString = new URLSearchParams(formData).toString(); // 직렬화
		let body = queryString;
		let option = {
			method : method,
			headers : headers,
			body : body
		};
		fetch(url, option)
		.then((resp)=>{ // 리절브 함수 1
			return resp.text();
		})
		.then((txt)=>{
			window["result-area"].innerHTML = txt;
		})
		.catch((e)=>{ // 리젝트 함수
			console.error(e);
		});
	});
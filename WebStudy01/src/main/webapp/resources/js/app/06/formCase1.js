// case 1 : a 태그의 요청을 비동기로 처리하기
//document.querySelector("a") // 하나의 a 태그만 검색
let aTags = document.querySelectorAll("a") // 하나의 a 태그만 검색
console.log(aTags);

aTags.forEach(v => {
	v.addEventListener("click", (e) => { // (e) 여기서 e는 이벤트핸들러다
		e.preventDefault(); // 동기요청 중단
		
		let url = e.target.href; // a 태그 접근.속성 중 href 값 가져옴
		let options = { // options 객체
			//method : get, // 생략가능 
			headers : {
				"Accept":"application/json"
			} 
			//body :, // get방식은 body가 없음으로 생략가능
		};
		fetch(url, options) // promise 객체가 리턴이 된다. 리절브함수(정상처리), 리젝트함수(문제발생)
		.then((resp) => { // 리절브 함수 바인딩
			if(resp.ok){ // 먼저 리스폰스의 결과가 200-299까지의 값인지 확인 및 boolean 리턴.
				return resp.json(); // response stream을 받아 완료 될 때까지 읽고, json으로 파싱한 결과를 프로미스로 반환한다.
			}else{
				throw new Error(`요청 처리 실패, 상태코드 : ${resp.status}`); // 리젝트 함수에서 처리가 된다.
			}
		})
		.then((obj) => { // promise 체이닝
			console.log(obj);
		})
		.catch((err) => { // 리젝트 함수 바인딩
			console.log(err);
		})
	});
});


// case 2 : form의 전송 이벤트를 비동기로 변경하기

// 폼 셀렉팅하기
let forms = document.forms;
forms[0].addEventListener("submit", (e) => {
	e.preventDefault();
	let form = e.target;
	
	// request line
	let url = form.action;
	let method = form.method;
	
	// request header
	let headers ={
		"content-type" : form.enctype, // 메소드 형식이 post인 경우에만 사용가능
		"accept" : "text/html"
	};
	
	// request body
	let formData = new FormData(form); // 폼안의 파라미터 확보
	console.log(new URLSearchParams(formData).toString()); // 직렬화된 파라메터 만들기
	let body = new URLSearchParams(formData).toString();
	
	let options = {
		method:method,
		headers:headers
	};
	
	if(method == "get"){
		url = `${url}?${body}`;
	}else{
		options.body = body;
	} 
	
	fetch(url, options)
	.then((resp) => {
		if(resp.ok){
			return resp.text();
		}else{
			throw new Error(`에러 발생, 상태코드 : ${resp.status}`);
		}
	})
	.then((html) => {
		//console.log(html);
		//document.body.append(html);
		document.body.innerHTML = document.body.innerHTML + html;
	})
	.catch((err) => {
		console.log(err);
	})
});






























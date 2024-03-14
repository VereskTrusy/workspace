/**
 * window['mbti-form'] --->>  연상배열구조 이름(id)을 값으로 사용한다.
 */
console.log(window['mbti-form']);
window['mbti-form'].addEventListener("submit", (e)=>{
	e.preventDefault(); // 액션 중지
	
	// 비동기 요청 처리
	let form = e.target; // mbti-form
	let url = form.action;
	let method = form.method;
	let headers = {
		"content-type" : form.enctype, // 보낼 컨텐츠 설정
		"accept" : "text/html" // 받을 컨텐츠 설정
	}
	let fd = new FormData(form); //  폼데이터 키, 값
	let queryString = new URLSearchParams(fd).toString(); // 직렬화
	let body = queryString;
	let option = {
		method : method,
		hreaders : headers,
		body : body
	}
	fetch(url, option) // durl answpdltdma
	.then((resp)=>{ // ok 가정
		return resp.text()
	})
	.then((html)=>{
		//let newDocument = new DOMParser().parseFromString(html, headers.accept); // html 읽은다음 객체로 변환 시켜주는 애
		//let element = newDocument.querySelector("pre");
		//window['mbti-area'].append(element);
		window['mbti-area'].innerHTML = html;
	})
	.catch()
	
});
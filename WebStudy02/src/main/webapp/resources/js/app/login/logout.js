/**
 * 
 */
document.addEventListener("click", (event) => {
	if(!event.target.classList.contains("asyncA")) return false; // 클릭하지 않은 객체 거르기
	
	event.preventDefault();
	//console.log("이벤트 들어옴");
	
	let aTag = event.target;
	let url = aTag.href;
	//let method = aTag.dataset.method ? aTag.dataset.method : "get";
	let method = aTag.dataset.method ?? "get"; // 널병합연산자,  잇으면 앞에꺼 없으몀 뒤에꺼
	let headers = {
		//"content-type" : "",
		"Accept" : "text/plain"
	};
	let options = {
		method:method,
		headers:headers
	};
	fetch(url,options)
	.then((resp) => {
		if(resp.ok){
			//console.log(resp.text());
			return resp.text();
		}else{
			throw new Error(`처리 실패 상태코드 : ${resp.status}`);
		}
	})
	.then((html)=>{ // 프로미스가 반환되는 이유는 dom객체를 다시 파싱해야해서 / 시간 오래 걸림 / 
		msgArea.innerText = html; // id속성은 그대로 글로벌 변수에 반영된다.
	})
	.catch((error)=>{
		console.log(error);
	})
});
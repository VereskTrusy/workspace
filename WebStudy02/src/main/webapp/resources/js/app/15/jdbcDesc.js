/*
json 데이터를 수신하고, csr 방식으로 html 소스 생성
*/
document.addEventListener("DOMContentLoaded", (e)=>{
	// 비동기 요청 
	url = "";
	let method = "get";
	
	let headers = {
		"Accept" : "application/json"
	};
	let options = {
		method : method,
		headers : headers
	};
	fetch(url, options)
	.then((resp) => {
		if(resp.ok){
			return resp.json();
		}else{
			throw new Error(`처리 실패 상태코드 : ${resp.status}`);
		}
	})
	.then(({headers, propsName, resultList})=>{
		let trTag = `<tr>${headers.map(cn => `<th>${cn}</th>`).join("")}</tr>`;
		window["head-area"].innerHTML = trTag;
		
		let trTags = "";
		for(let propsMap of resultList){
			trTags += `<tr>${propsName.map(pn => `<td>${propsMap[pn]}</td>`).join("")}</tr>`;
		}
		window["data-area"].innerHTML = trTags;
	})
	.catch((err)=>{
		console.log(err);
	})
});
/*
1. 타겟 셀렉팅. querySelector(selector)
2. 이벤트 종류 결정. click, mouseover, dbclick...
3. 이벤트 핸들러 구현. function(){}, 
4. 타겟에 이벤트 핸들러 바인딩. JQ : on, js : 타겟.addEventListner(이벤트종류, 핸들러)
*/

let handler = function(event){
	//let target = event.target; // 이벤트를 발생시킨 타겟에 대한 레퍼런스를 가지고 잇음
	let url = "";
	
	// 비동기 처리
	// http://localhost/WebStudy01/03/viewer.jsp
	fetch("../eta")
	.then(resp => {
		if(resp.ok){
			return resp.text();
		} else {
			// 캣치로 이동하려면 에러를 발생시켜줘야함
			throw new Error("처리실패");
		}
	}) // 리절브 함수 예약
	.then((text) => {
		lyricArea.innerHTML = text
	})
	.catch((err) => { // 리젝트함수
		console.log(err);
	}); 
};
let btnTag = document.getElementById("lyricBtn");
//let lyricBtn = document.querySelector("#lyricBtn");
btnTag.addEventListener("click", handler);

// 서버 사이드에서는 동기요청이나 비동기요청이 다르지 않다. 

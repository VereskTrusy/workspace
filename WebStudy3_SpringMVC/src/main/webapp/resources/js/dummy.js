/**
 * 
 */
 alert("DUMMY");
 
 let divTag = document.getElementById("mainArea");
 let handler = function(event){ // 모든 함수는 이벤트 객체를 받을 수 있다.
 	let target = event.target;
 	//console.log(target === divTag);
 	target.style.backgroundColor = "red"; 
 };
 divTag.addEventListener("click", handler);
 
 

// 1. 타겟을 결정
// 2. 타겟의 이벤트 종류
// 3. 이벤트 핸들러
// 4. 핸들러를 타겟에 붙이기

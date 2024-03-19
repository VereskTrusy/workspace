/**
 *	type = button, 클릭 이벤트 발생, 타겟 : 버튼
	type = submit, 클릭 이벤트 발생 후 서브밋 이벤트 발생, 타겟 : 폼
	type = reset, 클릭 이벤트 발생 후 리셋 이벤트 발생 , 타겟 : 폼
	
	폼을 전송할때 페이지가 전환되는 걸 중단하고,
	선택한 이미지를 페이지 하단에(dody append 구조)에 출력
	
	1. submit 이벤트 중단
	2. 클라이언트가 선택한 option value 확보
	3. 새로운 이미지 태그 생성
	4. img 태그의 src 속성을 선택한 이미지로 대체
	5. body에 newImg 를 append 구조로 추가
 */

// 내가 한거...
/*let frm = document.querySelector("form");
frm.addEventListener("submit", ()=>{
    event.preventDefault();

	let select = document.querySelector("select");

	let img = document.querySelector("img");
	img.setAttribute("src", "http://localhost/WebStudy01/image.do?name="+select.options[select.selectedIndex].value);
});*/


// 쌤이 한거..
document.forms[0].addEventListener("submit", (event)=>{
	event.preventDefault();
	let url = event.target.action;
	let imageName = event.target.name.value; // 태그의 속성들을 사용할 수 있으니까 이거 알고 써라~~~~
	
	let newImg = document.createElement("img");
	newImg.src= `${url}?name=${imageName}`; // ${}템플ㄹ릿 머시기
	document.body.appendChild(newImg);
	
	
});

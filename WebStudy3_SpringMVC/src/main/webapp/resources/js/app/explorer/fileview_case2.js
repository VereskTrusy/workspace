const log = console.log; // 콘솔의 로그 레퍼런스 할당
let cPath = document.body.dataset.contextPath; // body 에 설정 해놓은 contextPath 가져오기(data property)

// 
document.querySelectorAll("li.folder>a").forEach((a)=>{ // . : 클래스로 셀렉팅,  > : 자식 셀렉팅 
	
	// left area 폴더 a 태그 클릭 이벤트
	a.addEventListener("click", e=>{
		// 기본 이벤트 정지
		e.preventDefault();
		
		// 폴더 or 파일 파라메터 세팅
		let type = a.dataset.click;
		let url = `${a.href}&type=${type}`;
		
		// 비동기 전송 : 파일/폴더 리스트 요청
		let method = "get";
		let headers = {
			"accept" : "application/json"
		};
		let option = {
			method : method,
			headers : headers
		};
		fetch(url, option)
		.then(resp=>resp.json())
		.then(outter=>{
			// 태그 문자열 만들기 1
			//['1','2'].map(ele=>ele+"번째").join("\n")
			/*let ulTag = `<ul class="col-6">
				<c:forEach items="${wrapperList}" var="wrapper" >
					${jsonObj.map(wrapper=>`<li id="${wrapper.name}" class="${wrapper.file ? 'file':'folder'}">${wrapper.name}</li>`)}
				</c:forEach>
			</ul>`;
			log(ulTag);*/
			
			// 태그 문자열 만들기 2
			let ulTag2 = `<ul class="col-6">`;
			let jsonObj = outter.wrapperList;
			for(let wrapper of jsonObj){
				ulTag2 += `
					<li data-name="${wrapper.name}" id="${wrapper.path}" class="${wrapper.file ? 'file':'folder'}">${wrapper.name}</li>
				`;
			}
			
			ulTag2 += `</ul>`;
			//log(ulTag2);
			
			// div 태그에 만든 태그 넣어주기
			window['right-area'].innerHTML = ulTag2;
		})
		.catch(e=>console.error(e))
	});
	
	// left area 폴더 a 태그 더블클릭 이벤트
	a.addEventListener("dblclick", e=>{
		// 기본 이벤트 정지
		e.preventDefault();
		
		// 폴더 or 파일 파라메터 세팅
		let type = a.dataset.dblclick;
		let url = `${a.href}&type=${type}`;  // a태그의 href 속성 가져오깅
		
		window.location.href = url;
	});
});

// right area 폴더/파일 더블클릭 이벤트
window['right-area'].addEventListener("click", function(e){
	// div > li 요소 검사
	if(!e.target.classList.contains("file")) return false;
	
	// 파일/폴더 경로/파일명 가져오기(data property)
	let path = e.target.id;
	
	// 비동기 전송 : 파일 사이즈 요청
	let url = `${cPath}/case2/fileInfo`;
	let method = "get";
	let headers = {
		"accept" : "application/json"
	};
	let urlSearchParams = new URLSearchParams(); // key, value 의 파라메터 만들기(FormData 대신 활용함)
	urlSearchParams.append("path", path);
	let queryString = urlSearchParams.toString();
	let option = {
		method : method,
		headers : headers
	};
	fetch(`${url}?${queryString}`, option)
	.then(resp=>resp.json())
	.then(jsonObj=>{
		log(jsonObj.size);
		e.target.innerHTML = e.target.dataset.name + ", " + jsonObj.size; // e.target = li , li data -> dataset
	})
	.catch(err=>console.error(err))
});



/*

*/
window['ua-btn'].addEventListener("click", (e)=>{
	let agent = window.navigator.userAgent;
	
	const BrowserInfo = {
		EDG:"엣지",
	    WHALE:'웨일',
	    CHROME:'크롬',
	    OTHERS:'기타',
		
		findBrowserName : function(agent){
			agent = agent.toUpperCase();
			let browserName = this.OTHERS;
			
			for(let prop in this){
				if(agent.indexOf(prop) >= 0){
					browserName = this[prop]
					break;
				}
			}
			
			return browserName;
		}
	}
	BrowserInfo['SAFARI'] = "사파리";
	
	let browserName = BrowserInfo.findBrowserName(agent);
	
	msgArea.innerHTML = browserName;
});






// 이벤트 버블링
// 도큐먼트 전체에 이벤트를 걸고
// 발생된 이벤트가 a 태그 이고, 클래스명이 같은지 검사하여 검사한다.
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


/*document.addEventListener("click", (e)=>{
	if(!e.target.classList.contains("clientBtn")) return false;
	//console.log(window.navigator.userAgent);
	
	let userAgent = window.navigator.userAgent.toUpperCase();
	
	const BrowserInfo = {
	    EDG: { name: '엣지' },
	    WHALE: { name: '웨일' },
	    CHROME: { name: '크롬' },
	    SAFARI: { name: '사파리' },
	    OTHERS: { name: '기타' },
	
	    findBrowser: function(userAgent) {
	        let finded = this.OTHERS;
	
	        for (const key in this) {
	            if (this[key] !== this.findBrowser && userAgent.toUpperCase().includes(key)) {
	                finded = this[key];
	                break;
	            }
	        }
	        return finded;
	    },
	
	    findBrowserName: function(userAgent) {
	        return this.findBrowser(userAgent).name;
	    }
	};
	
	let browserName = BrowserInfo.findBrowserName(userAgent);
	let msg = "당신의 브라우저는 browser입니다.".replace("browser", browserName)
	
	msgArea.innerText = msg;
});*/
// 회원 정보 클릭 이벤트 할당
document.addEventListener("DOMContentLoaded", (e)=>{
	// 모달창 요소 접근
	let modalelement = document.querySelector("#exampleModal");
	// 모달창 활성화 이벤트 : data-bs-toggle="modal" data-bs-target="#exampleModal" 를 이용하여 해당 요소 활성화 여부 판단
	modalelement.addEventListener("show.bs.modal", function(e){
		let modalElement = this;
		let tr = event.relatedTarget;
		let url = tr.dataset.url;
		let method = "get";
		let headers = {
			"accept" : "application/json"
		};
		let option = {
			method : method,
			headers : headers
		};
		
		fetch(url, option)
		.then(resp=>{
			return resp.json();
		})
		.then((data)=>{	
			
			let tdArr = document.querySelectorAll("td[id]");
			for(item of tdArr){
				if(data.who != null && !(data.who == "") && item.memId == data.who){
					//
				}
				let propName = item.id;
				item.innerHTML = data.member[propName];
			}
		})
		.catch(err=>{
			console.log(err);
		});
	});
	
	// 모달창 비활성화 이벤트 : data-bs-toggle="modal" data-bs-target="#exampleModal" 를 이용하여 해당 요소 닫기 여부 판단
	modalelement.addEventListener("hidden.bs.modal", function(e){
		let tdArr = document.querySelectorAll("td[id]");
		for(item of tdArr){
			let propName = item.id;
			item.innerHTML = "";
		}
	});
});
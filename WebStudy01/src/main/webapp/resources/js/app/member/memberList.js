// html loaded 함수 선언 방법 3가지
//$(document).on("ready", function(){ });
//$(document).ready(function(){ });
//$(function(){ });

$(function(){
	const $modal = $("#exampleModal").on("show.bs.modal", function(event){
		let modalElement = this;
		
		let tr = event.relatedTarget;
		let memId = $(tr).data("memId") // 데이터 빼낼때
		// body 안에 패스 설정 안쓰고 c:url 태그 사용하기 위해 주석처리함
		//const cPath = document.body.dataset.contextPath;
		//let url = `${cPath}/member/memberDetail.do`; 
		let url = tr.dataset.url;
		let method = "get";
		let headers = {
			"accept" : "application/json" // -> "json"
		};
		// 비동기 요청
		$.ajax({
			url:url
			,method:method
			,dataType: "json"
			,data:{
				who:memId
			}, success:function({member}, status, jqXHR){
				//console.log(member?.memId); // 옵셔널체이닝 ?앞에있는게 널이 아니면 ?후단 실행, 널이면 undefinde로 대체함
				$modal.find("td[id]").each(function(index, td){
					let propName = td.id; // 여기서 propName은 리털럴이다.
					td.innerHTML = member[propName]; // 리터럴로 찾으려면 연상배열구조 사용한다.
				});
			}, error:function(jqXHR, status, errorText){
				console.log(jqXHR, status, errorText);
			}
		});
	})
	.on("hidden.bs.modal", function(){
		$modal.find("td[id]").html(""); // td[] 의 내부 비우기
	});
	
	// 멤버 리스트의 클릭 이벤트 강제 실행 - trigger() 사용
	$("tr[data-mem-id].active").trigger("click");
});

// 이벤트 부여 방법
//$("tr[data-mem-id]").on("click", function(){ });
//$(document).on("click", "tr[data-mem-id]", function(){
	// 여기서 this는 무조건 HTML element 객체다
	// jquery 객체로 사용하려면 $(this) 이렇게 감싸줘야한다.(javascript객체->jquery객체)
	
	// javascript에서 data속성 접근, 추가 방법
	// this.dataset.memId 
	// this.dataset.newKey="value"; // 데이터 새로 넣을때
	
	// jquery에서 data속성 접근, 추가 방법
	// $(this).data("newKey", {}) // 제이쿼리의 데이터 펑션으로 객체 집어넣을때
	//$(this).data(k, v) // 데이터 넣을때
	//$(this).data(k) // 데이터 빼낼때
//});

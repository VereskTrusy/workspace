  var jQuery1x = jQuery.noConflict();
$(function (){
	
	// DB에서 조직 정보 가져오기
	jQuery1x.ajax({
		url:"/organization/jsonList",
		type:"post",
		dataType:"json",
		beforeSend: function(xhr) {
            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
        },
		success:function(result){
			//[{"userId":"NAVER_2023060009","userNm":"김윤아","userTelno":"01029949383","userEml":"2023060009@naver.com","userImg":"NULL","deptNo":1,"jbgdCd":"JG001","jbtlCd":"JT001"}
		 	const data = [];
		 	let nodes = result;
		 	
		 	// parent 기준으로 데이터 정리
		 	const parents = {};
		 	nodes.forEach(node => {
		 	  if (!parents[node.parent]) {
		 	    parents[node.parent] = [];
		 	  }
			  // idCard를 가져올 때 index가 아니라 id를 가져와야 함!!
		 	  parents[node.parent].push({name : node.child, id : node.id, jbgdCd : node.jbgdCd, userImg: node.img, deptNm : node.dept});
		 	});
			
		 	// jstree에서 사용할 수 있는 형태로 데이터 변환
		 	Object.keys(parents).forEach(parent => {
		 	  const children = parents[parent].map(child => {
		 	    return {text: child.name + " " + child.jbgdCd, type: "file", id : child.id, userImg : child.userImg, deptNm : child.deptNm};
		 	  });
		 	  data.push({
		 	    text: parent,
		 	    children: children
		 	  });
		 	});
		 	
		 	// ajax data 받아오기 
		 	jQuery1x('#jstree').jstree({
		 		'core': {
		 			"check_callback" : true,
		 			'data': data
		 			},
		 		"types" :{
		 			"default" : {
		 				"icon" : "fa-solid fa-folder"
		 			},
		 			"file" : {
		 				"icon" : "fa-solid fa-address-card"
		 			}
		 		},	
		 		"plugins" : ["types", "dnd", "search"]
		 		
		 		
		 		//children node 클릭했을때 hidden 처리 해제
		 	}).on('select_node.jstree', function(event, data) {
		 		
				var selectedNode = data.node;
				// 자식 노드(사원 정보)일 경우 이벤트 실행
				if(selectedNode.parent != '#') {
					//중복 실행 방지
					$("#draftmy1").off().on('click', function() {
						lineEdit(selectedNode);
					})
					
					$("#draftmy2").off().on('click', function() {
						susinEdit(selectedNode);
					})
					
					$("#draftmy3").off().on('click', function() {
						ramEdit(selectedNode);
					})
					
							
					//취소 버튼
					$("#lineClose").off().on("click", function() {
						$(".draft-line, .draft-susin, .draft-ram").remove();
						$(".draft-div").removeClass("d-none");
					
					});

					// 확인 버튼
					$("#lineSave").off().on("click", function() {
						// 확정이 난 데이터에서 수정할 데이터라는 뜻을 가진 클래스를 지워줌
						$(".draft-line, .draft-susin, .draft-ram").removeClass("draft-line draft-susin draft-ram");
						// 삭제버튼을 눌러 d-none 처리했던 태그들을 지워줌
						$(".draft-div.d-none, .draft-div.d-none, .draft-div.d-none").remove();
						
						// 확인 누르면 기존에 등록된 html을 초기화
						$("#draftLineDisplay, #draftSusinDisplay, #draftRamDisplay").html("");
						
						$(".signBox").remove();
					
						//추가로 생성됬던 태그들을 결재선 설정 밖으로 보내줌
						$("#lineSpace > .alert").each(function(index, value) {
							changeDisplay(index, value);
								
						});
						
						// 수신자 처리
						$("#susin > .alert").each(function(index, value) {
							chageDisplay2(index, value);
						});
						
						// 회람자 처리
						$("#ram > .alert").each(function(index, value) {
							chageDisplay3(index, value);
						});
						
						
					})
				}
		 	});
			 	
		}
		 	
	});
	var lineIndexNum = 0;
	function chageDisplay2(index, value) {
		var content = value.cloneNode(true);
		
		var draftLineContent = content.querySelector('.card-body > .d-flex')
		content.classList.remove('kanban-item');
		content.classList.add('col');
		
		var btnDelDraft = content.querySelector('.draft-del-btn');
		btnDelDraft.remove();
		
	// form에 전달할 userId 생성
		var atrzUserId = content.querySelector('#lineId').value;
		var inputHiddenId = document.createElement('input');
		inputHiddenId.setAttribute('type', 'hidden');
		inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].userId');
		inputHiddenId.setAttribute('value', atrzUserId);
		
		draftLineContent.prepend(inputHiddenId);
	// form에 전달할 userId 생성
	
	// form에 전달할 atrzSn 생성
		var inputHiddenId = document.createElement('input');
		inputHiddenId.setAttribute('type', 'hidden');
		inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].atrzSn');
		inputHiddenId.setAttribute('value', 0);
		
		draftLineContent.prepend(inputHiddenId);
	// form에 전달할 atrzSn 생성
	
	// form에 전달할 atrzCd 생성
		var inputHiddenId = document.createElement('input');
		inputHiddenId.setAttribute('type', 'hidden');
		inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].atrzCd');
		inputHiddenId.setAttribute('value', 'DR002');
		
		draftLineContent.prepend(inputHiddenId);
		
		lineIndexNum++;
	// form에 전달할 atrzCd 생성
		
		$("#draftSusinDisplay").append(content);
	}
	
	function chageDisplay3(index, value) {
		var content = value.cloneNode(true);
		
		var draftLineContent = content.querySelector('.card-body > .d-flex')
		content.classList.remove('kanban-item');
		content.classList.add('col');
		
		var btnDelDraft = content.querySelector('.draft-del-btn');
		btnDelDraft.remove();
		
	// form에 전달할 userId 생성
		var atrzUserId = content.querySelector('#lineId').value;
		var inputHiddenId = document.createElement('input');
		inputHiddenId.setAttribute('type', 'hidden');
		inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].userId');
		inputHiddenId.setAttribute('value', atrzUserId);
		
		draftLineContent.prepend(inputHiddenId);
	// form에 전달할 userId 생성
	
	// form에 전달할 atrzSn 생성
		var inputHiddenId = document.createElement('input');
		inputHiddenId.setAttribute('type', 'hidden');
		inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].atrzSn');
		inputHiddenId.setAttribute('value', 0);
		
		draftLineContent.prepend(inputHiddenId);

	// form에 전달할 atrzSn 생성
	
	// form에 전달할 atrzCd 생성
		var inputHiddenId = document.createElement('input');
		inputHiddenId.setAttribute('type', 'hidden');
		inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].atrzCd');
		inputHiddenId.setAttribute('value', 'DR003');
		
		draftLineContent.prepend(inputHiddenId);
	// form에 전달할 atrzCd 생성
		lineIndexNum++;
		$("#draftRamDisplay").append(content);
	}
	
	// 결제선 설정 모달 밖으로 결제선 생성 
	function changeDisplay(index, value){
		var content = value.cloneNode(true);
		// 결재 라인 내용 태그
			var draftLineContent = content.querySelector('.card-body > .d-flex')
			content.classList.remove('kanban-item');
		// 기안 순서 번호 넣기
			var seqCount = document.createElement('div');
			seqCount.classList.add('align-middle', 'mx-auto');
			seqCount.textContent = (index + 2).toString();
			
			draftLineContent.prepend(seqCount);
		// 기안 순서 번호 넣기	
		
		// form에 전달할 userId 생성
			var atrzUserId = content.querySelector('#lineId').value;
			var inputHiddenId = document.createElement('input');
			inputHiddenId.setAttribute('type', 'hidden');
			inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].userId');
			inputHiddenId.setAttribute('value', atrzUserId);
			
			draftLineContent.prepend(inputHiddenId);
		// form에 전달할 userId 생성
		
		// form에 전달할 atrzSn 생성
			var inputHiddenId = document.createElement('input');
			inputHiddenId.setAttribute('type', 'hidden');
			inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].atrzSn');
			inputHiddenId.setAttribute('value', index + 1);
			
			draftLineContent.prepend(inputHiddenId);
		// form에 전달할 atrzSn 생성
		
		// form에 전달할 atrzCd 생성
			var inputHiddenId = document.createElement('input');
			inputHiddenId.setAttribute('type', 'hidden');
			inputHiddenId.setAttribute('name', 'draftAtrzVOList[' + lineIndexNum + '].atrzCd');
			inputHiddenId.setAttribute('value', 'DR001');
			
			draftLineContent.prepend(inputHiddenId);
			lineIndexNum++;
		// form에 전달할 atrzCd 생성
		
		// 삭제 버튼(X) 지우고 해당 자리에 뱃지 넣기
			var btnDelDraft = content.querySelector('.draft-del-btn');
			btnDelDraft.remove();
			
			var draftBadge = document.createElement('div');
			draftBadge.classList.add('mx-auto');
			draftBadge.innerHTML = '<span class="badge rounded-pill badge-subtle-primary">결재자</span>';

			draftLineContent.append(draftBadge);
		// 삭제 버튼(X) 지우기 해당 자리에 뱃지 넣기
		
		
		// 수정 권한 체크
			var isMody = content.querySelector('#isMody');
			var isModyFather = isMody.closest(".mx-auto");
			if(isMody.checked) {
				isModyFather.remove();
				
				var modiOn = document.createElement('div');
				modiOn.classList.add('mx-auto'); 
				modiOn.innerHTML = '<span class="fas fa-pen"></span>'; 
				
				draftLineContent.append(modiOn);
			} else {
				isModyFather.remove();
				var modiOn = document.createElement('div');
				
				modiOn.classList.add('mx-auto', 'invisible'); 
				modiOn.innerHTML = '<span class="fas fa-pen"></span>'; 
				draftLineContent.append(modiOn);
			}
		// 수정 권한 체크
			
			$("#draftLineDisplay").append(content);
		
		// 결재 사인 박스 생성
			var myttemp = content.querySelectorAll(".mx-auto");
			
			var userNm = myttemp[2].textContent // 이름
			var userjbgd = myttemp[4].textContent // 직급
			
			var signBox = document.querySelector("#signBox");
			var tempSignBox = document.createElement('div');
			tempSignBox.classList.add('border-400', 'signBox');
			tempSignBox.innerHTML = `
				<table class="table text-center" border="1">
					<thead>
						<tr>
							<th class="p-1" scope="col">` + userjbgd + `</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="p-1" style="height: 5rem;">
							<img alt="서명이미지" src="/resources/image/user/sign/nosign.png" 
								style="width: 71px; height: 71px"></td>
						</tr>
						<tr>
							<td class="p-1">`+ userNm + `</td>
						</tr>
						<tr>
							<td class="p-1" style="font-size: 12px; font-weight: bold">날짜</td>
						</tr>
					</tbody>
				</table>`;
				

			signBox.append(tempSignBox);  // 요소를 추가
	}
	
	
	function lineEdit(selectedNode) {
		var userNm = selectedNode.text.split(" ")[0];
		var jbgdNm = selectedNode.text.split(" ")[1];
		var userId = selectedNode.id;
		var userImg = selectedNode.original.userImg;
		var deptNm = selectedNode.original.deptNm;
		var line =
				`<div class="alert card mb-3 kanban-item shadow-sm dark__bg-1100 p-0 draft-div draft-line" style="border: 0px">
					<div class="card-body border p-2">
						<div class="d-flex align-items-center fs--1 fw-medium font-sans-serif mb-0">
							<div class=" mx-auto">` + `<img alt="유저사진" class="rounded-circle my-img-size" src="` +  userImg + `"></div>
							<input type="hidden" id="lineId" value="` + userId + `" />
							<div class=" mx-auto">` + userNm + `</div>
							<div class=" mx-auto">` + deptNm + `</div>
							<div class=" mx-auto">` + jbgdNm + `</div>
							<div class=" mx-auto">
								<input class="form-check-input" id="isMody"
									type="checkbox" value="" />
							</div>
							<button class="btn-close close-card draft-del-btn" type="button"></button>
						</div>
					</div>
				</div>`
			$("#lineSpace").append(line);
			
	}

	function susinEdit(selectedNode) {
		var userNm = selectedNode.text.split(" ")[0];
		var jbgdNm = selectedNode.text.split(" ")[1];
		var userId = selectedNode.id;
		var userImg = selectedNode.original.userImg;
		var deptNm = selectedNode.original.deptNm;
		
		var line =
				`<div class="alert card mb-3 kanban-item shadow-sm dark__bg-1100 p-0 draft-div draft-susin" style="border: 0px">
					<div class="card-body border p-2">
						<div class="d-flex align-items-center fs--1 fw-medium font-sans-serif mb-0">
							<input type="hidden" id="lineId" value="` + userId + `" />
							<div class=" mx-auto">` + userNm + `</div>
							<div class=" mx-auto">` + jbgdNm + `</div>
							<button class="btn-close close-card draft-del-btn" type="button"></button>
						</div>
					</div>
				</div>`
			$("#susin").append(line);
	}
	
	function ramEdit(selectedNode) {
		var userNm = selectedNode.text.split(" ")[0];
		var jbgdNm = selectedNode.text.split(" ")[1];
		var userId = selectedNode.id;
		var userImg = selectedNode.original.userImg;
		var deptNm = selectedNode.original.deptNm;
		
		var line =
				`<div class="alert card mb-3 kanban-item shadow-sm dark__bg-1100 p-0 draft-div draft-ram" style="border: 0px">
					<div class="card-body border p-2">
						<div class="d-flex align-items-center fs--1 fw-medium font-sans-serif mb-0">
							<input type="hidden" id="lineId" value="` + userId + `" />
							<div class=" mx-auto">` + userNm + `</div>
							<div class=" mx-auto">` + jbgdNm + `</div>
							<button class="btn-close close-card draft-del-btn" type="button"></button>
						</div>
					</div>
				</div>`
			$("#ram").append(line);
	}
		
		 	
	
		
	 	// 조직도 내 검색 기능
	 	$('#search').on('keyup', function () {
	 	    var searchString = $(this).val();
	 	   jQuery1x('#jstree').jstree(true).search(searchString);
	 	    
	 	});
	 	
	 	//삭제 버튼(x버튼) 클릭 시 요소를 삭제하는것이 아닌 안보이게 바꿈
 		$("#draftAllLineSpace").on("click", ".draft-del-btn", function() {
 		    $(this).closest(".alert").addClass('d-none');
 		  });
		 	
		 	
			
			
});

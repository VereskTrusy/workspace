
	// 데이터 검증 및 처리 스크립트
	// 오늘 날짜 계산
	var today = new Date();
	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2);

	var formattedDate = year + '-' + month + '-' + day;
	var formattedDate2 = year + '' + month + '' + day;
	
	document.querySelector("#sysdate").setAttribute("value", formattedDate);
	document.querySelector(".sysdate").innerText = formattedDate;
	
	//제목 자동 설정
	document.querySelector("#draftTitle").innerText = $("#draftFormTitle")[0].innerText + "-" + $("#colFormLabel3")[0].value+ "-" + formattedDate2;
	
	// 오늘 날짜 계산
	
	//휴가 기간 오류 체크
	var vacDate = document.querySelectorAll(".datetimepicker");
	
	$(document).ready(function() {
		
		tinymce.init({
		    selector: "#draftClobContent",
		});
		
		$("#getApproval").on('click', function() {
			
			$("#drSttsCd")[0].value = 'DF003';
			
			var startDate = $("#datepicker");
			var endDate = $("#datepicker2");
			startDate.removeClass('is-valid');
			endDate.removeClass('is-valid');
			
			var docHtml = document.querySelector("#doc");
			var copyDocHtml = docHtml.cloneNode(true);
			
			// 기안일 ex) 2023-07-19
			var sysdate = copyDocHtml.querySelector("#sysdate")
			//휴가 종류 선택 ex)오후 반차(남은 휴가:1.0일)
			var select = copyDocHtml.querySelector("#vacChose");
			// 모달 가져온 휴가 종류 목록 중 선택된 휴가
			var vacModalChecked = copyDocHtml.querySelector('input[name="flexRadioDefault"]:checked');
			// 휴가 설정 모달에서의 선택된 휴가의 휴가코드명
			var codeName = vacModalChecked.getAttribute('code-name');

			tinymce.get('draftClobContent').setContent(copyDocHtml.outerHTML);
			
			
			frm1.submit();
			
		});
		
		$("#getImsi").on('click', function() {
			
			$("#drSttsCd")[0].value = 'DF001';
			
			var startDate = $("#datepicker");
			var endDate = $("#datepicker2");
			startDate.removeClass('is-valid');
			endDate.removeClass('is-valid');
					
			var docHtml = document.querySelector("#doc");
			var copyDocHtml = docHtml.cloneNode(true);
			
			tinymce.get('draftClobContent').setContent(copyDocHtml.outerHTML);
			
			
			frm1.submit();
			
		});
		
		
		var vacElements = document.querySelectorAll('.vac');
		vacElements.forEach(function(element) {
		  element.disabled = true;
		});
		
		
		var select = document.querySelector("#vacChose");
		
		
		// 날짜 계산 함수
		function dateCale(startDate, endDate) {
			if(typeof startDate == 'string') {
				startDate = new Date(startDate);
			}
			if(typeof endDate == 'string') {
				endDate = new Date(endDate);
			}
			var dateResult = endDate - startDate;
			var dateCount = Math.floor(dateResult / (1000 * 60 * 60 * 24));
			
			// 당일 포함 계산 처리
			if(dateCount >= 0) {
				dateCount = dateCount + 1;
			}
			return dateCount;
		};
		
		
		const alertPlaceholder = document.getElementById('liveAlertPlaceholder');

		// 날짜 틀렸을때 보여줄 알람창
		const alert = () => {
			alertPlaceholder.innerHTML = [
		    '<div class="alert alert-danger border-2 d-flex align-items-center" role="alert">',
		    '	<div class="bg-danger me-3 icon-item"><span class="fas fa-times-circle text-white fs-3"></span></div>',
		    '   <p class="mb-0 flex-1">시작일은 종료일 이후일 수 없습니다.</p>',
		    '   <button type="button" class="btn-close"  data-bs-dismiss="alert" aria-label="Close"></button>',
		    '</div>'
		  ].join('');
			
			
		    setTimeout(() => {
		        alertPlaceholder.innerHTML = '';
		      }, 3000);
		}
		
		const alert2 = () => {
			alertPlaceholder.innerHTML = [
		    '<div class="alert alert-danger border-2 d-flex align-items-center" role="alert">',
		    '	<div class="bg-danger me-3 icon-item"><span class="fas fa-times-circle text-white fs-3"></span></div>',
		    '   <p class="mb-0 flex-1">가지고있는 휴가보다 많은 휴가를 선택하셨습니다.</p>',
		    '   <button type="button" class="btn-close"  data-bs-dismiss="alert" aria-label="Close"></button>',
		    '</div>'
		  ].join('');
			
		    setTimeout(() => {
		        alertPlaceholder.innerHTML = '';
		      }, 3000);
		}
		
		
		const alert3 = () => {
			alertPlaceholder.innerHTML = [
		    '<div class="alert alert-warning border-2 d-flex align-items-center" role="alert">',
		    '	<div class="bg-warning me-3 icon-item"><span class="fas fa-exclamation-circle text-white fs-3"></span></div>',
		    '   <p class="mb-0 flex-1">반차는 하루만 선택할 수 있습니다.</p>',
		    '   <button type="button" class="btn-close"  data-bs-dismiss="alert" aria-label="Close"></button>',
		    '</div>'
		  ].join('');
			
		    setTimeout(() => {
		        alertPlaceholder.innerHTML = '';
		      }, 3000);
		}
	//휴가 기간 오류 체크
	
	
	// 휴가 종류 선택 모달에서 확인 버튼 클릭
		$('#vacSetBtn').on("click", function() {
			var selectedRadio = document.querySelector('input[name="flexRadioDefault"]:checked');
			if(selectedRadio != null) {
				clearVac();
				// 체크된 휴가 사용 목록 항목의 코드(ex: AU001)
				var selectedValue = selectedRadio.value;
				
				
				
				// 코드의 이름
				var codeName = selectedRadio.getAttribute('code-name');
				// 선택한 휴가 종류의 남은 기간
				var remainVac = selectedRadio.getAttribute('remain-vac');
				// 사용할 휴가의 부여받은 관리 번호
				var vacGrntNo = selectedRadio.getAttribute('vac-grnt-no');
				
				$("#vacGrntNo").val(vacGrntNo);
				
				select.value = codeName + " (남은휴가:" + remainVac + "일)";
				select.setAttribute("value", codeName + " (남은휴가:" + remainVac + "일)");
				
				select.setAttribute('vac-code', selectedValue);
				$("#vacTp")[0].value = selectedValue;
				$("#vacTp")[0].setAttribute("value", selectedValue);
				
				select.setAttribute('remain-vac', remainVac);
				
				// 잠금해제
				vacElements.forEach(function(element) {
					  element.disabled = false;
					});
				
				
				var picker = flatpickr(".datetimepicker", {
				  // 옵션 설정
				  onChange: function() {
						if((vacDate[0].value != "") && (vacDate[1].value != "")){
							dateMissMatch(codeName);
						}
				  }
				});
				
				var vacReason = $("#vacReason");
				vacReason.off().on("change", function() {
					vacReason.attr("value", vacReason.val());
				})
			}
		});
	
		
		
		// 휴가기간 선택창들이 변경될때 이벤트 발생
		
	
		// 휴가 유효성 검증 함수
		function dateMissMatch(codeName) {
			// 휴가 시작 날짜
			var startDate = $("#datepicker");
			startDate.attr("value", startDate.val());
			// 휴가 종료 날짜
			var endDate = $("#datepicker2");
			endDate.attr("value", endDate.val());
			// 휴가 사용할 날짜
			var vacDayCount = $("#vacDayCount");
			
			
			// 사용자가 사용 가능한 휴가 일 수
			var canUseDayCount = select.getAttribute('remain-vac');
			
			// 선택날짜가 시작날짜보다 작을경우 틀렸다는 알람과 input 태그 클래스 변경
			if(!codeName.includes('반차')) {
				
				if(vacDate[1].value < vacDate[0].value) {
					alert();
					startDate.addClass('is-invalid');
					endDate.addClass('is-invalid');
					startDate.removeClass('is-valid');
					endDate.removeClass('is-valid');
					
					// 정상적인 날짜 선택이라면
				} else {
					startDate.addClass('is-valid');
					endDate.addClass('is-valid');
					startDate.removeClass('is-invalid');
					endDate.removeClass('is-invalid');
					
					// 연차 휴가 사용 시
					// 사용 날짜 계산
					var dateCount = dateCale(vacDate[0].value, vacDate[1].value);
					
					// 사용 날짜 계산
					vacDayCount[0].value = dateCount;
					vacDayCount[0].setAttribute("value", dateCount);
					
					vacDayCount[0].name = "draftVacVO.cnyDay";
					$("#vacTimeCount2")[0].name = "";
					
					// 가지고있는 휴가보다 선택된 휴가가 더 길 경우
					if(Number(vacDayCount[0].value) > Number(canUseDayCount)) {
						// 검증 실패 클래스 부여
						endDate.addClass('is-invalid');
						// 잘못된 휴가 일 수 선택 알람
						alert2();
					}
				}
				
				// 반차라면
			} else {
				
				if(vacDate[1].value != vacDate[0].value) {
					endDate[0].value = startDate[0].value;
					endDate[0].setAttribute('value', startDate[0].value);
					alert3();
					dateMissMatch(codeName)
					// 정상적인 날짜 선택이라면
				} else if(vacDate[1].value == vacDate[0].value) {
					startDate.addClass('is-valid');
					endDate.addClass('is-valid');
					startDate.removeClass('is-invalid');
					endDate.removeClass('is-invalid');
					
					// 사용 날짜 계산
					$("#vacTimeCount")[0].value = 4;
					$("#vacTimeCount")[0].setAttribute('value', 4);
					$("#vacTimeCount2")[0].name = "draftVacVO.cnyDay";
					$("#vacDayCount")[0].name = "";
					
					// 가지고있는 휴가보다 선택된 휴가가 더 길 경우
					if(Number(vacDayCount[0].value) > Number(canUseDayCount)) {
						// 검증 실패 클래스 부여
						endDate.addClass('is-invalid');
						// 잘못된 휴가 일 수 선택 알람
						alert2();
					}
				}
			}
			
		};
	
// 	휴가 종류 선택 모달에서 확인 버튼 클릭
	
	// 휴가 내용 지우기 버튼
		$('#delVac').on("click", function() {
			clearVac();
		});
	// 내용 지우기 버튼
	
		function clearVac() {
			$("#datepicker")[0].value = "";
			$("#datepicker")[0].setAttribute('value', "");
			
			$("#datepicker2")[0].value = "";
			$("#datepicker2")[0].setAttribute('value', "");
			
			$("#vacDayCount")[0].value = "";
			$("#vacDayCount")[0].setAttribute('value', "");
			
			$("#vacTimeCount")[0].value = "";
			$("#vacTimeCount")[0].setAttribute('value', "");
			
			$("#vacReason")[0].value = "";
			$("#vacReason")[0].setAttribute('value', "");
			
			var startDate = $("#datepicker");
			var endDate = $("#datepicker2");
			
			startDate.removeClass('is-valid');
			endDate.removeClass('is-valid');
			startDate.removeClass('is-invalid');
			endDate.removeClass('is-invalid');
			
			select.setAttribute('vac-code', "");
			select.setAttribute('remain-vac', "");
			select.value ="";
			select.setAttribute("value", "");
		}
		
		var count = 0;
		
		
	// 기안 의견 데이터 전달
	    $("#draftRegistOpnion").keyup(function(event) {
	        if (event.which === 13) {
	            $("#setOpnionBtn").click();
	        }
	    });
	    
		$("#setOpnionBtn").on("click", function() {
			var draftShowOpinion = document.querySelector("#draftShowOpinion");
			var draftRegistOpnion = document.querySelector("#draftRegistOpnion");
			
			var opinion = draftRegistOpnion.value;
			
			if(document.getElementById("opZero")){
				$("#opZero").remove();
			}
			var nowTime = new Date();
			
			var opinionTemp = `<input class="form-control-plaintext" name="draftOpnVOList[` + count + `].opnnCn" readonly value="${draftUserVO.userNm}: ` + opinion +  `"/>`
			opinionTemp += `<input type="hidden" name="draftOpnVOList[` + count + `].userId" value="${draftUserVO.userId}"/>`
			opinionTemp += `<input type="hidden" name="draftOpnVOList[` + count + `].drOpnnDt" value="`+ nowTime +`"/>`
			count++;
			draftShowOpinion.innerHTML += opinionTemp;
			
			draftRegistOpnion.value = "";
		});
	// 기안 의견 데이터 전달
	
	});

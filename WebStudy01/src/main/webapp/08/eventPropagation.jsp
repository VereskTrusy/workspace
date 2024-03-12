<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.top{
		border: 3px solid black;
		width: 400px;
		height: 400px;
		text-align: center;
	}
	.middle{
		border: 3px solid red;
		width: 200px;
		height: 200px;
	}
	:focus {
		border : 3px solid blue;
	}
</style>
</head>
<body>
<pre>
	DOM 트리구조의 노드로 구성되는 HTML 엘리먼트, 대상으로 발생하는 이벤트
	해당 엘리먼트의 부모나 자식 노드쪽으로 이벤트가 전파(propagation)되는 구조를 가진다.
	bubbling propagation : 자식 노드에서 부모 노드쪽으로 전파되는 구조.
	capturing propagation : 부모 노드에서 자식 노드쪽으로 전파되는 구조.
</pre>
<div id="bubbleTop" class="top bubbling" tabindex="-1"> <!-- tabindex=""를 할당해주면 그때부터 포커스를 얻을 수 있다. -->
	<div id="bubbleMiddle" class="middle bubbling" tabindex="-1">
		<button id="bubbleBottom" class="bubbling" tabindex="-1">최하위 자식버튼- Event Bubbling</button>
	</div>
</div>
<div id="captureTop" class="top capturing" tabindex="1">
	<div id="captureMiddle" class="middle capturing" tabindex="2">
		<button id="captureBottom" class="capturing" tabindex="3">최하위 자식버튼- Event Capturing</button>
	</div>
</div>
<script type="text/javascript">
// bubbling 전파구조
document.querySelectorAll(".bubbling").forEach((be)=>{ // 엘리먼트 중 하나를 be로 받는다
	be.addEventListener("click", (e)=>{
		console.log("click bubbling to ", be.id, " target : ", e.target.id);
		//e.preventDefault(); // 이벤트를 중단시키는게 아니라, 클릭/체인지 등
		//e.stopPropagation();
	});
	
	be.addEventListener("focus", (e)=>{ //  포커스 이벤트는 전파되지 않는다.
		console.log("focus bubbling to ", be.id, " target : ", e.target.id);
		//e.preventDefault(); // 이벤트를 중단시키는게 아니라, 클릭/체인지 등
		//e.stopPropagation();
	});
});

// capturing 전파구조
document.querySelectorAll(".capturing").forEach((ce)=>{ // 엘리먼트 중 하나를 ce로 받는다
	ce.addEventListener("click", (e)=>{
		console.log("click capturing from ", ce.id, " target : ", e.target.id);
		//e.stopPropagation();
	}, true); // 세번째 파라미터가 전파구조를 설정 가능함
	
	ce.addEventListener("focus", (e)=>{
		console.log("focus capturing from ", ce.id, " target : ", e.target.id);
		//e.stopPropagation();
	}, true);
});
</script>
</body>
</html>









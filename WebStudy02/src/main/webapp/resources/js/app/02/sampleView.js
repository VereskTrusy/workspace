// 1. 비동기 요청 발생
// 2. 1초 마다 한번씩
// 3. 받아온 시간을 서버의 시간에 반영
setInterval(function(){
	clientArea.innerHTML = Date.now(); // 클라이언트의 현재시간 찍기 
	
	// 비동기(fetch사용)로 서버의 현재시간 찍기
    fetch("../now") // 왜 url을 이렇게 지정햇더라??
    .then(resp => resp.text())
    .then(n => serverArea.innerHTML = n);
}, 1000);


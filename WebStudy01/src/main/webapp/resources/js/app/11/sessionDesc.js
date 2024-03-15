const SPEED = 100;
const contextPath = document.body.dataset.contextPath;

// 클래스로 변환하기.
class SessionTimer2{
	constructor(timeout, element){
		this.timeout = timeout;
		this.element = element;
		this.element.sessinTimer = this;
		this.init();
	}
	
	createMessageArea(){
		let source = `
			<div style='display:none;'>
			<button type="button" class="controll" data-ts-state="true">예</button>
			<button type="button" class="controll">아니오</button>
			</div>
		`;
		this.msgArea = new DOMParser().parseFromString(source, 'text/html').body.children[0];
		this.msgArea.addEventListener("click",function(e){
			if(e.target.dataset.tsState){
				this.reset();
			}
			this.msgArea.style.display = "none";
		}.bind(this));
		this.element.after(this.msgArea);
	}
	
	init(){
		fetch("", {method:"head"});
		this.timer = this.timeout;
		this.element.innerHTML = this.timeout;
		this.createMessageArea();
		let obj = this;
		this.timeoutId = setTimeout(function(){
			obj.msgArea.style.display = "block";
		}, (this.timeout-60)*SPEED);
		
		this.intervalId = setInterval(function(){
			if(obj.timer > 0){
				obj.timer--;
			}else{
				obj.destroy();
			}
			obj.element.innerHTML = obj.timer;
		}, SPEED);
	}
	
	destroy(){
		clearInterval(this.intervalId);
		clearTimeout(this.timeoutId);
		//document.body.removeChild(this.msgArea);
		this.msgArea.remove();
	}
	
	reset(){
		this.destroy();
		this.init();
	}
}

// ...
const SessionTimer = function(timeout, element){
	this.timeout = timeout;
	this.element = element;
	this.element.sessinTimer = this;
	
	this.createMessageArea = function(){
		let source = `
			<div style='display:none;'>
			<button type="button" class="controll" data-ts-state="true">예</button>
			<button type="button" class="controll">아니오</button>
			</div>
		`;
		this.msgArea = new DOMParser().parseFromString(source, 'text/html').body.children[0];
		this.msgArea.addEventListener("click",function(e){
			if(e.target.after(this.tsState)){
				this.reset();
			}
			this.msgArea.style.disply = "none";
		}.bind(this));
		this.element.after(this.msgArea);
	}
	
	this.init = function(){
		fetch("", {method:"head"});
		this.timer = this.timeout;
		this.element.innerHTML = this.timeout;
		this.createMessageArea();
		let obj = this;
		this.timeoutId = setTimeout(function(){
			obj.msgArea.style.display = "block";
		}, (this.timeout-60)*SPEED);
		
		this.intervalId = setInterval(function(){
			if(obj.timer > 0){
				obj.timer--;
			}else{
				obj.destroy();
			}
			obj.element.innerHTML = obj.timer;
		}, SPEED);
	}
	
	this.destroy = function(){
		clearInterval(this.intervalId);
		clearTimeout(this.timeoutId);
		//document.body.removeChild(this.msgArea);
		this.msgArea.remove();
	}
	
	this.reset = function(){
		this.destroy();
		this.init();
		// 
	}
	
	this.init();
	
}

document.querySelectorAll("[data-ts-timeout]").forEach(element=>{
	let timeout = element.dataset['tsTimeout'];
	console.log(timeout);
	new SessionTimer2(timeout, element);
});

stopBtn.addEventListener("click", ()=>{
	document.querySelectorAll("[data-ts-timeout]").forEach(element=>{
		element.sessinTimer.destroy();
	});
});
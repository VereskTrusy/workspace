/**
 * 

Date
생성자 Date()
정적 메서드
Date.now() 현재 밀리초.
Date.prototype.getDate() Date에서 현지 시간 기준 일(1–31)을 반환합니다.
Date.prototype.getDay() Date에서 현지 시간 기준 요일(0–6)을 반환합니다.
Date.prototype.getFullYear() Date에서 현지 시간 기준 연도(네 자리 연도면 네 자리로)를 반환합니다.
Date.prototype.getMonth() Date에서 현지 시간 기준 월(0–11)을 반환합니다.
Date.prototype.setDate()  현지 시간 기준으로 일을 설정합니다.
Date.prototype.setFullYear() 현지 시간 기준으로 연도(네 자리 연도면 네 자리로)를 설정합니다.
Date.prototype.setMonth() 현지 시간 기준으로 월을 설정합니다.
Date.prototype.setTime() Date가 나타낼 시간을 1970년 1월 1일 00:00:00 UTC로부터의 경과시간(밀리초)으로 설정합니다. 기준 이전의 시간은 음수 값을 사용해 설정할 수 있습니다.

 */


let today = new Date();
//console.log(today);

let todayYoil = Date.prototype.getDay();
console.log(todayYoil);
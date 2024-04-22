package kr.or.ddit.case09;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.case07.vo.BankInfoVO;
import lombok.extern.slf4j.Slf4j;

// 폼을 사용하지 않은 비동기요청에서 사용한다.

@Slf4j
@RestController
@RequestMapping(
	value = "/case09"
	, consumes = MediaType.APPLICATION_JSON_VALUE //
	, produces = MediaType.APPLICATION_JSON_VALUE //
)
public class RESTJsonUnmasharllingController {
	
	@PostMapping("jsonPayload3")
	public Map<String, Object> handler3(@RequestBody BankInfoVO bank) {
		log.info("{} 등록 실패", bank);
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("success", false);
		result.put("message", "실패 원인에 해당하는 메시지");
		result.put("target", bank);
		
		return result;
	}
	
	
	
	
	
	@PostMapping("jsonPayload2")
	public boolean handler2(@RequestBody BankInfoVO bank) {
		log.info("{} 등록 성공", bank);
		return true;
	}
	
	
	@PostMapping("jsonPayload1")
	public BankInfoVO handler1(@RequestBody BankInfoVO bank) {
		// 날짜없는 뱅크인포 받아서
		// 날짜있는 뱅크인포 넘겨줘보기
		bank.setBankDate(LocalDate.now());
		return bank;
	}
}

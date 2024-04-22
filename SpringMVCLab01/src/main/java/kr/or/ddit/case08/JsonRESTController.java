package kr.or.ddit.case08;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.case07.vo.BankInfoVO;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/case08")
public class JsonRESTController {
	
	
	@RequestMapping("rest2")
	public String handler2() {
		return "jsonView";
	}
	// "jsonView"
	

	@RequestMapping("rest1")
	public BankInfoVO handler1() {
		return bank();
	}
//	{
//	    "bankNo": "1-11-1111-11",
//	    "bankName": "하나은행",
//	    "bankUserName": null,
//	    "bankDate": "2024-04-19"
//	}
	
	
	private BankInfoVO bank() {
		BankInfoVO target = new BankInfoVO();
		target.setBankName("하나은행");
		target.setBankNo("1-11-1111-11");
		target.setBankDate(LocalDate.now());
		return target;
	}
}

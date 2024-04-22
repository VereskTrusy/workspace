package kr.or.ddit.case08;

import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.or.ddit.case07.vo.BankInfoVO;

@Controller
@RequestMapping(value="/case08", produces = MediaType.APPLICATION_JSON_VALUE)//json으로 받기
public class JsonMarshallingController {

	
	// 직접 오브젝트 매퍼 마샬링 해보기
	@RequestMapping("json3")
	@ResponseBody
	public String handler3() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());;
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String json = mapper.writeValueAsString(bank());
		return json;
	}


	
	@RequestMapping("json2")
	@ResponseBody // BankInfoVO 가 문자화 되어 responsebody에 들어간다. -> @ResponseBody 어노테이션을 사용하면 스프링이 알아서 마샬링 해준다.
	public BankInfoVO handler2() { // BankInfoVO의 모델만 전달 -> 별도의 뷰를 사용하지 말고 서비스만 사용
		return bank();
	}

	
	@GetMapping("json1")
	public String handler1(Model model) {
		model.addAttribute("target", bank());
		
		return "jsonView"; //jsonview는 outter객체를 만들어준다.
	}

	
	

	private BankInfoVO bank() {
		BankInfoVO target = new BankInfoVO();
		target.setBankName("하나은행");
		target.setBankNo("1-11-1111-11");
		target.setBankDate(LocalDate.now());
		return target;
	}
}

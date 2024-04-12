package kr.or.ddit.misison;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/mission/case01")
public class Mission1Controller {

	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void missionGet() {
		log.info("/mission/case01 의 GET 요청 작동");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void missionPost() {
		log.info("/mission/case01 의 POST 요청 작동");
	}
}

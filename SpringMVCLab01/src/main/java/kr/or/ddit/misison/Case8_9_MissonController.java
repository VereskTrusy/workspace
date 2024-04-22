package kr.or.ddit.misison;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.case09.ClaculateVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 
 */
@Slf4j
@RestController
@RequestMapping(
	value = "/mission/case08_9"
)
public class Case8_9_MissonController {
	
	@PostMapping("operator")
	public ClaculateVO handler1(@RequestBody ClaculateVO calVO) {
		
		
		return calVO;
	}
}

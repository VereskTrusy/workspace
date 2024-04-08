package kr.or.ddit.case4;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case4.bts.service.BtsService;
import kr.or.ddit.vo.BtsVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class Case4Playground {
	private BtsService service;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case4/conf/Case4-Context.xml");
		
		Case4Playground controller = context.getBean("case4Playground", Case4Playground.class);
		List<BtsVO> list = controller.service.readBtsList();
		
		
//		BtsService service = context.getBean("btsService", BtsService.class);
//		List<BtsVO> list = service.readBtsList();
		log.info("BTS LIST : {}", list);
	}	
}

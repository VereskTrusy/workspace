package kr.or.ddit.draft.mapper;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.draft.vo.DraftSttsCd;
import kr.or.ddit.draft.vo.DraftVO;

public interface DarftMapperTest {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"test-context.xml"});

		DraftMapper draftMapper = (DraftMapper)context.getBean("draftMapper");
		
//
//		List<DraftVO> data = draftMapper.MydraftAtrzList("NAVER_2014030001");
//		for (DraftVO draftVO : data) {
//			System.out.println(draftVO);
//		}
		

	}

}

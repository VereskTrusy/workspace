package kr.or.ddit.bts;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import kr.or.ddit.bts.service.BtsServiceImpl;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.BtsVO;

class BtsServiceImplTest {

	// BtsServiceImpl readBts test
	@Test
	void test1() { // assert 계열의 메서드 사용하여 exception 예측
		assertThrows(PkNotFoundException.class, new Executable() { // 익명 함수사용
			
			@Override
			public void execute() throws Throwable {
				BtsServiceImpl service = new BtsServiceImpl();
				BtsVO vo = service.readBts("");
				System.out.println(
					  "code : " + vo.getCode() 
					+ " name : " + vo.getName() 
					+ " path : " + vo.getPath());
			}
		});
	}
	
	
	
	

}

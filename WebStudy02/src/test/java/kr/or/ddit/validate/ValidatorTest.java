package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ValidatorTest {
	static Validator validator;
	
	@BeforeAll
	static void setUpBeforClass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // Validation : 클래스 패스 모두 검사 하여 validation 관련한 구현체를 찾고 빌드한다.
        validator = factory.getValidator();
	}
	
	@Test
	void testMemberVO() {
		MemberVO target = new MemberVO();
//		target.setMemId("a001");
//		target.setMemPass("12");
//		target.setMemName("복칠");
//		target.setMemRegno1("240404");
//		target.setMemZip("집");
//		target.setMemAdd1("주소1");
//		target.setMemAdd2("주소2");
//		target.setMemMail("asdf@korea.kr");
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target, InsertGroup.class, UpdateGroup.class, DeleteGroup.class);
		
		for(ConstraintViolation<MemberVO> single : violations) {
			String propName = single.getPropertyPath().toString();
			String message = single.getMessage();
			log.info("{} : {}", propName, message);
		}
		
		boolean valid = violations.isEmpty();
		log.info("검증 통과 여부 : {}", valid);
		log.info("검증에 통과하지 못한 프로퍼티 갯수 : {}", violations.size());
	}
}

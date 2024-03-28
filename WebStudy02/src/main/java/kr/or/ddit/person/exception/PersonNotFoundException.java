package kr.or.ddit.person.exception;

public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(String id) {
		super(String.format("해당 사용자[%s]가 존재하지 않습니다.", id));
	}
	
}

package kr.or.ddit.exception;

/**
 * PK로 조회한 데이터가 없을 때 발생시킴.
 *
 */
public class PkNotFoundException extends ResponseStatusException{

	public PkNotFoundException(int status) {
		super(status, "test 해당 데이터가 존재하지 않습니다.");
	}

}

package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * PK로 조회한 데이터가 없을 때 발생시킴.
 *
 */
public class PkNotFoundException extends ResponseStatusException {

	public PkNotFoundException(int status) {
		super(HttpStatus.valueOf(status), "test 해당 데이터가 존재하지 않습니다.");
	}

}

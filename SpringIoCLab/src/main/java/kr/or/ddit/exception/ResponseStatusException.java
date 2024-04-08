package kr.or.ddit.exception;

// 1. 커서텀 익셉션
// 2. 혹시 예외가 발생하더라도 컴파일 에러 안나게
// 언체크드 익셉션
public class ResponseStatusException extends RuntimeException{
	private int status;

	public ResponseStatusException(int status, String message) {
		super(message);
		this.status = status;
	}
	
	public ResponseStatusException(int status) {
		this(status, String.format("%d 상태코드 결정을 위한 예외", status));
	}
	
	public int getStatus() {
		return status;
	}
}

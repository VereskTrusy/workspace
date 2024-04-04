package kr.or.ddit.mvc;

public class CannotResolverViewNameException extends RuntimeException{

	public CannotResolverViewNameException() {
		super();
	}

	public CannotResolverViewNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CannotResolverViewNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotResolverViewNameException(String message) {
		super(message);
	}

	public CannotResolverViewNameException(Throwable cause) {
		super(cause);
	}
	
}

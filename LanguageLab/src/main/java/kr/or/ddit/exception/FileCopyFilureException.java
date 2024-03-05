package kr.or.ddit.exception;

import java.io.IOException;
import java.io.UncheckedIOException;

public class FileCopyFilureException extends UncheckedIOException {

	public FileCopyFilureException(IOException cause) {
		super(cause);
	}

	public FileCopyFilureException(String message, IOException cause) {
		super(message, cause);
	}
	
}

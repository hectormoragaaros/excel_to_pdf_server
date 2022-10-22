package org.hectormoraga.exceltopdfclient.exception;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.hectormoraga.exceltopdfclient.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<ResponseMessage> handleGenericMultipartException(MultipartException exc) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(exc.getMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseMessage> handleGenericRuntimeException(RuntimeException exc) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(exc.getMessage()));
	}
	
}

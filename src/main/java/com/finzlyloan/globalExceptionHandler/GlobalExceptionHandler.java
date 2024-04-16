package com.finzlyloan.globalExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoanFailureException.class)
	public ResponseEntity<ErrorResponse> custumerNotFound(LoanFailureException cnfe, WebRequest wq){
		
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),cnfe.getMessage(),
				wq.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage()),
				HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public ResponseEntity<ErrorResponse> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest wq) {
//		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), nfe.getMessage(), 
//				wq.getDescription(false)),HttpStatus.BAD_REQUEST);
//	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> myExceptionHandler(Exception e, WebRequest wq) throws IOException {
	
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),e.getMessage(),
				wq.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
}

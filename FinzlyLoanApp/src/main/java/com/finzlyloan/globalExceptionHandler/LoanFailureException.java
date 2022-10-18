package com.finzlyloan.globalExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoanFailureException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public LoanFailureException(String msg) {
		super(msg);
	}
}

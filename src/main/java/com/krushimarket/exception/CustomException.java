package com.krushimarket.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomException extends RuntimeException{
	public CustomException(String msg) {
		super(msg);
	}
}

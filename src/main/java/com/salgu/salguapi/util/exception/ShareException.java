package com.salgu.salguapi.util.exception;

import com.salgu.salguapi.util.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class ShareException extends CustomException{

	private ResponseCodeEnum responseCodeEnum = null;

	public ShareException(String message) {
		super(message);
	}

	public ShareException(ResponseCodeEnum code) {
		super(code.getMessage());
		this.responseCodeEnum = code;
	}

	public ShareException(ResponseCodeEnum code, String message) {
		super(message);
		this.responseCodeEnum = code;
	}
}
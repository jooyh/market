package com.study.market.commons.exceptions;

/**
 * FILE NAME   : LoginException.java
 * PACKAGE     : com.study.market.exceptions
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 22.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 22.     SIWAN       최초작성
 */
@SuppressWarnings("serial")
public class LoginException extends Exception{
	/* 인증 예외처리 클래스
	 *
	 * 100 > 로그인에 실패했습니다.
	 **/

	private final int ERR_CODE;

	public LoginException() {
		super("로그인에 실패했습니다.");
		this.ERR_CODE = 100;
	}

	public LoginException(String msg , int errCode) {
		super(msg);
		this.ERR_CODE = errCode;
	}

	public LoginException(String msg) {
		this(msg,100);
	}

	public int getErrCode() {
		return this.ERR_CODE;
	}
}

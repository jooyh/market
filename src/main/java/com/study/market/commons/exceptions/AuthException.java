package com.study.market.commons.exceptions;
/**
 * FILE NAME   : AuthException.java
 * PACKAGE     : com.study.market.exceptions
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */
@SuppressWarnings("serial")
public class AuthException extends Exception{
	/* 인증 예외처리 클래스
	 *
	 * 100 > 인증정보 없음
	 * 200 > 등록오류
	 * 300 > 토큰 생성오류
	 **/

	private final int ERR_CODE;

	public AuthException(String msg , int errCode) {
		super(msg);
		this.ERR_CODE = errCode;
	}

	public AuthException(String msg) {
		this(msg,0);
	}

	public int getErrCode() {
		return this.ERR_CODE;
	}
}


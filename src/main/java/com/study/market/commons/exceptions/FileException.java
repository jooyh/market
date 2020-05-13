package com.study.market.commons.exceptions;
/**
 * FILE NAME   : FileException.java
 * PACKAGE     : com.study.market.exceptions
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 13.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 13.     SIWAN       최초작성
 */
@SuppressWarnings("serial")
public class FileException extends Exception{

	private final int ERR_CODE;

	public FileException(String msg , int errCode) {
		super(msg);
		this.ERR_CODE = errCode;
	}

	public FileException(String msg) {
		this(msg,100);
	}

	public int getErrCode() {
		return this.ERR_CODE;
	}

}

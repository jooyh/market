package com.study.market.commons.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.study.market.commons.exceptions.FileException;
import com.study.market.commons.util.FileUtil;

/**
 * FILE NAME   : BaseController.java
 * PACKAGE     : com.study.market.commons
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 3.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 3.     SIWAN       최초작성
 */
public class BaseController {

	private Map paramMap;

	@Autowired
	private FileUtil fileUtil;

	public Map getParamMap(HttpServletRequest request) {
		this.setParamMap(request.getAttribute("params"));
		return paramMap;
	}

	private void setParamMap(Object object) {
		this.paramMap = (Map) object;
	}

	public List getFileMap(MultipartHttpServletRequest request) throws FileException {
		return fileUtil.uploadFiles(request);
	}

}


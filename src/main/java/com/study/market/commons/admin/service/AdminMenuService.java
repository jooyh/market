package com.study.market.commons.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;

@Service
public class AdminMenuService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "AdminMenuMapper.";

	/**
	 * NAME : selectAdminMenuList
	 * DESC : 관리자 메뉴 목록 조회
	 * DATE : 2020. 5. 25.
	 * <pre>
	 * @auther jyh
	 * @param param
	 * @return 메뉴 목록
	 * </pre>
	 */
	public List selectAdminMenuList() {
		return (List) sqlSession.selectList(super.mkSqlId(NAME_SPACE,"selectAdminMenuList"));
	}


}

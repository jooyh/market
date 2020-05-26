package com.study.market.commons.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;

/**
 * @author jyh
 *
 */
@Service
public class AdminAccIpService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "AdminAccIpMapper.";

	/**
	 * NAME : selectAccIpList
	 * DESC : 허용 IP 목록 조회
	 * DATE : 2020. 5. 26.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public List selectAccIpList(Map params) {
		return sqlSession.selectList(mkSqlId(NAME_SPACE, "selectAccIpList"),params);
	}

	/**
	 * NAME : insertAccIp
	 * DESC : 허용 IP 등록
	 * DATE : 2020. 5. 26.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public int insertAccIp(Map params) {
		return sqlSession.insert(mkSqlId(NAME_SPACE, "insertAccIp"), params);
	}


}

package com.stroll.www.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stroll.www.vo.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession mybatis;
	
	public UserVO checkPassword(UserVO vo) {
		return mybatis.selectOne("user.checkPassword", vo);
	}

	public void registerUser(UserVO vo) {
		mybatis.insert("user.registerUser", vo);
	}

	public UserVO selectUser(UserVO vo) {
		return mybatis.selectOne("user.selectUser");
		
	}
	
}

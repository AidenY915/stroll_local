package com.stroll.www.controller;

import org.springframework.stereotype.Service;

import com.stroll.www.dao.UserDAO;
import com.stroll.www.vo.UserVO;

@Service
public class UserService {
	private UserDAO dao;

	private String checkPassword(UserVO vo) {
		return dao.checkPassword(vo).getId();
	}
	
	public String login(UserVO vo) {
		return checkPassword(vo);
		
	}
}

package com.stroll.www.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stroll.www.dao.UserDAO;
import com.stroll.www.vo.UserVO;

@Service
public class UserService {
	@Autowired
	private UserDAO dao;

	private String checkPassword(UserVO vo) {
		UserVO rsltVO = dao.checkPassword(vo);
		if(rsltVO == null) return null;
		return rsltVO.getId();
	}
	
	public String login(UserVO vo) {
		return checkPassword(vo);
	}

	public void registerUser(UserVO vo) {
		dao.registerUser(vo);
	}

	public void idDuplicateCheck(UserVO vo, PrintWriter out) {
		vo = dao.selectUser(vo);
		if(vo == null) {
			out.print("true");
		}else {
			out.print("false");
		}
		return;	
	}
}

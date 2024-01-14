package com.stroll.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stroll.www.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(UserVO vo, HttpServletRequest request) {
		String id = service.login(vo);
		if(id != null)
			request.getSession().setAttribute("id", id);
		return;
	}
}

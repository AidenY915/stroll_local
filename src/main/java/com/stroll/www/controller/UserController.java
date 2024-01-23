package com.stroll.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stroll.www.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVO vo,@RequestParam("path") String path, HttpServletRequest request) {
		String id = service.login(vo);
		if(id != null) {
			HttpSession session = request.getSession(); 
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(60*20);
		}
		System.out.println("id : " + id);
		System.out.println(path);
		return "redirect:" + path.replace("/stroll", "");
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	@RequestMapping(value = "/registerOK")
	public String register(UserVO vo) {
		System.out.println("<register 컨트롤러>");
		service.registerUser(vo);
		return "redirect:/";
	}
	@RequestMapping(value = "idDuplicateCheck")
	public void idDuplicateCheck(UserVO vo, HttpServletResponse response) {
		try(PrintWriter out = response.getWriter()) {
			service.idDuplicateCheck(vo, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}

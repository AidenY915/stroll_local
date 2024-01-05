package com.stroll.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping(value={"/main", "/"})
	public String showMain() {
		return "main";
	}
	
	@RequestMapping("/moreInfo")
	public String showMoreInfo() {
		return "moreInfo";	
	}
}

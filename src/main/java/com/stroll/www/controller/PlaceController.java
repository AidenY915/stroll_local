package com.stroll.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stroll.www.vo.PlaceVO;

@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/aroundme")
	public String showAroundme(@RequestParam(name = "keywords", defaultValue = "", required = false) String keywords,
			PlaceVO vo, Model model) {
		model.addAttribute("places", placeService.getPlaceList(vo ,keywords));
		return "aroundme";
	}

	@RequestMapping("/detail")
	public String showDetail(PlaceVO vo, Model model) {
		model.addAttribute("place", placeService.getPlace(vo));
		model.addAttribute("imgs", placeService.getImgs(vo));
		model.addAttribute("replies", replyService.selectReplies(vo));
		return "detail";
	}
	
	@RequestMapping("/newplace")
	public String showNewPlace() {
		return "newPlace";
	}
	@RequestMapping(value = "/insertPlace", method = RequestMethod.POST)
	public String insertPlace(@RequestParam("imgs") MultipartFile[] imgs ,PlaceVO vo, RedirectAttributes redirect) {
		System.out.println(vo);
		System.out.println("insertPlace입장");
		redirect.addAttribute("no", placeService.insertPlace(vo, imgs));
		return "redirect:detail";
	}
}

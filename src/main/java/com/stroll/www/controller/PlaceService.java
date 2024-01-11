package com.stroll.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stroll.www.dao.PlaceDAO;
import com.stroll.www.vo.PlaceVO;

@Service
public class PlaceService {
	@Autowired
	private PlaceDAO dao;
	public PlaceVO getPlace(PlaceVO vo) {
		return dao.getPlace(vo);
	}
	public List<PlaceVO> getPlaceList(String keywords) {
		PlaceVO vo = new PlaceVO();
		keywords = keywords.replaceAll(" ", "|");
		vo.setTitle(keywords);
		vo.setAddress(keywords);
		return dao.getPlaceList(vo);
	}
	
}

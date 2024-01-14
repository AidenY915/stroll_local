package com.stroll.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stroll.www.dao.ReplyDAO;
import com.stroll.www.vo.PlaceVO;
import com.stroll.www.vo.ReplyVO;

@Service
public class ReplyService {
	@Autowired
	private ReplyDAO dao;

	public void insertReply(ReplyVO vo) {
		dao.insertReply(vo);
	}

	public List<ReplyVO> selectReplies(PlaceVO vo) {
		List<ReplyVO> rsltReplies = dao.selectReplies(vo);
		System.out.println(rsltReplies);
		return rsltReplies;
	}
}

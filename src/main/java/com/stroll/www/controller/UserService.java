package com.stroll.www.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stroll.www.dao.UserDAO;
import com.stroll.www.vo.PlaceVO;
import com.stroll.www.vo.UserVO;
import com.stroll.www.vo.WishVO;

@Service
public class UserService {
	@Autowired
	private UserDAO dao;

	private String checkPassword(UserVO vo) {
		UserVO rsltVO = dao.checkPassword(vo);
		if (rsltVO == null)
			return null;
		return rsltVO.getId();
	}

	public String login(UserVO vo) {
		return checkPassword(vo);
	}

	public void registerUser(UserVO vo) {
		dao.registerUser(vo);
	}

	public void duplicateCheck(UserVO vo, PrintWriter out) {
		if (vo.getId() != null)
			vo = dao.selectUser(vo);
		else if (vo.getNickname() != null)
			vo = dao.selectUserByNickname(vo);
		if (vo == null) {
			out.print("true");
		} else {
			out.print("false");
		}
		return;
	}

	public void addToWishList(WishVO vo) {
		if (dao.selectWish(vo) == null)
			dao.insertToWishList(vo);
	}

	public void deleteFromWishList(WishVO vo) {
		dao.deleteFromWishList(vo);
	}

	public Boolean isWishedPlace(WishVO vo) {
		return dao.selectWish(vo) != null;
	}

	public List<PlaceVO> getWishedPlaces(WishVO vo) {
		return dao.selectWishedPlaces(vo);
	}

	public List<PlaceVO> getUserPlaces(UserVO vo) {
		return dao.selectUserPlaces(vo);
	}

	public Object getUserReivews(UserVO vo) {
		return dao.selectUserReivews(vo);
	}

	public boolean withdraw(UserVO vo) {
		if (dao.deleteUser(vo) != 1) {
			return false;
		}
		return true;
	}
}

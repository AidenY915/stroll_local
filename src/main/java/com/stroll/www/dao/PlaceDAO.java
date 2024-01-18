package com.stroll.www.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stroll.www.vo.PlaceVO;

@Repository
public class PlaceDAO {
	
	@Autowired
	private SqlSession mybatis;

	public PlaceVO getPlace(PlaceVO vo) {
		return mybatis.selectOne("place.selectPlace", vo);
	}

	public List<PlaceVO> getPlaceList(PlaceVO vo) {
		return mybatis.selectList("place.selectPlaces", vo);
	}

	public void insertPlace(PlaceVO vo) {
		mybatis.insert("place.insertPlace", vo);
	}

	public List<PlaceVO> getPlaceListByAddress(PlaceVO vo) {
		return mybatis.selectList("place.selectPlacesByAddress", vo);
	}
}

package com.stroll.www.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stroll.www.vo.PlaceRowMapper;
import com.stroll.www.vo.PlaceVO;

@Repository
public class PlaceDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String PLACE_TABLE = "place";
	
	public PlaceVO getPlace(PlaceVO vo) {
		String selectPlaceQuery="SELECT * FROM " + PLACE_TABLE + " WHERE no = ?";
		return jdbcTemplate.queryForObject(selectPlaceQuery, new PlaceRowMapper(), vo.getNo());
	}

	public List<PlaceVO> getPlaceList(String keywords) {
		String[] words = keywords.split(" ");
		StringBuilder selectPlaceListQuery = new StringBuilder(100);
		selectPlaceListQuery.append("SELECT * FROM " + PLACE_TABLE); //keywords에 공백만 있으면 아무것도 안나옴.
		if(words.length!=0) selectPlaceListQuery.append(" WHERE 1=0");
		for(String word : words) {
			selectPlaceListQuery.append(" OR title LIKE '%" + word + "%' OR address LIKE '%" + word + "%'");
		}
		System.out.println(selectPlaceListQuery);
		return jdbcTemplate.query(selectPlaceListQuery.toString(), new PlaceRowMapper());		
	}
}

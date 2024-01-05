package com.stroll.www.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PlaceRowMapper implements RowMapper<PlaceVO> {

	@Override
	public PlaceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PlaceVO vo = new PlaceVO();
		vo.setNo(rs.getInt("no"));
		vo.setTitle(rs.getString("title"));
		vo.setContent(rs.getString("content"));
		vo.setWrittenDate(rs.getTimestamp("writtenDate"));
		vo.setAddress(rs.getString("address"));
		vo.setDetailAddress(rs.getString("detailAddress"));
		vo.setX(rs.getDouble("x"));
		vo.setY(rs.getDouble("y"));
		return vo;
	}

}

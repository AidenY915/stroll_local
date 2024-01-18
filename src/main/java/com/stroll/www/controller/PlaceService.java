package com.stroll.www.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
	public List<PlaceVO> getPlaceList(PlaceVO vo, String keywords) {
		keywords = keywords.replaceAll(" ", "|");
		if(keywords.equals("")) keywords=".*";
		vo.setTitle(keywords);
		vo.setDetailAddress(keywords);
		if(vo.getAddress() == null || vo.getAddress().equals(""))
			vo.setAddress(keywords);
		else {
			String addressRegex = vo.getAddress().replaceAll("(특별시|시)", "[가-힣]{0,3}");
			vo.setAddress(addressRegex);
			System.out.println(vo);
			return dao.getPlaceListByAddress(vo);
		}
		System.out.println(vo);
		return dao.getPlaceList(vo);
	}
	public void insertPlace(PlaceVO vo) {
		String jsonStr = getKakaoCoordinate(vo.getAddress() + vo.getDetailAddress());
		String x = jsonStr.split("\"x\":\"")[1].split("\"")[0];
		String y = jsonStr.split("\"y\":\"")[1].split("\"")[0];
		System.out.println(x);
		System.out.println(y);
		vo.setX(Double.parseDouble(x));
		vo.setY(Double.parseDouble(y));
		dao.insertPlace(vo);
				//,"x":"127.030921234166","y":"37.4924272855457"
	}
	
	private String getKakaoCoordinate(String address) {
	    String apiKey = "7cb356828f22bbbdc0fd58da42790461";
	    String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
	    String jsonString = null;
	    System.out.println(address);
	    try {
	        address = URLEncoder.encode(address, "UTF-8");

	        String addr = apiUrl + "?query=" + address;

	        URL url = new URL(addr);
	        URLConnection conn = url.openConnection();
	        conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

	        BufferedReader rd = null;
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        StringBuffer docJson = new StringBuffer();

	        String line;

	        while ((line=rd.readLine()) != null) {
	            docJson.append(line);
	        }

	        jsonString = docJson.toString();
	        rd.close();

	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return jsonString;
	}
	
}

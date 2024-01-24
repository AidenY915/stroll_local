package com.stroll.www.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stroll.www.dao.PlaceDAO;
import com.stroll.www.vo.PlaceVO;

@Service
public class PlaceService {
	@Autowired
	private PlaceDAO dao;
	private final static int PAGE_SIZE = 10;

	public PlaceVO getPlace(PlaceVO vo) {
		vo = dao.getPlace(vo);
		vo.setStar(Math.round(vo.getStar() * 10) / 10.0f);
		return vo;
	}

	public List<PlaceVO> getPlaceList(PlaceVO vo, String keywords, String order, int page, HttpServletRequest request,
			int maxDistance, int minStar) {
		List<PlaceVO> listFromDb = null;
		keywords = keywords.replaceAll(" ", "|");
		if (keywords.equals(""))
			keywords = ".*";
		vo.setTitle(keywords);
		vo.setDetailAddress(keywords);
		if (vo.getCategory() == null)
			vo.setCategory("");
		if (vo.getAddress() == null || vo.getAddress().equals("")) {
			vo.setAddress(keywords);
			listFromDb = dao.getPlaceList(vo);
		} else {
			String addressRegex = vo.getAddress().replaceAll("(특별시|광역시|시)", "[가-힣]{0,3}").replaceAll("^[가-힣]+도", "");
			vo.setAddress(addressRegex);
			listFromDb = dao.getPlaceListByAddress(vo);
		}
		for (PlaceVO place : listFromDb) {
			place.setDistance((int) Math.pow(((Math.pow(place.getX() * 1849 - vo.getX() * 1849, 2)
					+ Math.pow(place.getY() * 110940 - vo.getY() * 110940, 2))), 0.5));
			place.setStar(Math.round(place.getStar() * 10) / 10.0f);
		}
		sortPlaces(listFromDb, order);
		if(vo.getX()!=0)
			filterPlaces(listFromDb, maxDistance, minStar);
		
		List<PlaceVO> rsltList = new LinkedList<>();
		request.setAttribute("numOfPages", (int) Math.ceil(listFromDb.size() / (double) PAGE_SIZE));
		for (int i = (page - 1) * PAGE_SIZE; i < (page) * PAGE_SIZE && i < listFromDb.size(); i++) {
			rsltList.add(listFromDb.get(i));
		}
		return rsltList;
	}

	public int insertPlace(PlaceVO vo, MultipartFile[] imgs) {
		String jsonStr = getKakaoCoordinate(vo.getAddress() + vo.getDetailAddress());
		String x = jsonStr.split("\"x\":\"")[1].split("\"")[0];
		String y = jsonStr.split("\"y\":\"")[1].split("\"")[0];
		System.out.println(x);
		System.out.println(y);
		vo.setX(Double.parseDouble(x));
		vo.setY(Double.parseDouble(y));
		dao.insertPlace(vo);
		uploadImgs(imgs, vo);
		return vo.getNo();
		// ,"x":"127.030921234166","y":"37.4924272855457"
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

			while ((line = rd.readLine()) != null) {
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

	private void uploadImgs(MultipartFile[] imgs, PlaceVO vo) {
		System.out.println(Arrays.toString(imgs));
		for (int i = 0; i < imgs.length; i++) {
			if (imgs[i].isEmpty())
				break;
			try {
				// String extension = imgs[i].getOriginalFilename().split("\\.")[1]; jpg로 통일
				imgs[i].transferTo(new File(
						"C:\\Users\\Aiden\\Documents\\Codes\\SPRING\\stroll\\src\\main\\webapp\\resources\\upload\\imgs\\"
								+ vo.getNo() + "_" + (i + 1) + "." + "jpg"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public List<String> getImgs(PlaceVO vo) {
		File dir = new File(
				"C:\\Users\\Aiden\\Documents\\Codes\\SPRING\\stroll\\src\\main\\webapp\\resources\\upload\\imgs\\");
		String[] fileNames = dir.list(); // 자동 이름 순 정렬
		if (fileNames == null) {
			return null;
		}
		List<String> rslt = new ArrayList<>(fileNames.length);
		boolean endFlag = false;
		for (String fileName : fileNames) {
			System.out.println(fileName);
			if (fileName.split("_")[0].equals("" + vo.getNo())) {
				rslt.add("resources/upload/imgs/" + fileName);
				endFlag = true;
			} else if (endFlag)
				break;
		}
		System.out.println(rslt);
		return rslt;
	}

	private void sortPlaces(List<PlaceVO> placeList, String order) {
		switch (order) {
		case "distance":
			Collections.sort(placeList, (p1, p2) -> (p1.getDistance() - p2.getDistance()));
			break;
		case "star":
			Collections.sort(placeList, (p1, p2) -> ((int) (p2.getStar() - p1.getStar() * 10)));
			break;
		}
	}

	private void filterPlaces(List<PlaceVO> placeList, int maxDistance, int minStar) {
		if (maxDistance >= 1 && maxDistance <= 50) {
			maxDistance*=100;
			for (int i = 0; i < placeList.size(); i++) {
				if (placeList.get(i).getDistance() > maxDistance) {
					placeList.remove(i);
					i--;
				}
			}
		}
		if (minStar >= 1 && minStar <= 5) {
			for (int i = 0; i < placeList.size(); i++) {
				if (placeList.get(i).getStar() < minStar) {
					placeList.remove(i);
					i--;
				}
			}
		}
	}

	public boolean deletePlace(PlaceVO vo, String id) {
		vo = dao.getPlace(vo);
		if (vo.getUserId().equals(id)) {
			return dao.deletePlace(vo) == 1;
		}
		return false;
	}
}

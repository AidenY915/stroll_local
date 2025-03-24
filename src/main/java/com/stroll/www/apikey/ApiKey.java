package com.stroll.www.apikey;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiKey {
	public static String kakaoApiKey;
	{
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/config/api_key.properties")) {
            prop.load(fis);
            String kakaoApiKey = prop.getProperty("kakao_api_key");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

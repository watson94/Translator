package com.example.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Translator{
	public static String translate(String keyword) {
		String answer = null;
		String lang = "en-ru";
		try {
			URL url = new URL(
					"http://translate.yandex.net/api/v1/tr.json/translate?lang="
							+ lang + 
							"&text="
							+ keyword);
			URLConnection connection = url.openConnection();
			StringBuilder response = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String tmp;
			while ((tmp = reader.readLine()) != null) {
				response.append(tmp);
			}
			JSONObject json = new JSONObject(response.toString());
			JSONArray translateArray = json.getJSONArray("text");
			answer = translateArray.getString(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return answer;
	}
}

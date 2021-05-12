package com.itacademy.dicegame.security;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Auth0Token {

	public String getToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject requestBody = new JSONObject();
		try {
			requestBody.put("client_id", "si2NWLcIpaAwwtiBmr6LRsu9qLNi9kby");
			requestBody.put("client_secret", "zI5MHkGgjPqJLxp7fEiiSnQMwRCTdqshEmyVNqoX-wR_R7u-cVvg0VUhmLX5A0R_");
			requestBody.put("audience", "m15DiceGame");
			requestBody.put("grant_type", "client_credentials");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> result = restTemplate.postForObject("https://gpitacademy.eu.auth0.com/oauth/token",
				request, HashMap.class);
		
		return result.get("access_token");
	}

}

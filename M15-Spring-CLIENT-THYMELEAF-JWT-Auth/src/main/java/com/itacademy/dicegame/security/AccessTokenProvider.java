package com.itacademy.dicegame.security;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@Component
public class AccessTokenProvider {

	@Value("${auth0.client-id}")
	private String clientId;

	@Value("${auth0.client-secret}")
	private String clientSecret;

	@Value("${auth0.audience}")
	private String audience;

	public AccessTokenProvider() {
	}

	public AccessToken getAccessToken(OidcUser auth0User) {
		HttpResponse<String> response;
		AccessToken accessToken;
		try {
			response = Unirest.post("https://gpitacademy.eu.auth0.com/oauth/token")
					.header("content-type", "application/json")
					.body("{\"client_id\":\"" + clientId + "\",\"client_secret\":\"" + clientSecret + "\",\"audience\":\"" + audience + "\",\"grant_type\":\"client_credentials\"}")
					.asString();
			ObjectMapper objectMapper = new ObjectMapper();
			accessToken = objectMapper.readValue(response.getBody(), AccessToken.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return accessToken;
	}
}
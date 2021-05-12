package com.itacademy.dicegame.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itacademy.dicegame.dto.PlayerDTO;

@Service
public class WebService {

	String host = "http://localhost:8080/";
	
	public PlayerDTO getPlayerByIdAuth0(OidcUser principal) {
		RestTemplate restTemplate = new RestTemplate();
		PlayerDTO player = null;
		try {
		System.out.println(principal.getIdToken().getTokenValue());
			 player = restTemplate.exchange
			 (host + "players/auth0/" + principal.getSubject(), HttpMethod.GET, createEntityFromToken(principal), PlayerDTO.class).getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return player;
	}
	
	public void postPlayerByIdAuth0(PlayerDTO player,OidcUser principal) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(host + "player/"  ,player, PlayerDTO.class).getBody();
		
		System.out.println(principal.getIdToken().getTokenValue());
		 ResponseEntity<PlayerDTO> response = restTemplate.exchange
		 (host + "player/" + principal.getSubject(), HttpMethod.POST, createEntityFromToken(principal), PlayerDTO.class);

		 System.out.println(response.getBody());
		
		
	}
	
	
	public HttpEntity<PlayerDTO> createEntityFromToken(OidcUser principal){
	
	return createHttpEntity(createHeaders(principal.getIdToken().getTokenValue()));
	}
	
	private HttpHeaders createHeaders(String accessToken){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
		   return headers;
		}
	
	private HttpEntity<PlayerDTO> createHttpEntity(HttpHeaders headers ){
		HttpEntity request = new HttpEntity<PlayerDTO>(headers);
		return request;
	}




	
	
	
	
	
	
}

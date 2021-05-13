package com.itacademy.dicegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.itacademy.dicegame.dto.PlayerDTO;

import reactor.core.publisher.Mono;

@Service
public class WebService {
/*
 * Servici responsable to consum api Rest
 * 
 */
	@Value("${host.api.url}")
	private String host;
	
    @Autowired
    WebClient webClient;
	
	public PlayerDTO getPlayerByIdAuth0(OidcUser principal) {
		PlayerDTO player = webClient.get()
		.uri("/players/auth0/" + principal.getSubject())
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + principal.getIdToken().getTokenValue())
		.retrieve()
		.bodyToMono(PlayerDTO.class).block();
		return player;
	}
	
	public void postPlayerByIdAuth0(PlayerDTO player,OidcUser principal) {
		PlayerDTO playerdb = webClient.post()
		.uri("/players")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + principal.getIdToken().getTokenValue())
		.body(Mono.just(player), PlayerDTO.class)
		.retrieve()
		.bodyToMono(PlayerDTO.class).block();
	}

}

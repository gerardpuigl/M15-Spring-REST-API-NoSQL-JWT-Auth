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
	
	public PlayerDTO getPlayerByIdAuth0(OidcUser auth0User) {
		PlayerDTO player = webClient.get()
		.uri("/players/auth0/" + auth0User.getSubject())
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + auth0User.getIdToken().getTokenValue())
		.retrieve()
		.bodyToMono(PlayerDTO.class).block();
		return player;
	}
	
	public void postNewPlayer(PlayerDTO player,OidcUser auth0User) {
		PlayerDTO playerdb = webClient.post()
		.uri("/players")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + auth0User.getIdToken().getTokenValue())
		.body(Mono.just(player), PlayerDTO.class)
		.retrieve()
		.bodyToMono(PlayerDTO.class).block();
	}

	public void updatePlayer(PlayerDTO player, OidcUser auth0User) {
		PlayerDTO playerdb = webClient.put()
		.uri("/players")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + auth0User.getIdToken().getTokenValue())
		.body(Mono.just(player), PlayerDTO.class)
		.retrieve()
		.bodyToMono(PlayerDTO.class).block();
		
	}

}

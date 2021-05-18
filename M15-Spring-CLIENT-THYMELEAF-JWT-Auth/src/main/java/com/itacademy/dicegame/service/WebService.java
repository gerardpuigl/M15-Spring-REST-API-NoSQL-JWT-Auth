package com.itacademy.dicegame.service;

import java.util.Collections;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.Converters.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.itacademy.dicegame.dto.DiceGameDTO;
import com.itacademy.dicegame.dto.OneDiceGame;
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

	public void deleteplayer(PlayerDTO player, OidcUser auth0User) {
		webClient.delete()
		.uri("/players/" + player.getId())
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + auth0User.getIdToken().getTokenValue())
		.retrieve()
		.bodyToMono(String.class)
		.block();		
	}

	public OneDiceGame throwonedice(PlayerDTO player, OidcUser auth0User) {
		return webClient.post()
		.uri("/players/"+ player.getId() + "/games/OneDiceGame")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + auth0User.getIdToken().getTokenValue())
		.retrieve()
		.bodyToMono(OneDiceGame.class)
		.block();		
	}

	public List<OneDiceGame> getAllGames(PlayerDTO player, OidcUser auth0User) {
		List<OneDiceGame> gamelist = webClient.get()
		.uri("/players/"+ player.getId() + "/games/OneDiceGame")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + auth0User.getIdToken().getTokenValue())
		.retrieve()
		.bodyToFlux(OneDiceGame.class)
		.buffer().blockLast();
		Collections.reverse(gamelist);
		return gamelist;
	}
	
	public List<OneDiceGame> getLast10DiceGames(PlayerDTO player, OidcUser auth0User){
		List<OneDiceGame> gamelist =getAllGames(player, auth0User);
		return gamelist.stream().limit(10).collect(Collectors.toList());
		}
	
}

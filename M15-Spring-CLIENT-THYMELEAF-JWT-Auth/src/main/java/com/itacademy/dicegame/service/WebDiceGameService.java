package com.itacademy.dicegame.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.itacademy.dicegame.dto.DiceGameDTO;
import com.itacademy.dicegame.dto.PlayerDTO;

@Service
public class WebDiceGameService {
/*
 * Servici responsable to consum api Rest
 * 
 */
	
	/**
	 * @Value take parameters from properties files. Change configurated parameters is easier in this way.
	 */
	@Value("${host.api.url}")
	private String host;
	
    @Autowired
    WebClient webClient;
    
	public void deletePlayerGamesByType(PlayerDTO player, OidcUser auth0User, String gameType) {
		webClient.delete()
			.uri("/players/"+ player.getId() + "/games/" + gameType)
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}
    
	public DiceGameDTO throwonedice(PlayerDTO player, OidcUser auth0User, String gameType) {
		return webClient.post()
			.uri("/players/"+ player.getId() + "/games/" + gameType)
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToMono(DiceGameDTO.class)
			.block();		
	}

	public List<DiceGameDTO> getAllGames(PlayerDTO player, OidcUser auth0User, String gameType) {
		List<DiceGameDTO> gamelist = webClient.get()
			.uri("/players/"+ player.getId() + "/games/" + gameType)
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToFlux(DiceGameDTO.class)
			.buffer().blockLast();
		if(gamelist != null) Collections.reverse(gamelist);
		return gamelist;
	}
	
	public List<DiceGameDTO> getLast10DiceGames(PlayerDTO player, OidcUser auth0User, String gameType){
		List<DiceGameDTO> gamelist = getAllGames(player, auth0User, gameType);
		if(gamelist != null) gamelist = gamelist.stream().limit(10).collect(Collectors.toList());
		return gamelist;
		}

	public PlayerDTO getWinner(PlayerDTO player, OidcUser auth0User, String gameType) {
		PlayerDTO winner = webClient.get()
			.uri("/players/games/" + gameType + "/winner")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToMono(PlayerDTO.class).block();
		return winner;
	}

	public PlayerDTO getLoser(PlayerDTO player, OidcUser auth0User, String gameType) {
		PlayerDTO loser = webClient.get()
			.uri("/players/games/" + gameType + "/loser")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToMono(PlayerDTO.class).block();
		return loser;
	}
	
	public List<PlayerDTO> getRanking(PlayerDTO player, OidcUser auth0User, String gameType) {
		List<PlayerDTO> ranking = webClient.get()
			.uri("/players/games/" + gameType + "/ranking")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToFlux(PlayerDTO.class)
			.buffer().blockLast();
		return ranking;
	}
	
	public double getAverage(PlayerDTO player, OidcUser auth0User, String gameType) {
		double average = webClient.get()
			.uri("/players/games/" + gameType + "/average")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToMono(Double.class).block();
		return average;
	}	
	
}

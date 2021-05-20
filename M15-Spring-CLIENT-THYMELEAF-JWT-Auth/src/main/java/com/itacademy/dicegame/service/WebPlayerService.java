package com.itacademy.dicegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.itacademy.dicegame.dto.PlayerDTO;
import com.itacademy.dicegame.security.AccessToken;
import com.itacademy.dicegame.security.AccessTokenProvider;

import reactor.core.publisher.Mono;

@Service
public class WebPlayerService {
	
	@Value("${host.api.url}")
	private String host;
	
    @Autowired
    WebClient webClient;
    
    @Autowired
    AccessTokenProvider accessTokenProvider;
    
	public PlayerDTO getPlayerByIdAuth0(OidcUser auth0User) {
		AccessToken accessToken = accessTokenProvider.getAccessToken(auth0User);
		PlayerDTO player = webClient.get()
			.uri("/players/auth0/" + auth0User.getSubject())
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, accessToken.toString())
			.retrieve()
			.bodyToMono(PlayerDTO.class).block();
		player.setAccessToken(accessToken);
		return player;
	}
	
	public PlayerDTO postNewPlayer(PlayerDTO player,OidcUser auth0User) {
		AccessToken accessToken = accessTokenProvider.getAccessToken(auth0User);
		webClient.post()
			.uri("/players")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION,  accessToken.toString())
			.body(Mono.just(player), PlayerDTO.class)
			.retrieve()
			.bodyToMono(PlayerDTO.class).block();

		player.setAccessToken(accessToken);
		return player;
	}

	public void updatePlayer(PlayerDTO player, OidcUser auth0User) {
		webClient.put()
			.uri("/players")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.body(Mono.just(player), PlayerDTO.class)
			.retrieve()
			.bodyToMono(PlayerDTO.class).block();
	}

	public void deleteplayer(PlayerDTO player, OidcUser auth0User) {
		webClient.delete()
			.uri("/players/" + player.getId())
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, player.getStringAccessToken())
			.retrieve()
			.bodyToMono(String.class)
			.block();		
	}
	
}

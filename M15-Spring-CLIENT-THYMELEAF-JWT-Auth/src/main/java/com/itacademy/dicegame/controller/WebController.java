package com.itacademy.dicegame.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.dicegame.dto.PlayerDTO;
import com.itacademy.dicegame.service.WebService;

@Controller
public class WebController {
	/*
	 * Controller View
	 * 
	 * Done with RestTemplate to simulate and independent APP that request info to the Rest API.
	 * 
	 */
	@Autowired
	WebService webservice;

	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
            PlayerDTO player = webservice.getPlayerByIdAuth0(principal);
            if(player==null) return "redirect:/newplayer";
            model.addAttribute("player", player);
        }
        return "index";
	}
	
	@GetMapping("/newplayer")
	public String newPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
        model.addAttribute("profile", oidcUser.getClaims());
        PlayerDTO player = new PlayerDTO();
        player.setAuth0id(oidcUser.getSubject());
        player.setAuth0email(oidcUser.getEmail());
        model.addAttribute("player", player);
        return "newplayer";
    }

	@PostMapping("/newplayer")
	public String postNewPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser, PlayerDTO player) {
        model.addAttribute("profile", oidcUser.getClaims());
        webservice.postPlayerByIdAuth0(player, oidcUser);
        return "redirect:/";
    }
	
	@GetMapping("/diceGames")
	public String diceGames(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "diceGames";
	}
	
	
	
	
	//TODO investigar aquest LOG?
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
        model.addAttribute("profile", oidcUser.getClaims());
        model.addAttribute("profileJson", claimsToJson(oidcUser.getClaims()));
        return "profile";
    }

    private String claimsToJson(Map<String, Object> claims) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(claims);
        } catch (JsonProcessingException jpe) {
            log.error("Error parsing claims to JSON", jpe);
        }
        return "Error parsing claims to JSON.";
    }
	
	
}

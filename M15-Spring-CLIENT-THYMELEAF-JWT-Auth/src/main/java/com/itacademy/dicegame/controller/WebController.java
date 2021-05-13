package com.itacademy.dicegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itacademy.dicegame.dto.PlayerDTO;
import com.itacademy.dicegame.service.WebService;
import com.itacademy.dicegame.utils.AuthenticatorDiceGame;


@Controller
@SessionAttributes("player")
public class WebController {
	/**
	 * Controller View
	 *   
	 */
	@Autowired
	WebService webservice;
	
	@Autowired
	AuthenticatorDiceGame authenticator;

	@ModelAttribute("player")
	public PlayerDTO playerSession() {
	    return new PlayerDTO();
	}
	
	// Landing Page
	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal OidcUser authUser, @ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model,authUser,player);
		}
        return "index";
	}
	
	// Show form for new auth0 acounts that still are not in the Data Base system
	@GetMapping("/newplayer")
	public String newPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
		model.addAttribute("profile", oidcUser.getClaims());
		PlayerDTO player = new PlayerDTO();
		player.setAuth0id(oidcUser.getSubject());
		player.setAuth0email(oidcUser.getEmail());
		model.addAttribute("newplayer", player);
		return "newplayer";
	}

	// Post new player in DB system
	@PostMapping("/newplayer")
	public String postNewPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser, @ModelAttribute("newplayer") PlayerDTO player) {
		model.addAttribute("profile", oidcUser.getClaims());
		webservice.postPlayerByIdAuth0(player, oidcUser);
		model.addAttribute("player", player);
		return "redirect:/";
	}
	
	
	// Check the user profile
	@GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OidcUser authUser, @ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model,authUser,player);
		}
        return "profile";
    }
	
	//game selector	
	@GetMapping("/diceGames")
	public String diceGames(Model model, @AuthenticationPrincipal OidcUser authUser, @ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model,authUser,player);
		}
		return "diceGames";
	}

	
	
}



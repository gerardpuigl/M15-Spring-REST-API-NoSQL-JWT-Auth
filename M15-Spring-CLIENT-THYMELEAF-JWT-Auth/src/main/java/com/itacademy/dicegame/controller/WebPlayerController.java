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
import com.itacademy.dicegame.service.WebPlayerService;
import com.itacademy.dicegame.utils.AuthenticatorDiceGame;

@Controller
@SessionAttributes("player")
public class WebPlayerController {
	/**
	 * Controller View
	 * 
	 */
	@Autowired
	WebPlayerService webservice;

	@Autowired
	AuthenticatorDiceGame authenticator;

	@ModelAttribute("player")
	public PlayerDTO playerSession() {
		return new PlayerDTO();
	}

	// Landing Page
	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		return "index";
	}

	// Show for new auth0 acounts that still are not in the Data Base system
	@GetMapping("/newplayer")
	public String newPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
		model.addAttribute("profile", oidcUser.getClaims());
		PlayerDTO player = new PlayerDTO();
		player.setAuth0id(oidcUser.getSubject());
		player.setAuth0email(oidcUser.getEmail());
		model.addAttribute("newplayer", player);
		return "/player/newplayer";
	}

	// Post new player to API
	@PostMapping("/newplayer")
	public String postNewPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser,
			@ModelAttribute("newplayer") PlayerDTO player) {
		model.addAttribute("profile", oidcUser.getClaims());
		player = webservice.postNewPlayer(player, oidcUser);
		model.addAttribute("player", player);
		return "redirect:/";
	}

	// User profile
	@GetMapping("/profile")
	public String profile(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		model.addAttribute("player", webservice.getPlayerByIdAuth0(authUser));
		return "/player/profile";
	}

	// Edit player form
	@GetMapping("/editplayer")
	public String editplayer(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		return "/player/editplayer";
	}

	// Post updateplayer to API
	@PostMapping("/editplayer")
	public String postEditedPlayer(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("editplayer") PlayerDTO editPlayer,@ModelAttribute("player") PlayerDTO player) {
		model.addAttribute("profile", authUser.getClaims());
		player.setName(editPlayer.getName());
		player.setAnonimus(editPlayer.isAnonimus());
		webservice.updatePlayer(player, authUser);
		model.addAttribute("player", player);
		return "redirect:/profile";
	}
	
	// Delete player to API
	@GetMapping("/deleteplayer")
	public String deleplayer(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
			webservice.deleteplayer(player, authUser);
			model.addAttribute("profile", null);
			model.addAttribute("player", null);
		}
		return "redirect:/logout";
	}

}

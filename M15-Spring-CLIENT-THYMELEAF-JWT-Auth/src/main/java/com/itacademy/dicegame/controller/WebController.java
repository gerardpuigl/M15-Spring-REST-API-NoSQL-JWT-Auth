package com.itacademy.dicegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itacademy.dicegame.dto.DiceGameDTO;
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
	public String home(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
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
		return "/player/newplayer";
	}

	// Post new player in DB system
	@PostMapping("/newplayer")
	public String postNewPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser,
			@ModelAttribute("newplayer") PlayerDTO player) {
		model.addAttribute("profile", oidcUser.getClaims());
		webservice.postNewPlayer(player, oidcUser);
		model.addAttribute("player", player);
		return "redirect:/";
	}

	// Check the user profile
	@GetMapping("/profile")
	public String profile(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		model.addAttribute("player", webservice.getPlayerByIdAuth0(authUser));
		return "/player/profile";
	}

	// edit player menu
	@GetMapping("/editplayer")
	public String editplayer(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		return "/player/editplayer";
	}

	// updateplayer
	@PostMapping("/editplayer")
	public String postEditedPlayer(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("editplayer") PlayerDTO player) {
		model.addAttribute("profile", authUser.getClaims());
		webservice.updatePlayer(player, authUser);
		model.addAttribute("player", player);
		return "redirect:/player/profile";
	}

	// deleteplayer
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

	// game selector
	@GetMapping("/dicegames")
	public String diceGames(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		return "/dicegames/dicegames";
	}

	// start game
	@GetMapping("/dicegames/{gameType}")
	public String onedicegame(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player, @PathVariable("gameType") String gameType) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		model.addAttribute("gameType", gameType);
		model.addAttribute("allgames", webservice.getLast10DiceGames(player, authUser, gameType));
		return "/dicegames/play";
	}

	// throw dices
	@GetMapping("/dicegames/{gameType}/throw")
	public String throwdice(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player, @PathVariable("gameType") String gameType) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		DiceGameDTO dicethrow = webservice.throwonedice(player, authUser, gameType);
		model.addAttribute("gameType", gameType);
		model.addAttribute("lastplay", dicethrow);
		model.addAttribute("player", webservice.getPlayerByIdAuth0(authUser));
		model.addAttribute("allgames", webservice.getLast10DiceGames(player, authUser, gameType));
		return "/dicegames/play";

	}

}

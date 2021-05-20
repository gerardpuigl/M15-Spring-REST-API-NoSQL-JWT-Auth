package com.itacademy.dicegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itacademy.dicegame.dto.DiceGameDTO;
import com.itacademy.dicegame.dto.GameType;
import com.itacademy.dicegame.dto.PlayerDTO;
import com.itacademy.dicegame.service.WebDiceGameService;
import com.itacademy.dicegame.service.WebPlayerService;
import com.itacademy.dicegame.utils.AuthenticatorDiceGame;

@Controller
@SessionAttributes("player")
public class WebDiceGameController {
	/**
	 * Controller View
	 * 
	 */
	@Autowired
	WebPlayerService webPlayerServices;

	@Autowired
	WebDiceGameService webDiceGameServices;

	@Autowired
	AuthenticatorDiceGame authenticator;

	@ModelAttribute("player")
	public PlayerDTO playerSession() {
		return new PlayerDTO();
	}
	
	// Show choose game page
	@GetMapping("/dicegames")
	public String diceGames(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		return "/dicegames/dicegames";
	}
	
	// Delete all player's games for one game type
	@GetMapping("/dicegames/{gameType}/delete")
	public String deletePlayerGamesByType(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player, @PathVariable("gameType") String gameType) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		webDiceGameServices.deletePlayerGamesByType(player, authUser, gameType);
		return "redirect:/profile";
	}

	// Entry in game page
	@GetMapping("/dicegames/{gameType}")
	public String onedicegame(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player, @PathVariable("gameType") String gameType) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		model.addAttribute("gameType", gameType);
		model.addAttribute("allgames", webDiceGameServices.getLast10DiceGames(player, authUser, gameType));
		return "/dicegames/play";
	}

	// Throw dices in game page
	@GetMapping("/dicegames/{gameType}/throw")
	public String throwdice(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player, @PathVariable("gameType") String gameType) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		DiceGameDTO dicethrow = webDiceGameServices.throwonedice(player, authUser, gameType);
		model.addAttribute("gameType", gameType);
		model.addAttribute("lastplay", dicethrow);
		model.addAttribute("player", webPlayerServices.getPlayerByIdAuth0(authUser));
		model.addAttribute("allgames", webDiceGameServices.getLast10DiceGames(player, authUser, gameType));
		return "/dicegames/play";
	}

	// General Stadistics
	@GetMapping("/dicegames/stadistics")
	public String generalStadistics(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		model.addAttribute("oneDiceWinner", webDiceGameServices.getWinner(player, authUser, GameType.OneDiceGame));
		model.addAttribute("oneDiceLoser", webDiceGameServices.getLoser(player, authUser, GameType.OneDiceGame));
		model.addAttribute("oneDiceAverage", webDiceGameServices.getAverage(player, authUser, GameType.OneDiceGame));
		model.addAttribute("twoDiceWinner", webDiceGameServices.getWinner(player, authUser, GameType.TwoDiceGame));
		model.addAttribute("twoDiceLoser", webDiceGameServices.getLoser(player, authUser, GameType.TwoDiceGame));
		model.addAttribute("twoDiceAverage", webDiceGameServices.getAverage(player, authUser, GameType.TwoDiceGame));
		model.addAttribute("threeDiceWinner", webDiceGameServices.getWinner(player, authUser, GameType.ThreeDiceGame));
		model.addAttribute("threeDiceLoser", webDiceGameServices.getLoser(player, authUser, GameType.ThreeDiceGame));
		model.addAttribute("threeDiceAverage", webDiceGameServices.getAverage(player, authUser, GameType.ThreeDiceGame));
		return "/dicegames/stadistics";
	}
	
	// Rankings by GameType
	@GetMapping("/dicegames/{gameType}/ranking")
	public String ranking(Model model, @AuthenticationPrincipal OidcUser authUser,
			@ModelAttribute("player") PlayerDTO player, @PathVariable("gameType") String gameType) {
		if (authUser != null) {
			model = authenticator.checkDataBasePlayer(model, authUser, player);
		}
		model.addAttribute("gameType", gameType);
		model.addAttribute("ranking", webDiceGameServices.getRanking(player, authUser, gameType));
		return "/dicegames/ranking";
	}
	
}

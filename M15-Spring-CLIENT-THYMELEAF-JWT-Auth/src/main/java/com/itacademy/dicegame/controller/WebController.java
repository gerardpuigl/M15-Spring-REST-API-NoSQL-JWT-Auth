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

@Controller
@SessionAttributes("player")
public class WebController {
	/*
	 * Controller View
	 * 
	 * Done with RestTemplate to simulate and independent APP that request info to the Rest API.
	 * 
	 */
	@Autowired
	WebService webservice;

	@ModelAttribute("player")
	public PlayerDTO playerSession() {
	    return new PlayerDTO();
	}
	
	//Landing Page
	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal OidcUser principal, @ModelAttribute("player") PlayerDTO player) {
		if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
            player = webservice.getPlayerByIdAuth0(principal);
            if(player==null) return "redirect:/newplayer";
            model.addAttribute("player", player);
		}
        return "index";
	}
	
	//page new auth0 acounts that still are not in the Data Base system
	@GetMapping("/newplayer")
	public String newPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
		model.addAttribute("profile", oidcUser.getClaims());
        PlayerDTO player = new PlayerDTO();
        player.setAuth0id(oidcUser.getSubject());
        player.setAuth0email(oidcUser.getEmail());
        model.addAttribute("newplayer", player);
        return "newplayer";
    }

	//post new player
	@PostMapping("/newplayer")
	public String postNewPlayer(Model model, @AuthenticationPrincipal OidcUser oidcUser, @ModelAttribute("newplayer") PlayerDTO player) {
        model.addAttribute("profile", oidcUser.getClaims());
        webservice.postPlayerByIdAuth0(player, oidcUser);
        model.addAttribute("player", player);
        return "redirect:/";
    }
	
	//player profile
	@GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OidcUser principal, @ModelAttribute("player") PlayerDTO player) {
		if (principal != null) model.addAttribute("profile", principal.getClaims());
		if (player.getAuth0id() == null) return "redirect:/";
        return "profile";
    }
	
	//game selector	
	@GetMapping("/diceGames")
	public String diceGames(Model model, @AuthenticationPrincipal OidcUser principal, @ModelAttribute("player") PlayerDTO player) {
		if (principal != null) model.addAttribute("profile", principal.getClaims());
		if (player.getAuth0id() == null) return "redirect:/";
		return "diceGames";
	}
}

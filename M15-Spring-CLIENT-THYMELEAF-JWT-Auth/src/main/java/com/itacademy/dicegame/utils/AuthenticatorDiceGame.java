package com.itacademy.dicegame.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.itacademy.dicegame.dto.PlayerDTO;
import com.itacademy.dicegame.service.WebPlayerService;

@Component
public class AuthenticatorDiceGame {
/**
 * 
 * This class check if the authUser_id is equals to player object. If not try to call api for right player object. 
 * If it's not in the MongoDB that means it's the first time that he/she entry in the app and for this case
 * we redirect to newplayer.html via throwing NewPlayerException
 *  
 */	
	@Autowired
	WebPlayerService webservice;

	public Model checkDataBasePlayer(Model model, OidcUser authUser, PlayerDTO player) {
		model.addAttribute("profile", authUser.getClaims());
		try {
			if (player.getAuth0id() == null || player.getId() ==null)
				player = webservice.getPlayerByIdAuth0(authUser);
			if (!player.getAuth0id().equals(authUser.getSubject()))
				player = webservice.getPlayerByIdAuth0(authUser);
		} catch (NullPointerException e) {
			throw new NewPlayerException();
		}
		model.addAttribute("player", player);
		return model;
	}
}

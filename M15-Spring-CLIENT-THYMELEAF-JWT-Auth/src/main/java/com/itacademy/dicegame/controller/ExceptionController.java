package com.itacademy.dicegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itacademy.dicegame.dto.PlayerDTO;
import com.itacademy.dicegame.utils.AuthenticatorDiceGame;
import com.itacademy.dicegame.utils.NewPlayerException;

@ControllerAdvice
@SessionAttributes("player")
public class ExceptionController {
	
	@Autowired
	AuthenticatorDiceGame authenticator;

	@ModelAttribute("player")
	public PlayerDTO playerSession() {
		return new PlayerDTO();
	}
	
	// Take NewPlayerException - More info in utils.NewPlayerException.java
	@ExceptionHandler(NewPlayerException.class)
	public String newPlayer() {
		return "redirect:/newplayer";
	}

	//take general Errors
	@ExceptionHandler(Exception.class)
	public String fullShopApiError(@RequestBody Exception ex, Model model) {
		model.addAttribute("errorCode", ex.getClass().getSimpleName());	
		model.addAttribute("errorMessage", ex.getMessage());
		return "error";
	}
	
}

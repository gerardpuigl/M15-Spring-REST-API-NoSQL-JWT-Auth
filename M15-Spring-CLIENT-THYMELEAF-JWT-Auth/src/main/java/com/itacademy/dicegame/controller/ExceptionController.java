package com.itacademy.dicegame.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.itacademy.dicegame.utils.NewPlayerException;

@ControllerAdvice
public class ExceptionController {
	
	// Take NewPlayerException - More info in utils.NewPlayerException.java
	@ExceptionHandler(NewPlayerException.class)
	public String newPlayer() {
		return "redirect:/newplayer";
	}

}

package com.itacademy.dicegame.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dicegame.domain.Player;

@RestController
@RequestMapping("/players")
public class PlayerController {
	
	@PostMapping("")
	public Player createplayer() {
		return null;
	}
	
	@PutMapping("")
	public Player modifyPlayer() {
		return null;
	}

	@GetMapping("")
	public List<Player> getPlayersList() {
		return null;
	}
	
	@GetMapping("/{id}")
	public Player getPlayerById() {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public String deletePlayerById() {
		return null;
	}
}

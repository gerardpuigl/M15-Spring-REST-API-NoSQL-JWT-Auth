package com.itacademy.dicegame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {
	/*
	 * Controller CRUD Players
	 */
	
	@Autowired
	PlayerService playerService;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)  // 201
	public Player createplayer(@Valid @RequestBody Player player) {
		return playerService.createPlayer(player);
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

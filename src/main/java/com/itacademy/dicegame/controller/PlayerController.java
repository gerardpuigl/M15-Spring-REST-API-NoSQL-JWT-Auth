package com.itacademy.dicegame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//create new Player
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)  // 201
	public Player createplayer(@Valid @RequestBody Player player) {
		return playerService.createPlayer(player);
	}
	
	//modify Player
	@PutMapping("")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	public Player modifyPlayer(@Valid @RequestBody Player player) {
		return playerService.modifyPlayer(player);
	}

	//get Player List
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)  // 200
	public List<Player> getPlayersList() {
		return playerService.getAll();
	}
	
	//get Player by id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)  // 200
	public Player getPlayerById(@PathVariable("id") int idPlayer) {
		return playerService.getPlayerById(idPlayer);
	}
		
	//delete Player by id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	public String deletePlayerById(@PathVariable("id") int idPlayer) {
		return playerService.deleteById(idPlayer);
	}
}

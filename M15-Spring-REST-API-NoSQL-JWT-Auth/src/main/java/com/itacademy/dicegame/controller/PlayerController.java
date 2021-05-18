package com.itacademy.dicegame.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.domain.player.PlayerDTO;
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
	@PreAuthorize("hasAuthority('create:player')")
	public PlayerDTO createplayer(@Valid @RequestBody Player player) {
		return playerService.createPlayer(player);
	}
	
	//modify Player
	@PutMapping("")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	@PreAuthorize("hasAuthority('update:player')")
	public PlayerDTO modifyPlayer(@Valid @RequestBody Player player) {
		return playerService.modifyPlayer(player);
	}

	//get Player List
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)  // 200
	@PreAuthorize("hasAuthority('get:player')")
	public List<PlayerDTO> getPlayersList() {
		return playerService.getAll();
	}
	
	//get Player by id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)  // 200
	@PreAuthorize("hasAuthority('get:player')")
	public PlayerDTO getPlayerById(@PathVariable("id") UUID idPlayer) {
		return playerService.getPlayerById(idPlayer);
	}
	
	//get Player by auth0_id
	@GetMapping("/auth0/{id}")
	@ResponseStatus(HttpStatus.OK)  // 200
	@PreAuthorize("hasAuthority('get:player')")
	public Player getPlayerByAuth0_id(@PathVariable("id") String auth0_id) {
		return playerService.getPlayerByAuthid(auth0_id);
	}
	
	//delete Player by id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	@PreAuthorize("hasAuthority('delete:player')")
	public String deletePlayerById(@PathVariable("id") UUID idPlayer) {
		return playerService.deleteById(idPlayer);
	}
}

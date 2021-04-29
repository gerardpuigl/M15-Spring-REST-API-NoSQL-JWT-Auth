package com.itacademy.dicegame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.service.DiceGameService;

@RestController
@RequestMapping("/players/{id}")
public class DiceGameController {
	/*
	 * Controller CRUD dice plays/games
	 */
	
	@Autowired
	private DiceGameService diceGameService;
	
	public DiceGameController() {
	}
	
	//add new throw the dices
	@PostMapping("/games")
	@ResponseStatus(HttpStatus.CREATED)  // 201
	@PreAuthorize("hasAuthority('create:game')")
	public DiceGame throwTheDices(@PathVariable("id") String idPlayer) {
		return diceGameService.newGame(idPlayer);
	}
	
	//get all player's games
	@GetMapping("/games")
	@ResponseStatus(HttpStatus.OK)  // 200
	@PreAuthorize("hasAuthority('get:game')")
	public List<DiceGame> getAllGames(@PathVariable("id") String idPlayer) {
		return diceGameService.getAllGames(idPlayer);
	}
	
	//delete all player's games
	@DeleteMapping("/games")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	@PreAuthorize("hasAuthority('delete:game')")
	public String deleteAllGames(@PathVariable("id") String idPlayer) {
		return diceGameService.deleteAllGames(idPlayer);
	}
	
}

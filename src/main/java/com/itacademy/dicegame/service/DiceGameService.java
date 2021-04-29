package com.itacademy.dicegame.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.DiceGameRepository;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class DiceGameService {
	
	@Autowired
	private DiceGameRepository diceGameRepository;

	@Autowired
	private PlayerRepository playerRepository;
	
	// create new game and play
	public DiceGame newGame(UUID idPlayer) {
		Player player = playerRepository.findById(idPlayer).get();
		DiceGame game=new DiceGame(player);
		diceGameRepository.save(game);
		player.addGame(game);
		player.setWinPercentage();
		playerRepository.save(player);
		return game;
	}

	// get all games from a player
	public List<DiceGame> getAllGames(UUID idPlayer) {
		return diceGameRepository.findByPlayer_idIs(idPlayer);
	}

	// delete all games from a player
	public String deleteAllGames(UUID idPlayer) {
		Player player = playerRepository.findById(idPlayer).get();
		diceGameRepository.deleteAll(getAllGames(idPlayer));
		player.getDiceGameList().clear();
		player.setWinPercentage();
		playerRepository.save(player);
		return "S'han eliminat totes les partides del jugador amb id: " + idPlayer;
	}
}

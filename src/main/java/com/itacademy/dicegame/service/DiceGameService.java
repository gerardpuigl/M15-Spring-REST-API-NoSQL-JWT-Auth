package com.itacademy.dicegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.persistence.DiceGameRepository;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class DiceGameService {
	
	@Autowired
	DiceGameRepository gamesRepository;

	@Autowired
	PlayerRepository playerRepository;
	
	// create new game and play
	public DiceGame newGame(int idPlayer) {
		DiceGame game=new DiceGame(playerRepository.findById(idPlayer).get());
		gamesRepository.save(game);
		return game;
	}
}

package com.itacademy.dicegame.persistence;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.dicegame.domain.diceGame.DiceGame;
import com.itacademy.dicegame.domain.diceGame.DiceGameFactory;
import com.itacademy.dicegame.domain.diceGame.GameType;
import com.itacademy.dicegame.domain.player.Player;

@Component
public class InitialData {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private DiceGameRepository diceGameRepository;

	@Autowired
	private DiceGameFactory diceGameFactory;


	// populate the repository if it's empty
	@PostConstruct
	public void populateIfEmpty() {
		if (playerRepository.findAll().size() == 0) {
			createTestPlayer("TestPlayer1", 10);
			createTestPlayer("TestPlayer2", 7);
			createTestPlayer("TestPlayer3", 15);
			// createTestPlayer("Average",300);
		}
		;
	}

	// create un player and "x" random games
	private void createTestPlayer(String name, int initialGames) {
		Player player = new Player(name);
		playerRepository.save(player);

		for (int i = 0; i < initialGames; i++) {
			DiceGame game = diceGameFactory.getGame(player, GameType.OneDiceGame);
			diceGameRepository.save(game);
			player.addGame(game);
		}
		for (int i = 0; i < initialGames; i++) {
			DiceGame game = diceGameFactory.getGame(player, GameType.TwoDiceGame);
			diceGameRepository.save(game);
			player.addGame(game);
		}
		for (int i = 0; i < initialGames; i++) {
			DiceGame game = diceGameFactory.getGame(player, GameType.ThreeDiceGame);
			diceGameRepository.save(game);
			player.addGame(game);
		}
		player.setWinPercentage();
		playerRepository.save(player);
	}
}

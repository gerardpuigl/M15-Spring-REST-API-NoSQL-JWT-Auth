package com.itacademy.dicegame.service;

import java.util.List;
import java.util.UUID;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.diceGame.DiceGame;
import com.itacademy.dicegame.domain.diceGame.DiceGameFactory;
import com.itacademy.dicegame.domain.diceGame.TwoDiceGame;
import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.persistence.DiceGameRepository;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class DiceGameService {
	
	@Autowired
	private DiceGameRepository diceGameRepository;

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private DiceGameFactory diceGameFactory;
	
	// create new game and play
	public DiceGame newGame(UUID idPlayer, String typeGame) {
		Player player = playerRepository.findById(idPlayer).get();
		DiceGame game = diceGameFactory.getGame(player, typeGame);
		diceGameRepository.save(game);
		player.addGame((TwoDiceGame) game);
		player.setWinPercentage();
		playerRepository.save(player);
		return game;
	}

	// get all games from a player
	public List<DiceGame> getAllGames(UUID idPlayer, String typeGame) {
		return diceGameRepository.findByPlayer_idIs(idPlayer);
	}

	// delete all games from a player
	public String deleteAllGames(UUID idPlayer, String typeGame) {
		Player player = playerRepository.findById(idPlayer).get();
		diceGameRepository.deleteAll(getAllGames(idPlayer,typeGame));
		player.getDiceGameList().clear();
		player.setWinPercentage();
		playerRepository.save(player);
		return "S'han eliminat totes les partides del jugador amb id: " + idPlayer;
	}
	
	// return total Win Average
	public double getPlayersWinPercentage(String typeGame) {		
		double winPercentage = 0;
		List<DiceGame> diceGameList = diceGameRepository.findAll();
		if(diceGameList != null) {
			double wins = diceGameList.stream().filter(dg -> dg.getResult()==true).count();
			double total = diceGameList.size();
			winPercentage = (wins/total)*100;
		}
		return DoubleRounder.round(winPercentage, 2);
	}

	// get player with worse win percentage
	public Player getLoser(String typeGame) {
		return playerRepository.findTopByOrderByWinPercentage();
	}
	
	// get player the best win percentage
	public Player getWinner(String typeGame) {
		return playerRepository.findTopByOrderByWinPercentageDesc();
	}
}

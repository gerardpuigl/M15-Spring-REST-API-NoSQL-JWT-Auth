package com.itacademy.dicegame.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.diceGame.DiceGame;
import com.itacademy.dicegame.domain.diceGame.DiceGameFactory;
import com.itacademy.dicegame.domain.diceGame.GameType;
import com.itacademy.dicegame.domain.diceGame.OneDiceGame;
import com.itacademy.dicegame.domain.diceGame.ThreeDiceGame;
import com.itacademy.dicegame.domain.diceGame.TwoDiceGame;
import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.domain.player.PlayerDTO;
import com.itacademy.dicegame.persistence.DiceGameRepository;
import com.itacademy.dicegame.persistence.PlayerRepository;
import com.itacademy.dicegame.utils.CalculateWinPercentage;
import com.itacademy.dicegame.utils.DTOConverter;

@Service
public class DiceGameService {
	
	@Autowired
	private DiceGameRepository diceGameRepository;

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private DTOConverter dtoConverter;
	
	@Autowired
	private DiceGameFactory diceGameFactory;
	
	@Autowired
	private CalculateWinPercentage calculateWinPercentage;
	
	// create new game and play
	public DiceGame newGame(UUID idPlayer, String typeGame) {
		Player player = playerRepository.findById(idPlayer).get();
		DiceGame game = diceGameFactory.getGame(player, typeGame);
		diceGameRepository.save(game);
		player.addGame(game);
		player.setWinPercentage();
		playerRepository.save(player);
		return game;
	}

	// get all games from a player
	public List<DiceGame> getAllGames(UUID idPlayer, String typeGame) {
		
		List<DiceGame> diceGameList = diceGameRepository.findByPlayer_idIs(idPlayer);
		
		if (typeGame.equals(GameType.OneDiceGame)) {
			diceGameList = diceGameList.stream().filter(dg -> dg.getClass() == OneDiceGame.class).collect(Collectors.toList());
		} else if (typeGame.equals(GameType.TwoDiceGame)) {
			diceGameList = diceGameList.stream().filter(dg -> dg.getClass() == TwoDiceGame.class).collect(Collectors.toList());
		} else if (typeGame.equals(GameType.ThreeDiceGame)) {
			diceGameList = diceGameList.stream().filter(dg -> dg.getClass() == ThreeDiceGame.class).collect(Collectors.toList());
		}
		return diceGameList;
	}

	// delete all games from a player
	public String deleteAllGames(UUID idPlayer, String typeGame) {
		Player player = playerRepository.findById(idPlayer).get();
		diceGameRepository.deleteAll(getAllGames(idPlayer,typeGame));
		//player.getDiceGameList().clear();
		player.setWinPercentage();
		playerRepository.save(player);
		return "S'han eliminat totes les partides del jugador amb id: " + idPlayer;
	}
	
	// return total Win Average
	public double getPlayersWinPercentage(String typeGame) {

		List<DiceGame> diceGameList = diceGameRepository.findAll();

		if (typeGame.equals(GameType.OneDiceGame)) {
			diceGameList = diceGameList.stream().filter(dg -> dg.getClass() == OneDiceGame.class).collect(Collectors.toList());
		} else if (typeGame.equals(GameType.TwoDiceGame)) {
			diceGameList = diceGameList.stream().filter(dg -> dg.getClass() == TwoDiceGame.class).collect(Collectors.toList());
		} else if (typeGame.equals(GameType.ThreeDiceGame)) {
			diceGameList = diceGameList.stream().filter(dg -> dg.getClass() == ThreeDiceGame.class).collect(Collectors.toList());
		}
		return calculateWinPercentage.fromList(diceGameList);
	}

	// get player with worse win percentage
	public PlayerDTO getLoser(String typeGame) {
		Player player = new Player();
		
		if (typeGame.equals(GameType.OneDiceGame)) {
			player = playerRepository.findTopByOrderByWinPercentageOneDice();
		} else if (typeGame.equals(GameType.TwoDiceGame)) {
			player = playerRepository.findTopByOrderByWinPercentageTwoDice();
		} else if (typeGame.equals(GameType.ThreeDiceGame)) {
			player = playerRepository.findTopByOrderByWinPercentageThreeDice();
		}
		return dtoConverter.convertToDto(player);
	}
	
	// get player the best win percentage
	public PlayerDTO getWinner(String typeGame) {
		Player player = new Player();
		if (typeGame.equals(GameType.OneDiceGame)) {
			player = playerRepository.findTopByOrderByWinPercentageOneDiceDesc();
		} else if (typeGame.equals(GameType.TwoDiceGame)) {
			player = playerRepository.findTopByOrderByWinPercentageTwoDiceDesc();
		} else if (typeGame.equals(GameType.ThreeDiceGame)) {
			player = playerRepository.findTopByOrderByWinPercentageThreeDiceDesc();
		}
		return dtoConverter.convertToDto(player);
	}
	
	// get player rankings
	public List<PlayerDTO> getRanking(String typeGame) {
		List<PlayerDTO> playerRanking= null;
		if (typeGame.equals(GameType.OneDiceGame)) {
			playerRanking = playerRepository.findByOrderByWinPercentageOneDiceDesc()
					.stream().map(player->dtoConverter.convertToDto(player))
					.collect(Collectors.toList());
		} else if (typeGame.equals(GameType.TwoDiceGame)) {
			playerRanking = playerRepository.findByOrderByWinPercentageTwoDiceDesc()
					.stream().map(player->dtoConverter.convertToDto(player))
					.collect(Collectors.toList());
		} else if (typeGame.equals(GameType.ThreeDiceGame)) {
			playerRanking = playerRepository.findByOrderByWinPercentageThreeDiceDesc()
					.stream().map(player->dtoConverter.convertToDto(player))
					.collect(Collectors.toList());
		}		
		return playerRanking;
	}
}

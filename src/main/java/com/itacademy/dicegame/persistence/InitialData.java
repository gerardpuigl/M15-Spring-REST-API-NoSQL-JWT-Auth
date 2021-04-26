package com.itacademy.dicegame.persistence;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.domain.Player;

@Component
public class InitialData {

		@Autowired
		private PlayerRepository playerRepository;

		@Autowired
		private DiceGameRepository diceGameRepository;
		
	    @PostConstruct
	    public void populateIfEmpty() {
	    	if(playerRepository.findAll().size() == 0) {
	    		Player player1 = new Player("TestPlayer1");
	    		playerRepository.save(player1);
	    		for (int i = 0; i < 10; i++) {
	    			DiceGame game = new DiceGame(player1);
	    			diceGameRepository.save(game);
					player1.addGame(game);
				}
	    		player1.setWinPercentage();
	    		playerRepository.save(player1);
	    		
	    		Player player2 = new Player("TestPlayer2");
	    		playerRepository.save(player2);	    		
	    		for (int i = 0; i < 7; i++) {
	    			DiceGame game = new DiceGame(player2);
	    			diceGameRepository.save(game);
					player2.addGame(game);
				}
	    		player2.setWinPercentage();
	    		playerRepository.save(player2);
	    		
	    		Player player3 = new Player("TestPlayer3");
	    		playerRepository.save(player3);
	    		for (int i = 0; i < 15; i++) {
	    			DiceGame game = new DiceGame(player3);
	    			diceGameRepository.save(game);
					player3.addGame(game);
				}
	    		player3.setWinPercentage();
	    		playerRepository.save(player3);
	    	};
	    }
	
}

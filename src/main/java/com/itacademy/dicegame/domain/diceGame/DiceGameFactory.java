package com.itacademy.dicegame.domain.diceGame;

import org.springframework.stereotype.Component;

import com.itacademy.dicegame.domain.player.Player;

@Component
public class DiceGameFactory {
	
	public DiceGameFactory() {
	}

	public DiceGame getGame(Player player, String typeGame) {
		if (typeGame.equals("OneDiceGame")) {
			return new OneDiceGame(player);
		} else if (typeGame.equals("TwoDiceGame")) {
			return new TwoDiceGame(player);
		} else if (typeGame.equals("ThreeDiceGame")) {
			return new ThreeDiceGame(player);
		}
		return null;
	}
}

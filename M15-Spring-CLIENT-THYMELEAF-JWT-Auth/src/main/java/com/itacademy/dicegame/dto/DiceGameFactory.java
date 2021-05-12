package com.itacademy.dicegame.dto;

import org.springframework.stereotype.Component;

@Component
public class DiceGameFactory {
	
	public DiceGameFactory() {
	}

	public DiceGameDTO getGame(PlayerDTO player, String typeGame) {
		if (typeGame.equals(GameType.OneDiceGame)) {
			return new OneDiceGame();
		} else if (typeGame.equals(GameType.TwoDiceGame)) {
			return new TwoDiceGame();
		} else if (typeGame.equals(GameType.ThreeDiceGame)) {
			return new ThreeDiceGame();
		}
		return null;
	}
}

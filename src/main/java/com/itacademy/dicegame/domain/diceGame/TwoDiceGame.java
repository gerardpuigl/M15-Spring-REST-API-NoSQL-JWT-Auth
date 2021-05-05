package com.itacademy.dicegame.domain.diceGame;

import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.NoRepositoryBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itacademy.dicegame.domain.player.Player;

@NoRepositoryBean
public class TwoDiceGame extends DiceGame {

	// First Dice Roll
	private int firstRoll;

	// Second Dice Roll
	private int secondRoll;

	// Result (boolean)
	private boolean result;
	
	@Transient
	@JsonIgnore
	private Dice dice = new Dice();
	
	public TwoDiceGame() {
	}
	
	public TwoDiceGame(Player player) {
		super(player);
		firstRoll = dice.roll();
		secondRoll = dice.roll();
		result = result();
	}

	private boolean result() {
		if(firstRoll+secondRoll==7) return true;
		return false;
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public boolean getResult() {
		return result;
	}
}

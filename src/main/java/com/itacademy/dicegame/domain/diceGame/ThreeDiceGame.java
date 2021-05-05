package com.itacademy.dicegame.domain.diceGame;

import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.NoRepositoryBean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itacademy.dicegame.domain.player.Player;

@NoRepositoryBean
public class ThreeDiceGame extends DiceGame {

	
	// First Dice Roll
	private int firstRoll;

	// Second Dice Roll
	private int secondRoll;
	
	// Third Dice Roll
	private int thridRoll;
	
	// Result (boolean)
	private boolean result;
	
	@Transient
	@JsonIgnore
	private Dice dice = new Dice();
	
	public ThreeDiceGame() {
	}
	
	public ThreeDiceGame(Player player) {
		super(player);
		firstRoll = dice.roll();
		secondRoll = dice.roll();
		result = result();
	}

	private boolean result() {
		if(firstRoll+secondRoll+thridRoll==7) return true;
		if(firstRoll+secondRoll+thridRoll==14) return true;
		return false;
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public int getThirdRoll() {
		return thridRoll;
	}
	
	public boolean getResult() {
		return result;
	}

}

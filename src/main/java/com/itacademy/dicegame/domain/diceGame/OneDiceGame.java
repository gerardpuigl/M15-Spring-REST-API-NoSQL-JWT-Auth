package com.itacademy.dicegame.domain.diceGame;


import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.NoRepositoryBean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itacademy.dicegame.domain.player.Player;

@NoRepositoryBean
public class OneDiceGame extends DiceGame{

	// First Dice Roll
	private int firstRoll;

	// Result (boolean)
	private boolean result;

	@Transient
	@JsonIgnore
	private Dice dice = new Dice();
	
	public OneDiceGame() {
	}
	
	public OneDiceGame(Player player) {
		super(player);
		firstRoll = dice.roll();
		result = result();
		
	}

	private boolean result() {
		if(firstRoll==6) return true;
		return false;
	}

			
	public int getFirstRoll() {
		return firstRoll;
	}

	public boolean getResult() {
		return result;
	}
}

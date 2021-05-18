package com.itacademy.dicegame.domain.diceGame;

import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.NoRepositoryBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itacademy.dicegame.domain.player.Player;

@NoRepositoryBean
public class TwoDiceGame extends DiceGame {

	//Array with dice rolls
	private int[] rolls= new int[2];

	// Result (boolean)
	private boolean result;
	
	@Transient
	@JsonIgnore
	private Dice dice = new Dice();
	
	public TwoDiceGame() {
	}
	
	public TwoDiceGame(Player player) {
		super(player);
		rolls[0] = dice.roll();
		rolls[1] = dice.roll();
		result = result();
	}

	private boolean result() {
		if(rolls[0]+rolls[1]==7) return true;
		return false;
	}

	public int[] getRolls() {
		return rolls;
	}

	public void setRolls(int[] rolls) {
		this.rolls = rolls;
	}

	public boolean getResult() {
		return result;
	}
}

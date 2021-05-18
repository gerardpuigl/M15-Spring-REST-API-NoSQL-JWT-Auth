package com.itacademy.dicegame.dto;


public class OneDiceGame extends DiceGameDTO{

	// First Dice Roll
	private int[] rolls;
	
	// Result (boolean)
	private boolean result;
	
	public OneDiceGame() {
	}
	
	public int[] getRolls() {
		return rolls;
	}

	public void setRolls(int[] rolls) {
		this.rolls = rolls;
	}	
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	

}

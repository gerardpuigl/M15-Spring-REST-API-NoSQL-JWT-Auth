package com.itacademy.dicegame.dto;


public class TwoDiceGame extends DiceGameDTO {

	private int[] rolls;

	private boolean result;
	
	public TwoDiceGame() {
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

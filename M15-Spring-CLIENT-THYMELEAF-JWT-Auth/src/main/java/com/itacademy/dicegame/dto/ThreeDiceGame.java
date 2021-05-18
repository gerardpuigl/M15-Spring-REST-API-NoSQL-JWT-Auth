package com.itacademy.dicegame.dto;


public class ThreeDiceGame extends DiceGameDTO {

	private int[] rolls;
	
	private boolean result;
	
	public ThreeDiceGame() {
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

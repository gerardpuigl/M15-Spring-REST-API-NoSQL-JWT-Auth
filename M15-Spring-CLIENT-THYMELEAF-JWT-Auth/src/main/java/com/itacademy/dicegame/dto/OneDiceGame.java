package com.itacademy.dicegame.dto;


public class OneDiceGame extends DiceGameDTO{

	// First Dice Roll
	private int firstRoll;

	// Result (boolean)
	private boolean result;
	
	public OneDiceGame() {
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public void setFirstRoll(int firstRoll) {
		this.firstRoll = firstRoll;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	

}

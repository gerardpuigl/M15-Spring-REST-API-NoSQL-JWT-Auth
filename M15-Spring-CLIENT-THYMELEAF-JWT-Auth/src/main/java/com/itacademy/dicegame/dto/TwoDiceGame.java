package com.itacademy.dicegame.dto;


public class TwoDiceGame extends DiceGameDTO {

	private int firstRoll;

	private int secondRoll;

	private boolean result;
	
	public TwoDiceGame() {
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public void setFirstRoll(int firstRoll) {
		this.firstRoll = firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public void setSecondRoll(int secondRoll) {
		this.secondRoll = secondRoll;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}

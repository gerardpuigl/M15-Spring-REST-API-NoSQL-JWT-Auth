package com.itacademy.dicegame.dto;


public class ThreeDiceGame extends DiceGameDTO {

	private int firstRoll;

	private int secondRoll;
	
	private int thridRoll;
	
	private boolean result;
	
	public ThreeDiceGame() {
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

	public int getThridRoll() {
		return thridRoll;
	}

	public void setThridRoll(int thridRoll) {
		this.thridRoll = thridRoll;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}

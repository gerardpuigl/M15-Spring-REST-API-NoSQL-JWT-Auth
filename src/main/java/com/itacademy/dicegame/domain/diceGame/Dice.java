package com.itacademy.dicegame.domain.diceGame;

import java.util.Random;

public class Dice {

	private int sides;

	private Random random = new Random();

	// default dice 6 sides
	public Dice() {
		sides = 6;
	}

	// other dices
	public Dice(int sides) throws Exception {
		if (sides <= 1)
			throw new Exception("the minimal dice sides are 2");
		this.sides = sides;
	}

	public int getSides() {
		return sides;
	}

	public int roll() {
		return random.nextInt(sides) + 1;
	}
}

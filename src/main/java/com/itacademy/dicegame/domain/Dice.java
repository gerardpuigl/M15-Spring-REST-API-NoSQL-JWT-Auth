package com.itacademy.dicegame.domain;

import java.util.Random;

import javassist.tools.reflect.CannotCreateException;

public class Dice {

	private int sides;
	
	private Random random=new Random();
	
	//default dice 6 sides
	public Dice() {
		sides=6;
	}
	
	//other dices
	public Dice(int sides) throws CannotCreateException {
		if(sides<=1) throw new CannotCreateException("the minimal dice sides are 2");
		this.sides=sides;		
	}
		
	public int getSides() {
		return sides;
	}

	public int roll() {
		return random.nextInt(sides)+1;
	}
}

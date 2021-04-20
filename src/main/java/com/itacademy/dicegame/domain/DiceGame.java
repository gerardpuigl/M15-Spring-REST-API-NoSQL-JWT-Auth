package com.itacademy.dicegame.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class DiceGame {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="diceGame_id")
	private int id;
	
	//TODO First Dice Roll (Dice need to be created)
	
	//TODO Second Dice Roll (Dice need to be created)
	
	//TODO Result (boolean)
	
	//TODO Player who played
}

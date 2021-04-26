package com.itacademy.dicegame.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Document(collection = "DiceGames")
public class DiceGame {

	@Id
	@NotNull
	private String id;

	// First Dice Roll
	private int firstRoll;

	// Second Dice Roll
	private int secondRoll;

	// Result (boolean)
	private boolean result;
	
	@CreatedDate
	private Date creationDate;

	// Player who played
	@DBRef
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //retorna només id
    @JsonIdentityReference(alwaysAsId = true) //retorna només id
	private Player player;

	@Transient
	@JsonIgnore
	private Dice dice = new Dice();
	
	public DiceGame() {
		firstRoll = dice.roll();
		secondRoll = dice.roll();
		result = result();
	}
	
	public DiceGame(Player player) {
		firstRoll = dice.roll();
		secondRoll = dice.roll();
		result = result();
		this.player=player;
	}

	private boolean result() {
		if(firstRoll+secondRoll==7) return true;
		return false;
	}

	public String getId() {
		return id;
	}
	
	public int getFirstRoll() {
		return firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public boolean getResult() {
		return result;
	}

	public Player getPlayer() {
		return player;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}

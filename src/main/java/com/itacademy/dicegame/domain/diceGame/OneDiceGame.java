package com.itacademy.dicegame.domain.diceGame;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.itacademy.dicegame.domain.player.Player;

@Document(collection = "OneDiceGames")
public class OneDiceGame implements DiceGame{

	@Id
	@NotNull
	private UUID id;

	// First Dice Roll
	private int firstRoll;

	// Result (boolean)
	private boolean result;
	
	@CreatedDate
	private Date creationDate;

	@Version 
	private int version;
	
	// Player who played
	@DBRef
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //retorna només id
    @JsonIdentityReference(alwaysAsId = true) //retorna només id
	private Player player;

	@Transient
	@JsonIgnore
	private Dice dice = new Dice();
	
	public OneDiceGame() {
	}
	
	public OneDiceGame(Player player) {
		firstRoll = dice.roll();
		result = result();
		this.player=player;
	}

	private boolean result() {
		if(firstRoll==6) return true;
		return false;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
		
	public int getFirstRoll() {
		return firstRoll;
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
	
	@JsonIgnore
	public boolean isNew() {
		return (getId() == null);
	}
}

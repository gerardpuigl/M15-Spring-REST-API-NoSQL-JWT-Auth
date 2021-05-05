package com.itacademy.dicegame.domain.diceGame;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.itacademy.dicegame.domain.player.Player;

@Document(collection = "DiceGame")
public class DiceGame {
	
	@Id
	@NotNull
	private UUID id;

	@CreatedDate
	private Date creationDate;

	@Version 
	private int version;
	
	// Player who played
	@DBRef
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //retorna només id
    @JsonIdentityReference(alwaysAsId = true) //retorna només id
	private Player player;


	public DiceGame() {
	}
	
	public DiceGame(Player player) {
		this.player=player;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	public boolean getResult() {
		return false;
	}
	
	@JsonIgnore
	public boolean isNew() {
		return (getId() == null);
	}
	
}

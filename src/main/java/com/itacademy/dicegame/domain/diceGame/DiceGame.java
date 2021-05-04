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

@Document
public interface DiceGame {

	public UUID getId();
	
	public void setId(UUID id);
		
	public int getFirstRoll();
	
	public boolean getResult();
	
	public Player getPlayer();

	public Date getCreationDate();
	
	@JsonIgnore
	public boolean isNew();
}

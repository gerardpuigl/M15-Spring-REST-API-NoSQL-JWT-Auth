package com.itacademy.dicegame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.decimal4j.util.DoubleRounder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Players")
public class Player {

	@Id
	private UUID id;

	private String name;

	@CreatedDate
	private Date creationDate;

	@Version 
	private int version;
	
	// % Success Games
	private double winPercentage;

	// List of the DiceGames played List<DiceGame>
	@DBRef
	private List<DiceGame> diceGameList = new ArrayList<DiceGame>();

	public Player() {
	}

	public Player(String name) {
		if (name.equals("") || name == null) {
			this.name = "ANÒNIM";
		} else {
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.equals("") || name == null) {
			this.name = "ANÒNIM";
		} else {
			this.name = name;
		}
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	@JsonIgnore
	public List<DiceGame> getDiceGameList() {
		return diceGameList;
	}

	public void addGame(DiceGame game) {
		diceGameList.add(game);
	}

	public void setWinPercentage() {
		double winPercentage;
		if (diceGameList != null) {
			double wins = diceGameList.stream().filter(dg -> dg.getResult() == true).count();
			double total = diceGameList.size();
			winPercentage = (wins / total) * 100;
		} else {
			winPercentage = 0;
		}
		this.winPercentage = DoubleRounder.round(winPercentage, 2);
	}

	public double getWinPercentage() {
		return this.winPercentage;
	}

	@JsonIgnore
	public boolean isNew() {
		return (getId() == null);
	}

}

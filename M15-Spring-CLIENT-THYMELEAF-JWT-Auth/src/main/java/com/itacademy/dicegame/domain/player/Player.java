package com.itacademy.dicegame.domain.player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itacademy.dicegame.domain.diceGame.DiceGame;
import com.itacademy.dicegame.domain.diceGame.OneDiceGame;
import com.itacademy.dicegame.domain.diceGame.ThreeDiceGame;
import com.itacademy.dicegame.domain.diceGame.TwoDiceGame;


@Document(collection = "Players")
public class Player {

	
	@Id
	private UUID id;

	@NotBlank
	private String name;

	private boolean anonimus;

	@CreatedDate
	private Date creationDate;

	@Version
	private int version;

	// % Success Games
	private double winPercentageOneDice;

	private double winPercentageTwoDice;

	private double winPercentageThreeDice;

	// List of the DiceGames played List<DiceGame>
	@DBRef(lazy = true)
	private List<DiceGame> diceGameList = new ArrayList<DiceGame>();

	public Player() {
	}

	public Player(String name) {
		this.name = name;
		anonimus = false;
	}

	public Player(String name, boolean anonimus) {
		this.name = name;
		this.anonimus = anonimus;
	}

	public String getName() {
		if (anonimus) {
			return "anònim";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAnonimus(boolean anonimus) {
		this.anonimus = anonimus;
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


	}

	public double getWinPercentageOneDice() {
		return winPercentageOneDice;
	}

	public double getWinPercentageTwoDice() {
		return winPercentageTwoDice;
	}

	public double getWinPercentageThreeDice() {
		return winPercentageThreeDice;
	}

	@JsonIgnore
	public boolean isNew() {
		return (getId() == null);
	}

}

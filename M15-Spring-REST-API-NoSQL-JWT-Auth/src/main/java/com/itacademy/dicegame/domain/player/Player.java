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
import com.itacademy.dicegame.utils.CalculateWinPercentage;

@Document(collection = "Players")
public class Player {

	CalculateWinPercentage calculateWinPercentage = new CalculateWinPercentage();
	
	@Id
	private UUID id;


	private String auth0id;
	

	private String auth0email;	
	
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAnonimus(boolean anonimus) {
		this.anonimus = anonimus;
	}

	public boolean isAnonimus() {
		return anonimus;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAuth0id() {
		return auth0id;
	}

	public String getAuth0email() {
		return auth0email;
	}

	public void setAuth0id(String auth0id) {
		this.auth0id = auth0id;
	}

	public void setAuth0email(String auth0email) {
		this.auth0email = auth0email;
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

		List<DiceGame> oneDiceGameList = diceGameList.stream().filter(dg -> dg.getClass() == OneDiceGame.class).collect(Collectors.toList());
		List<DiceGame> twoDiceGameList = diceGameList.stream().filter(dg -> dg.getClass() == TwoDiceGame.class).collect(Collectors.toList());
		List<DiceGame> threeDiceGameList = diceGameList.stream().filter(dg -> dg.getClass() == ThreeDiceGame.class).collect(Collectors.toList());

		winPercentageOneDice = calculateWinPercentage.fromList(oneDiceGameList);
		winPercentageTwoDice = calculateWinPercentage.fromList(twoDiceGameList);
		winPercentageThreeDice = calculateWinPercentage.fromList(threeDiceGameList);
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

package com.itacademy.dicegame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.decimal4j.util.DoubleRounder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
//@Table(name="Players")
@Document(collection = "Players")
public class Player {

	@Id
	private String id;
	
	@NotBlank
	private String name;
	
	@CreatedDate
	private Date creationDate;
	
	//% Success Games
	private double winPercentage;
	
	//List of the DiceGames played List<DiceGame>
	@DBRef
	private List<DiceGame> diceGameList = new ArrayList<DiceGame>();

	public Player() {
		this.name="ANÒNIM";
	}
	
	public Player(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate=creationDate;
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
		if(diceGameList != null) {
			double wins = diceGameList.stream().filter(dg -> dg.getResult()==true).count();
			double total = diceGameList.size();
			winPercentage = (wins/total)*100;
		}else {
			winPercentage=0;
		}
		this.winPercentage =  DoubleRounder.round(winPercentage, 2);
	}
	
	public double getWinPercentage() {
		return this.winPercentage;
	}
	
}

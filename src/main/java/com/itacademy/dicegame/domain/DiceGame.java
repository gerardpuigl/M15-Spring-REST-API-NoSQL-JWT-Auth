package com.itacademy.dicegame.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="DiceGames")
public class DiceGame {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diceGame_id")
	private int id;

	// First Dice Roll
	@Column(name="firstRoll")
	private int firstRoll;

	// Second Dice Roll
	@Column(name="secondRoll")
	private int secondRoll;

	// Result (boolean)
	@Column(name="result")
	private boolean result;
	
	@CreationTimestamp
	@Column(name="picture_registrationdate",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private Date creationDate;

	// Player who played
	@ManyToOne
	@JoinColumn(name = "player_id")
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

	public int getId() {
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

package com.itacademy.dicegame.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name="Players")
public class Player {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="player_id")
	private int id;
	
	@NotBlank
	@Column(name="name")
	private String name;
	
	@CreationTimestamp
	@Column(name="picture_registrationdate",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private Date creationDate;
	
	//TODO % Success Games
	
	//TODO List of the DiceGames played List<DiceGame>
	@Transient
	private List<DiceGame> DiceGameList;
	
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

	public int getId() {
		return id;
	}
	
	public Date getRegistrationDate() {
		return creationDate;
	}
	
	public void setRegistrationDate(Date registrationDate) {
		this.creationDate = registrationDate;
	}

	public List<DiceGame> getDiceGameList() {
		return DiceGameList;
	}

}

package com.itacademy.dicegame.dto;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

public class PlayerDTO {
	
	@Id
	private UUID id;
	
	private String auth0_id;
	
	private String auth0_email;	

	@NotBlank
	private String name;

	private boolean anonimus;

	@CreatedDate
	private Date creationDate;

	@Version
	private int version;

	private double winPercentageOneDice;

	private double winPercentageTwoDice;

	private double winPercentageThreeDice;

	public PlayerDTO() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAuth0_id() {
		return auth0_id;
	}

	public void setAuth0_id(String auth0_id) {
		this.auth0_id = auth0_id;
	}

	public String getAuth0_email() {
		return auth0_email;
	}

	public void setAuth0_email(String auth0_email) {
		this.auth0_email = auth0_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAnonimus() {
		return anonimus;
	}

	public void setAnonimus(boolean anonimus) {
		this.anonimus = anonimus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public double getWinPercentageOneDice() {
		return winPercentageOneDice;
	}

	public void setWinPercentageOneDice(double winPercentageOneDice) {
		this.winPercentageOneDice = winPercentageOneDice;
	}

	public double getWinPercentageTwoDice() {
		return winPercentageTwoDice;
	}

	public void setWinPercentageTwoDice(double winPercentageTwoDice) {
		this.winPercentageTwoDice = winPercentageTwoDice;
	}

	public double getWinPercentageThreeDice() {
		return winPercentageThreeDice;
	}

	public void setWinPercentageThreeDice(double winPercentageThreeDice) {
		this.winPercentageThreeDice = winPercentageThreeDice;
	}

}

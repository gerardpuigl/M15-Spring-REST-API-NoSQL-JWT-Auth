package com.itacademy.dicegame.dto;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import com.itacademy.dicegame.security.AccessToken;

public class PlayerDTO {
	
	@Id
	private UUID id;
	
	private String auth0id;
	
	private String auth0email;
	
	private AccessToken accessToken;

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

	public String getAuth0id() {
		return auth0id;
	}

	public void setAuth0id(String auth0id) {
		this.auth0id = auth0id;
	}

	public String getAuth0email() {
		return auth0email;
	}

	public void setAuth0email(String auth0email) {
		this.auth0email = auth0email;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}
	
	public String getStringAccessToken() {
		if(accessToken == null) return null;
		return accessToken.toString();
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
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

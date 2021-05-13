package com.itacademy.dicegame.domain.player;

import java.util.UUID;

public class PlayerDTO {
	/*
	 * Player with auth0id,auth0email considered sensitive information	 
	 */
	
	private UUID id;
	
//	private String auth0id;
//	
//	private String auth0email;	

	private String name;

	private boolean anonimus;

//	private Date creationDate;

//	private int version;

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

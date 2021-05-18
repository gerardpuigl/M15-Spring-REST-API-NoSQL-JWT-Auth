package com.itacademy.dicegame.dto;

import java.util.Date;
import java.util.UUID;

public class DiceGameDTO {
	
	private UUID id;

	private Date creationDate;

	private UUID player;

	private int[] rolls;

	private boolean result;
	
	public DiceGameDTO() {
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

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
	public int[] getRolls() {
		return rolls;
	}

	public void setRolls(int[] rolls) {
		this.rolls = rolls;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}

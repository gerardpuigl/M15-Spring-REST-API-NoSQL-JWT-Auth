package com.itacademy.dicegame.dto;

import java.util.Date;
import java.util.UUID;

public class DiceGameDTO {
	
	private UUID id;

	private Date creationDate;

	private UUID player;


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
	
}

package com.itacademy.dicegame.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;

	// createPlayer
	public Player createPlayer(@Valid Player player) {
		playerRepository.save(player);
		return playerRepository.findById(player.getId()).get();
	}

	
	
	
}

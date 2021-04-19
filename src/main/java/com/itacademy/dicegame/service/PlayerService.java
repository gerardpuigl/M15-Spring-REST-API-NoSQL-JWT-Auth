package com.itacademy.dicegame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;

	// create Player
	public Player createPlayer(Player player) {
		playerRepository.save(player);
		return getPlayerById(player.getId());
	}

	// modify Player
	public Player modifyPlayer(Player player) {
		playerRepository.save(player);
		return getPlayerById(player.getId());
	}

	// get One Player by id
	public Player getPlayerById(long id_Player) {
		return playerRepository.findById(id_Player).get();		
	}

	// get all players
	public List<Player> getAll() {
		return playerRepository.findAll();
	}

	// delete player
	public String deleteById(long idPlayer) {
		playerRepository.delete(getPlayerById(idPlayer));
		return "Usuari eliminat correctament";
	}
	
	
}

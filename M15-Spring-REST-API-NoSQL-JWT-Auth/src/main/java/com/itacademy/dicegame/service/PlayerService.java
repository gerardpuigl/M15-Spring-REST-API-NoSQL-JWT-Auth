package com.itacademy.dicegame.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	// create Player
	public Player createPlayer(Player player) {
		playerRepository.save(player);
		return getPlayerById(player.getId());
	}

	// modify Player
	public Player modifyPlayer(Player player) {
		Player playerDb = playerRepository.findById(player.getId()).get();
		playerDb.setName(player.getName());
		playerRepository.save(playerDb);
		return getPlayerById(player.getId());
	}

	// get One Player by id
	public Player getPlayerById(UUID idPlayer) {
		return playerRepository.findById(idPlayer).get();
	}
	
	// get One Player by auth0_id
	public Player getPlayerByAuthid(String auth0_id) {
		return playerRepository.findByAuth0id(auth0_id);
	}

	// get all players
	public List<Player> getAll() {
		return playerRepository.findAll();
	}

	// delete player
	public String deleteById(UUID idPlayer) {
		playerRepository.delete(getPlayerById(idPlayer));
		return "Usuari eliminat correctament";
	}
	

}

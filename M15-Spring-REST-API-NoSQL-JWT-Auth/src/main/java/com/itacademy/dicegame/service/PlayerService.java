package com.itacademy.dicegame.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.domain.player.PlayerDTO;
import com.itacademy.dicegame.persistence.PlayerRepository;
import com.itacademy.dicegame.utils.DTOConverter;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private DTOConverter dtoConverter;
	
	// create Player
	public PlayerDTO createPlayer(Player player) {
		playerRepository.save(player);
		return getPlayerById(player.getId());
	}

	// modify Player
	public PlayerDTO modifyPlayer(Player player) {
		Player playerDb = playerRepository.findById(player.getId()).get();
		playerDb.setName(player.getName());
		playerDb.setAnonimus(player.isAnonimus());
		playerRepository.save(playerDb);
		return getPlayerById(player.getId());
	}

	// get One Player by id
	public PlayerDTO getPlayerById(UUID idPlayer) {
		return dtoConverter.convertToDto(playerRepository.findById(idPlayer).get());
	}
	
	// get One Player with all information
	private Player getCompletedPlayerById(UUID idPlayer) {
		return playerRepository.findById(idPlayer).get();
	}
	
	// get One Player by auth0_id
	public Player getPlayerByAuthid(String auth0_id) {
		return playerRepository.findByAuth0id(auth0_id);
	}

	// get all players
	public List<PlayerDTO> getAll() {
		return playerRepository.findAll().stream()
				.map(p->dtoConverter.convertToDto(p))
				.collect(Collectors.toList());
	}

	// delete player
	public String deleteById(UUID idPlayer) {
		playerRepository.delete(getCompletedPlayerById(idPlayer));
		return "Usuari eliminat correctament";
	}
	
}

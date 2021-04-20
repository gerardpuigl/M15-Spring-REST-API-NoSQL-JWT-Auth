package com.itacademy.dicegame.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	@PersistenceContext
	private EntityManager entityManager;

	// create Player
	public Player createPlayer(Player player) {
		playerRepository.save(player);
		return getPlayerById(player.getId());
	}

	// modify Player
	public Player modifyPlayer(Player player) {
		playerRepository.save(player);
		//clean the Hibernate entityManager and force to take updated entity from Repository
		//without this "registrationDate" returns null
		entityManager.clear();
		return getPlayerById(player.getId());
	}

	// get One Player by id
	public Player getPlayerById(int id_Player) {
		return playerRepository.findById(id_Player).get();
	}

	// get all players
	public List<Player> getAll() {
		return playerRepository.findAll();
	}

	// delete player
	public String deleteById(int idPlayer) {
		playerRepository.delete(getPlayerById(idPlayer));
		return "Usuari eliminat correctament";
	}

}

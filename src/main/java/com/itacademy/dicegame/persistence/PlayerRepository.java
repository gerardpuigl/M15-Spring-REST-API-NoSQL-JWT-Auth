package com.itacademy.dicegame.persistence;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.player.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {
	
	public Player findTopByOrderByCreationDateDesc();

	public Player findTopByOrderByWinPercentage();
	
	public Player findTopByOrderByWinPercentageDesc();

}

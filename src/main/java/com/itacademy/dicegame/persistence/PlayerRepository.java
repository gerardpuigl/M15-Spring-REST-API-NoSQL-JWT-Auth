package com.itacademy.dicegame.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
	
	public Player findTopByOrderByCreationDateDesc();

	public Player findTopByOrderByWinPercentage();
	
	public Player findTopByOrderByWinPercentageDesc();

}

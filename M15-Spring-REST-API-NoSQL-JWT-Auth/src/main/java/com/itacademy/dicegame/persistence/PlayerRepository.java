package com.itacademy.dicegame.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.player.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {
	
	public Player findTopByOrderByCreationDateDesc();

	public Player findTopByOrderByWinPercentageOneDice();
	
	public Player findTopByOrderByWinPercentageOneDiceDesc();
	
	public Player findTopByOrderByWinPercentageTwoDice();
	
	public Player findTopByOrderByWinPercentageTwoDiceDesc();
	
	public Player findTopByOrderByWinPercentageThreeDice();
	
	public Player findTopByOrderByWinPercentageThreeDiceDesc();
	
	public Player findByAuth0id(String auth0_id);
	
	public List<Player> findByOrderByWinPercentageOneDiceDesc();
	
	public List<Player>  findByOrderByWinPercentageTwoDiceDesc();
	
	public List<Player>  findByOrderByWinPercentageThreeDiceDesc();
	
}

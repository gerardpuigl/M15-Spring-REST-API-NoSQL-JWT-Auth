package com.itacademy.dicegame.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	public Player findTopByOrderByCreationDateDesc();

	public Player findTopByOrderByWinPercentage();
	
	public Player findTopByOrderByWinPercentageDesc();

}

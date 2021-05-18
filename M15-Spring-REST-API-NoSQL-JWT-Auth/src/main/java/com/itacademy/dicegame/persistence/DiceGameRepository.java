package com.itacademy.dicegame.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.diceGame.DiceGame;
import com.itacademy.dicegame.domain.diceGame.OneDiceGame;

@Repository
public interface DiceGameRepository extends MongoRepository<DiceGame, UUID> {

	public DiceGame findTopByOrderByCreationDateDesc();
	
	public List<DiceGame> findByPlayer_idIs(UUID idPlayer);

}

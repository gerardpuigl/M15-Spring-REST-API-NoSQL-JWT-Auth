package com.itacademy.dicegame.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.DiceGame;

@Repository
public interface DiceGameRepository extends MongoRepository<DiceGame, String> {

	public DiceGame findTopByOrderByCreationDateDesc();
	
	public List<DiceGame> findByPlayer_idIs(String idPlayer);

}

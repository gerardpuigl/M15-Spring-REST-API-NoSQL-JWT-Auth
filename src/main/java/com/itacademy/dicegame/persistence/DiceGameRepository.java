package com.itacademy.dicegame.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dicegame.domain.DiceGame;

@Repository
public interface DiceGameRepository extends JpaRepository<DiceGame, Integer> {

	public DiceGame findTopByOrderByCreationDateDesc();

}

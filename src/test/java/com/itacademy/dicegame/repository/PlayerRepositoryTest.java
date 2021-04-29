package com.itacademy.dicegame.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.PlayerRepository;

@DataMongoTest
public class PlayerRepositoryTest {
	
	@Autowired
	private PlayerRepository repository;
	
	@Test
	public void createPlayer() {
		Player player=new Player("test");
		repository.save(player);
	}
		
	//test autoincrement
	@Test
	public void icrementId() {
		Player player1=new Player("test1");
		Player player2=new Player("test2");
		
		assertThat(player1.getId()+1==player2.getId());
	}
	
}

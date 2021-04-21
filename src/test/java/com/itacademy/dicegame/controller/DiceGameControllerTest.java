package com.itacademy.dicegame.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.DiceGameRepository;
import com.itacademy.dicegame.persistence.PlayerRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DiceGameControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private DiceGameRepository gamesRepository;
	
	private Player testPlayer;	
	
	private DiceGame testDiceGame1;
	private DiceGame testDiceGame2;
	
	//create Test Player and games
	@BeforeEach
	public void createTestDiceGame() {
		
		testPlayer = new Player("TestPlayer");
		playerRepository.save(testPlayer);
		
		testDiceGame1 = new DiceGame(testPlayer);
		testDiceGame2 = new DiceGame(testPlayer);
		gamesRepository.save(testDiceGame1);
		gamesRepository.save(testDiceGame2);
	}
	
	//remove Player and games
	@AfterEach
	public void deleteTestDiceGame() {
		
		gamesRepository.delete(testDiceGame1);
		gamesRepository.delete(testDiceGame2);
		
		playerRepository.delete(testPlayer);
	}
		
	//test create one Play
	@Test
	@DisplayName("Check create new Play and get the results: HTTP POST Request")
	public void postDiceGame() throws Exception {
		
		// request preparation
		String uri = "/players/" + testPlayer.getId() + "/games";					//request uri

		// request
		mockMvc.perform(post(uri) 													//request
				.accept(MediaType.APPLICATION_JSON_VALUE)) 							//data received
		
		// check results
		.andExpect(status().isCreated())											//check status
		.andExpect(jsonPath("$.id").isNotEmpty())									//check all param are not empty
		.andExpect(jsonPath("$.firstRoll").isNotEmpty())					
		.andExpect(jsonPath("$.secondRoll").isNotEmpty())
		.andExpect(jsonPath("$.result").isNotEmpty())
		.andExpect(jsonPath("$.creationDate").isNotEmpty())
		.andExpect(jsonPath("$.player").isNotEmpty());						
		
		// remove added entity
		gamesRepository.delete(gamesRepository.findTopByOrderByCreationDateDesc());

	}
	
	//test get one DiceGame
	@Test
	@DisplayName("Check all player's games: HTTP GET Request")
	public void getDiceGame() throws Exception {
		
		// request preparation
		String uri = "/players/" + testPlayer.getId() + "/games";					//request uri
		
		// request
		mockMvc.perform(get(uri) 													//request
				.accept(MediaType.APPLICATION_JSON_VALUE)) 							//data received
							
		// check results
		.andExpect(status().isOk())													//check status
		.andExpect(jsonPath("$", hasSize(2)));										//check recive all the player's games
	}
	
	
	//test delete one DiceGame
	@Test
	@DisplayName("Check delete all player's games: HTTP DELETE Request")
	public void deleteDiceGame() throws Exception {
	
		// request preparation
		String uri = "/players/" + testPlayer.getId() + "/games";					//request uri with id

		// request
		mockMvc.perform(delete(uri))												//delete request
		
		// check results
		.andExpect(status().isAccepted());											//check status
		assertThat(testPlayer.getDiceGameList().size() == 0);						//check if all games are deleted
	}
}

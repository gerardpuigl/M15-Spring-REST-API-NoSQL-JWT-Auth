package com.itacademy.dicegame.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.PlayerRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PlayerRepository repository;
	
	private Player testPlayer;
	
	//addTestPlayer
	@BeforeEach
	public void createTestPlayer() {
		testPlayer = new Player("TestPlayer");
		repository.save(testPlayer);
	}
	
	//deleteTestPlayer
	@AfterEach
	public void deleteTestPlayer() {
		repository.delete(testPlayer);
	}
		
	//test add one player
	@Test
	@DisplayName("Check add Player: HTTP POST Request + json")
	public void postPlayer() throws Exception {
		
		//Given
		String uri = "/players";											//request with body
		String json= "{\"name\":\"addTestPlayer\"}";						//json

		//When
		mockMvc.perform(post(uri)											
				.contentType(MediaType.APPLICATION_JSON).content(json) 		//data send
				.accept(MediaType.APPLICATION_JSON_VALUE)) 					//data received
		
		//Then
		.andExpect(status().isCreated())									//check status code
		.andExpect(jsonPath("$.name",is("addTestPlayer")))					//check name
		.andExpect(jsonPath("$.id",notNullValue()));						//check id is not null
		
		repository.delete(repository.findTopByOrderByRegistrationDateDesc());
	}
	
	//test get one player
	@Test
	@DisplayName("Check get Player: HTTP GET Request")
	public void getPlayer() throws Exception {
		
		//Given
		String uri = "/players/" + testPlayer.getId();						//Request by Id
		
		//When
		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE))	//data received
							
		//Then
		.andExpect(status().isOk())											//check status code	
		.andExpect(jsonPath("$.name",is("TestPlayer")))						//check name
		.andExpect(jsonPath("$.id",is(testPlayer.getId())));				//check return same id reference
	}
	
	//test delete one player
	@Test
	@DisplayName("Check modify Player: HTTP PUT Request + json")
	public void modifyPlayer() throws Exception {

		//Given
		String uri = "/players";
		testPlayer.setName("TestPlayerModifiedName");
		String json= new ObjectMapper().writeValueAsString(testPlayer); 

		//When
		mockMvc.perform(
				put(uri)		
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		
		//Then
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$.name",is("TestPlayerModifiedName")));
	}

	//test delete one player
	@Test
	@DisplayName("Check delete Player: HTTP DELETE Request")
	public void deletePlayer() throws Exception {
	
		//Given
		String uri = "/players/"+testPlayer.getId();

		//When
		mockMvc.perform(delete(uri))
		
		//Then
		.andExpect(status().isAccepted());
		assertThat(repository.findById(testPlayer.getId())==null);
	}
}

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
	
	//add Test Player
	@BeforeEach
	public void createTestPlayer() {
		testPlayer = new Player("TestPlayer");
		repository.save(testPlayer);
	}
	
	//delete Test Player
	@AfterEach
	public void deleteTestPlayer() {
		repository.delete(testPlayer);
	}
		
	//test add one player
	@Test
	@DisplayName("Check add Player: HTTP POST Request + json")
	public void postPlayer() throws Exception {
		
		// request preparation
		String uri = "/players";											//request uri
		String json= "{\"name\":\"addTestPlayer\"}";						//json

		// request
		mockMvc.perform(post(uri)											
				.contentType(MediaType.APPLICATION_JSON).content(json) 		//data send
				.accept(MediaType.APPLICATION_JSON_VALUE)) 					//data received
		
		// check results
		.andExpect(status().isCreated())									//check status code
		.andExpect(jsonPath("$.name",is("addTestPlayer")))					//check name
		.andExpect(jsonPath("$.id",notNullValue()));						//check id is not null
		
		// remove added entity
		repository.delete(repository.findTopByOrderByCreationDateDesc());
	}
	
	//test get one player
	@Test
	@DisplayName("Check get Player: HTTP GET Request")
	public void getPlayer() throws Exception {
		
		// request preparation
		String uri = "/players/" + testPlayer.getId();						//request uri by Id
		
		// request
		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE))	//data received
							
		// check results
		.andExpect(status().isOk())											//check status code	
		.andExpect(jsonPath("$.name",is("TestPlayer")))						//check name
		.andExpect(jsonPath("$.id",is(testPlayer.getId())));				//check return same id reference
	}
	
	//test delete one player
	@Test
	@DisplayName("Check modify Player: HTTP PUT Request + json")
	public void modifyPlayer() throws Exception {

		// request preparation
		String uri = "/players";											//request uri
		testPlayer.setName("TestPlayerModifiedName");						
		String json= new ObjectMapper().writeValueAsString(testPlayer); 	//json with modified name

		// request
		mockMvc.perform(put(uri)		
				.contentType(MediaType.APPLICATION_JSON).content(json) 		//data send
				.accept(MediaType.APPLICATION_JSON_VALUE)) 					//data received
		
		// check results
		.andExpect(status().isAccepted())									//check status code	
		.andExpect(jsonPath("$.name",is("TestPlayerModifiedName")));		//check modification
	}

	//test delete one player
	@Test
	@DisplayName("Check delete Player: HTTP DELETE Request")
	public void deletePlayer() throws Exception {
	
		// request preparation
		String uri = "/players/"+testPlayer.getId();						//request uri by Id

		// request
		mockMvc.perform(delete(uri))										//delete request
		
		// check results
		.andExpect(status().isAccepted());									//check status code	
		assertThat(repository.findById(testPlayer.getId())==null);			//check if delete
	}
}

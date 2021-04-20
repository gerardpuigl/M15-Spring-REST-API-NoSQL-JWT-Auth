package com.itacademy.dicegame.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
	
	@BeforeEach
	public void cretateTestPlayer() throws Exception {
		addPlayer();
	}
	
	//test add one player
	@Test
	public void addPlayer() throws Exception {
		
		//Given
		String uri = "/players";
		String json= "{\"name\":\"testPlayer\"}";

		//When
		ResultActions result =	postActions(mockMvc, uri, json);
		
		//Then
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.name",is("testPlayer")));
		result.andExpect(jsonPath("$.id",notNullValue()));
	}
	
	//test get one player
	@Test
	public void getPlayer() throws Exception {
		
		//Given
		int id= repository.findTopByOrderByRegistrationDateDesc().getId();
		String uri = "/players/" + id;

		//When
		ResultActions result =	getActions(mockMvc, uri);
		
		//Then
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.name",is("testPlayer")));
		result.andExpect(jsonPath("$.id",is(id)));
	}
	
	//test delete one player
	@Test
	public void modifyPlayer() throws Exception {

		//Given
		String uri = "/players";
		Player player= repository.findTopByOrderByRegistrationDateDesc();
		player.setName("testPlayerModified");
		String json= new ObjectMapper().writeValueAsString(player);

		//When
		ResultActions result =	putActions(mockMvc, uri, json);
		
		//Then
		result.andExpect(status().isAccepted());
		result.andExpect(jsonPath("$.name",is("testPlayerModified")));
	}
	
	//test delete one player
	@Test
	public void deletePlayer() throws Exception {
	
		//Given
		int id= repository.findTopByOrderByRegistrationDateDesc().getId();
		String uri = "/players/"+id;

		//When
		ResultActions result =	deleteActions(mockMvc, uri);
		
		//Then
		result.andExpect(status().isAccepted());
		assertThat(repository.findById(id)==null);
	}
	
	//post Json and return Json
	public ResultActions postActions(MockMvc mockMvc, String uri, String json) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.post(uri)		
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}
	
	//get and return Json
	public ResultActions getActions(MockMvc mockMvc, String uri) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}
	
	//put Json
	public ResultActions putActions(MockMvc mockMvc, String uri, String json) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.put(uri)		
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}
	
	//delete request
	public ResultActions deleteActions(MockMvc mockMvc, String uri) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.delete(uri));
	}
}

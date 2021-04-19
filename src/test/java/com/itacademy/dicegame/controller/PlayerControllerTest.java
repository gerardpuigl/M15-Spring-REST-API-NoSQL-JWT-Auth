package com.itacademy.dicegame.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//test add one player
	@Test
	public void createPlayer() throws Exception {
		
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

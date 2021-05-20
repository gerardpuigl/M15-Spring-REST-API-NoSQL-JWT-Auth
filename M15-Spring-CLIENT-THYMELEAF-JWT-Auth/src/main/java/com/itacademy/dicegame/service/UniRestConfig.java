package com.itacademy.dicegame.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mashape.unirest.http.Unirest;

@Configuration
public class UniRestConfig {
	
    @Bean
    public void configureUnirest() {
    	HttpClient httpClient = HttpClients.custom()
		    .disableCookieManagement()
		    .build();
		Unirest.setHttpClient(httpClient);
    }
}

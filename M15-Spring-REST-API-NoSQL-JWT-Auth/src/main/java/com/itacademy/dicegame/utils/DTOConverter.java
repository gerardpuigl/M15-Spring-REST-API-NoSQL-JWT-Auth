package com.itacademy.dicegame.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.domain.player.PlayerDTO;

@Component
public class DTOConverter {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
    @Autowired
    private ModelMapper modelMapper;
    
    public DTOConverter() {
    }
    
	public PlayerDTO convertToDto(Player player) {
		PlayerDTO playerDto = modelMapper.map(player, PlayerDTO.class);
	    return playerDto;
	}
	
	public Player convertToEntity(PlayerDTO playerDto) {
		Player player = modelMapper.map(playerDto, Player.class);
	    return player;
	}
}

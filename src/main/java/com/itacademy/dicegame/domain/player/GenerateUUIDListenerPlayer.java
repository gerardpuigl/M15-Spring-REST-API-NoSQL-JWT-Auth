package com.itacademy.dicegame.domain.player;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

//MongoDB don't create properly UUID and we override it's function
@Component
public class GenerateUUIDListenerPlayer extends AbstractMongoEventListener<Player> {
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<Player> event) {
		Player player = event.getSource();
		if (player.isNew()) {
			player.setId(UUID.randomUUID());
		}
	}
}
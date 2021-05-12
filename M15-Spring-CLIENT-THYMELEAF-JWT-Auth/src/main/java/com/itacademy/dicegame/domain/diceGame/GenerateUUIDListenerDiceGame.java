package com.itacademy.dicegame.domain.diceGame;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

//MongoDB don't create properly UUID and we override it's function
@Component
public class GenerateUUIDListenerDiceGame extends AbstractMongoEventListener<DiceGame> {
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<DiceGame> event) {
		DiceGame diceGame = event.getSource();
		if (diceGame.isNew()) {
			diceGame.setId(UUID.randomUUID());
		}
	}
}
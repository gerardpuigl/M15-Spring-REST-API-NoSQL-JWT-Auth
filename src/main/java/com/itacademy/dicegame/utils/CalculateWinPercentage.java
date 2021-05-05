package com.itacademy.dicegame.utils;

import java.util.List;

import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;

import com.itacademy.dicegame.domain.diceGame.DiceGame;

@Component
public class CalculateWinPercentage {

	public CalculateWinPercentage() {
	}

	public double fromList(List<DiceGame> diceGameList) {
		if (diceGameList.size() == 0 || diceGameList == null) {
			return 0;
		}
		double winPercentage;
		double wins = diceGameList.stream().filter(dg -> dg.getResult() == true).count();
		double total = diceGameList.size();
		winPercentage = (wins / total) * 100;
		return DoubleRounder.round(winPercentage, 2);
	}

}

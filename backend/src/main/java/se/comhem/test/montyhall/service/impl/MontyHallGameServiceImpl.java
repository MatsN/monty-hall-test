package se.comhem.test.montyhall.service.impl;

import java.text.NumberFormat;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import se.comhem.test.montyhall.domain.Deal;
import se.comhem.test.montyhall.service.MontyHallGameService;

@Component
public class MontyHallGameServiceImpl implements MontyHallGameService {

	@Override
	public String getPercentageWins(int numberOfgames, boolean switchDoor) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		double wins = 0;
		Deal[] doors = { Deal.CAR, Deal.GOAT, Deal.GOAT };
		
		for(int gameNumber=0; gameNumber < numberOfgames; gameNumber++) {
			int door = random.nextInt(0, 3);
			if ((doors[door].equals(Deal.CAR) && !switchDoor) || (doors[door].equals(Deal.GOAT) && switchDoor)) {
					wins++;
			}
		}
		
		double percentage = wins / numberOfgames;
		
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(0);
		
		return defaultFormat.format(percentage);
	}
}

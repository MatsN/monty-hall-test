package se.comhem.test.montyhall.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.comhem.test.montyhall.rest.dto.GameSettingsDto;
import se.comhem.test.montyhall.service.MontyHallGameService;

@RestController
public class MontyHallGameEndpoint {
	
	@Autowired
	MontyHallGameService gameService;
	
	@PostMapping
	public String getPercentageWins(@RequestBody final GameSettingsDto gameSettingsDto) {
		return gameService.getPercentageWins(gameSettingsDto.numberOfGames, gameSettingsDto.switchDoor);
	}

}

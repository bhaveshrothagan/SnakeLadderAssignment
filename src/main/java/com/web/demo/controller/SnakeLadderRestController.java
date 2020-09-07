/**
 * 
 */
package com.web.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.demo.dao.SnakeLadderDAO;
import com.web.demo.dto.PlayerInfo;

/**
 * @author bhavesh
 *
 */
@RestController
public class SnakeLadderRestController {

	Logger logger = Logger.getLogger(SnakeLadderRestController.class);

	@Autowired
	SnakeLadderDAO snakeLadderDao;

	// Registering the game
	@PostMapping("/v1/api/register")
	public String register(@RequestParam String teamName, @RequestParam String playerName) {

		// generating game code
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	// Entering the game using gamecode
	@PostMapping("/v1/api/register/{code}")
	public int registerUsingCode(@PathVariable("code") String gameCode) {

		// Validating game code exist or not from db

		return 0;
	}

	// List of players and details of current position and last 5 moment of each
	// player
	@GetMapping(value = "/v1/api/{code}")
	public List<PlayerInfo> getPlayerInfo(@PathVariable("code") String gameCode) throws Exception {

		logger.info("gameCode :: " + gameCode);

		List<PlayerInfo> playerList = new ArrayList<PlayerInfo>();
		// Validating game code
		if (gameCode == null || "".equalsIgnoreCase(gameCode.trim())) {
			logger.info("Game Code Not Found");
			throw new Exception("Game Code Not Found");
		}

		// retrieve the list of player for the gamecode
		try {
			playerList = snakeLadderDao.getPlayersDetail(gameCode);
		} catch (Exception e) {
			logger.error(e);
		}
		if (playerList == null) {
			throw new Exception("No Players found");
		}

		// Retrieve the detail of each player
		for (PlayerInfo playerInfo : playerList) {
			try {
				List<HashMap<Integer, Integer>> lastFiveMoves = snakeLadderDao.getLastFiveMoves(playerInfo.getId());

				if ((lastFiveMoves.size()) >= 1) {
					int key = Integer
							.parseInt(lastFiveMoves.get(lastFiveMoves.size() - 1).keySet().toArray()[0].toString());
					playerInfo.setCurrentPos(lastFiveMoves.get(lastFiveMoves.size() - 1).get(key));
				} else {
					playerInfo.setCurrentPos(0);
				}
				playerInfo.setLastFiveMoves(lastFiveMoves);

			} catch (Exception e) {
				logger.error(e);
				throw new Exception(e);
			}
		}
		return playerList;
	}
}

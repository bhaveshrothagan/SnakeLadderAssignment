package com.web.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.web.demo.dto.PlayerInfo;

@Repository
public class SnakeLadderDAO {

	Logger logger = Logger.getLogger(SnakeLadderDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	String playerListQuery = "select ID,GAME_ID,PLAYER_ID from game_player_info where GAME_ID = ?";
	String lastFiveMoveQuery = "select ID,CURRENT_POSITION,NEW_POSITION from game_stats where ID = ? LIMIT 5";

	public List<PlayerInfo> getPlayersDetail(String gameCode) throws Exception {
		List<PlayerInfo> playerInfos = new ArrayList<PlayerInfo>();
		try {
			Query query = entityManager.createNativeQuery(playerListQuery).setParameter(1, gameCode);
			List<Object[]> response = query.getResultList();
			Iterator it = response.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				PlayerInfo playerInfo = new PlayerInfo();
				playerInfo.setId(line[0].toString());
				playerInfo.setGameCode(line[1].toString());
				playerInfo.setPlayerId(line[2].toString());

				playerInfos.add(playerInfo);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

		return playerInfos;

	}

	public List<HashMap<Integer, Integer>> getLastFiveMoves(String id) throws Exception {
		// TODO Auto-generated method stub
		List<HashMap<Integer, Integer>> lastFiveMove = new ArrayList<HashMap<Integer, Integer>>();
		try {
			Query query = entityManager.createNativeQuery(lastFiveMoveQuery).setParameter(1, id);
			List<Object[]> response = query.getResultList();
			Iterator it = response.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				HashMap<Integer, Integer> moves = new HashMap<Integer, Integer>();
				int currentPos = Integer.parseInt(line[0].toString());
				int newPos = Integer.parseInt(line[1].toString());

				moves.put(currentPos, newPos);
				lastFiveMove.add(moves);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
		return lastFiveMove;
	}
}

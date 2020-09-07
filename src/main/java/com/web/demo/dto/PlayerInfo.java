package com.web.demo.dto;

import java.util.HashMap;
import java.util.List;

public class PlayerInfo {

	private String id;
	private String gameCode;
	private String playerId;
	private int currentPos;
	private List<HashMap<Integer, Integer>> lastFiveMoves;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public List<HashMap<Integer, Integer>> getLastFiveMoves() {
		return lastFiveMoves;
	}

	public void setLastFiveMoves(List<HashMap<Integer, Integer>> lastFiveMoves) {
		this.lastFiveMoves = lastFiveMoves;
	}

}

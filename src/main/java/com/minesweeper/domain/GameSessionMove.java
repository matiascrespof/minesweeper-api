package com.minesweeper.domain;

public class GameSessionMove {
	String gameId;
	int rowP;
	int columP;
	
	public GameSessionMove(String gameId, int rowP, int columP) {
		this.gameId = gameId;
		this.rowP = rowP;
		this.columP = columP;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int getRowP() {
		return rowP;
	}
	public void setRowP(int rowP) {
		this.rowP = rowP;
	}
	public int getColumP() {
		return columP;
	}
	public void setColumP(int columP) {
		this.columP = columP;
	}
	
	
}

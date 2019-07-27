package com.minesweeper.domain;

public class GameSessionMove {
	String gameId;
	int rowP;
	int columnP;
	
	public GameSessionMove(String gameId, int rowP, int columnP) {
		this.gameId = gameId;
		this.rowP = rowP;
		this.columnP = columnP;
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
		return columnP;
	}
	public void setColumP(int columnP) {
		this.columnP = columnP;
	}
	
	
}

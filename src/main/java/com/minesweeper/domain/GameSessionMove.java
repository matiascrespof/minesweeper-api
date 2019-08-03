package com.minesweeper.domain;
import javax.validation.constraints.NotNull;

public class GameSessionMove {
	
	@NotNull(message = "game Id must not be null!")
	String gameId;
	@NotNull(message = "Row must not be null!")
	Integer rowP;
	@NotNull(message = "Column must not be null!")
	Integer columnP;

	public GameSessionMove(String gameId, Integer rowP, Integer columnP) {
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

	public void setRowP(Integer rowP) {
		this.rowP = rowP;
	}

	public int getColumP() {
		return columnP;
	}

	public void setColumP(Integer columnP) {
		this.columnP = columnP;
	}

}

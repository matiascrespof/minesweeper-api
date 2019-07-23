package com.minesweeper.api.handler;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;
import com.minesweeper.api.utils.GameStatus;

/**
 * 
 * @author matias
 *
 */
public class GameBoard {
	Date startDate;
	Date startGameDate;
	Date endGameDate;
	int nRows;
	int nColumns;
	String gameStatus;
	Square[][] squares;
	int mines;

	/**
	 * Start new Board
	 * 
	 * @param user
	 */
	protected GameBoard(int rows, int columns, int nMines) {
		this.gameStatus = GameStatus.PAUSED.toString();
		this.startDate = new Date();
		this.nRows = rows - 1;
		this.nColumns = columns - 1;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public Date getStartDate() {
		return startDate;
	}

	public long gameDuration() {
		long gameDuration = 0L;
		if (gameStatus.equals(GameStatus.FINISHED.toString())) {
			Duration duration = Duration.between(startGameDate.toInstant(), endGameDate.toInstant());
			gameDuration = duration.get(ChronoUnit.SECONDS);
		}
		return gameDuration;
	}
}

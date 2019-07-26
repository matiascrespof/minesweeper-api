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
		this.setSquares(rows, columns);
		this.initCloseToBombs(rows, columns, nMines);
		this.evaluateBombsCloseTo();
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
		if (gameStatus.equals(GameStatus.FINISHED_LOOSE.toString())
				|| gameStatus.equals(GameStatus.FINISHED_WON.toString())) {
			Duration duration = Duration.between(startGameDate.toInstant(), endGameDate.toInstant());
			gameDuration = duration.get(ChronoUnit.SECONDS);
		}
		return gameDuration;
	}

	public void startGame() {
		this.gameStatus = GameStatus.IN_PROGRESS.toString();
		this.startGameDate = new Date();
	}

	public void endFailedGame() {
		this.gameStatus = GameStatus.FINISHED_LOOSE.toString();
		this.endGameDate = new Date();
	}

	/**
	 * init default Squares
	 * 
	 * @param columns
	 * @param rows
	 * @param nMines
	 */
	private void setSquares(int rows, int columns) {
		squares = new Square[rows][columns];
		IntStream.range(0, rows).forEach(r -> {
			IntStream.range(0, columns).forEach(c -> {
				// setting up bombs as false for now
				squares[r][c] = new Square(r, c, false);
			});
		});
	}

	/**
	 * TODO for now is the best way I found to set random bombs given the time
	 * 
	 * @param columns
	 * @param rows
	 * @param nMines
	 */
	private void initCloseToBombs(int rows, int columns, int nMines) {
		int minesLeft = nMines;
		Random row = new Random();
		Random colums = new Random();
		while (minesLeft > 0) {
			Square squareToSetABomb = squares[row.nextInt(rows)][colums.nextInt(columns)];
			if (!squareToSetABomb.hasAbomb()) {
				squareToSetABomb.setHasAbomb(true);
				minesLeft--;
			}
		}
	}

	/**
	 * evaluate number of bombs close to
	 */
	private void evaluateBombsCloseTo() {
		Arrays.stream(squares).parallel().forEach(sqy -> Arrays.stream(sqy).parallel().forEach(sq -> evaluateNeighbors(sq)));
	}

	/**
	 * Based on a Square evaluate all close to Squares to check bombs and set the
	 * number of bombs close to
	 * 
	 * @param sq
	 */
	private void evaluateNeighbors(Square sq) {
		// TODO clean up this code a little bit
		int bombsCloseToMe = 0;
		int rp = sq.getrPosition();
		int cp = sq.getcPosition();

		if (rp > 0) {
			// TODO 1 Evaluate -1, 0
			if (squares[rp - 1][cp].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 2 Evaluate -1,-1
		if (cp > 0 && rp > 0) {
			if (squares[rp - 1][cp - 1].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 3 Evaluate 0,-1
		if (cp > 0) {
			if (squares[rp][cp - 1].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 4 Evaluate +1,-1
		if (rp < this.nRows && cp > 0) {
			if (squares[rp + 1][cp - 1].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 5 Evaluate +1, 0
		if (rp < this.nRows) {
			if (squares[rp + 1][cp].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 6 Evaluate +1,+1
		if (rp < this.nRows && cp < this.nColumns) {
			if (squares[rp + 1][cp + 1].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 7 Evaluate 0,+1
		if (cp < this.nColumns) {
			if (squares[rp][cp + 1].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// TODO 8 Evaluate -1,+1
		if (rp > 0 && cp < this.nColumns) {
			if (squares[rp - 1][cp + 1].hasAbomb()) {
				bombsCloseToMe++;
			}
		}
		sq.setBombsCloseTo(bombsCloseToMe);
		System.out.println(
				"Bombs Close to Me for Row: " + rp + " Column: " + cp + " Number of Bombs:" + sq.getBombsCloseTo());

	}
}

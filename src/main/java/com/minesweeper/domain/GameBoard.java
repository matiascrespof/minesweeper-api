package com.minesweeper.domain;

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
	public GameBoard(int columns, int rows, int nMines) {
		this.gameStatus = GameStatus.PAUSED.toString();
		this.startDate = new Date();
		this.nRows = rows - 1;
		this.nColumns = columns - 1;
		this.setSquares(columns, rows);
		this.initCloseToBombs(columns, rows, nMines);
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

	public int getnRows() {
		return nRows;
	}

	public int getnColumns() {
		return nColumns;
	}

	/**
	 * init default Squares
	 * 
	 * @param columns
	 * @param rows
	 * @param nMines
	 */
	private void setSquares(int columns, int rows) {
		squares = new Square[columns][rows];
		IntStream.range(0, columns).forEach(c -> {
			IntStream.range(0, rows).forEach(r -> {
				// setting up bombs as false for now
				squares[c][r] = new Square(c, r, false);
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
	private void initCloseToBombs(int columns, int rows, int nMines) {
		int minesLeft = nMines;
		Random row = new Random();
		Random column = new Random();
		while (minesLeft > 0) {
			Square squareToSetABomb = squares[column.nextInt(columns)][row.nextInt(rows)];
			if (!squareToSetABomb.isHasAbomb()) {
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
			// 1 Evaluate 0,-1
			if (squares[cp][rp - 1].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 2 Evaluate -1,-1
		if (rp > 0 && cp > 0) {
			if (squares[cp - 1][rp - 1].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 3 Evaluate -1,0
		if (cp > 0) {
			if (squares[cp-1][rp].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 4 Evaluate +1,-1
		if (cp < this.nColumns && rp > 0) {
			if (squares[cp + 1][rp - 1].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 5 Evaluate +1, 0
		if (cp < this.nColumns) {
			if (squares[cp + 1][rp].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 6 Evaluate +1,+1
		if (cp < this.nColumns && rp < this.nRows) {
			if (squares[cp + 1][rp + 1].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 7 Evaluate 0,+1
		if (rp < this.nRows) {
			if (squares[cp][rp + 1].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		// 8 Evaluate -1,+1
		if (cp > 0 && rp < this.nRows) {
			if (squares[cp - 1][rp + 1].isHasAbomb()) {
				bombsCloseToMe++;
			}
		}
		sq.setBombsCloseTo(bombsCloseToMe);
	}
}

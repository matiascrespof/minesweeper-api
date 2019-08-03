package com.minesweeper.api.handler;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minesweeper.api.utils.GameStatus;
import com.minesweeper.domain.GameBoard;
import com.minesweeper.domain.Square;

/**
 * 
 * @author matias
 *
 */
public class GameBoardHandlerImpl implements GameBoardHandler {
	Logger logger = LoggerFactory.getLogger(GameBoardHandlerImpl.class);
	/**
	 * Begginer Parameters
	 */
	private static int BROWS = 9;
	private static int BCOLUMNS = 9;
	private static int BMINES = 10;
	/**
	 * Intermediate Parameters
	 */
	private static int IROWS = 16;
	private static int ICOLUMNS = 16;
	private static int IMINES = 40;

	/**
	 * Advanced Parameters
	 */
	private static int AROWS = 16;
	private static int ACOLUMNS = 30;
	private static int AMINES = 99;

	/**
	 * 
	 * @param user
	 * @return
	 */
	public GameBoard startNewBegginerBoard() {
		logger.info("Starting Begginer Game");
		GameBoard begginerBoard = new GameBoard(BCOLUMNS, BROWS, BMINES);
		return begginerBoard;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public GameBoard startNewIntermediateBoard() {
		logger.info("Starting Intermediate Game");
		GameBoard intermediateBoard = new GameBoard(ICOLUMNS, IROWS, IMINES);
		return intermediateBoard;

	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public GameBoard startNewAdvancedBoard() {
		logger.info("Starting Advanced Game");
		GameBoard advancedBoard = new GameBoard(ACOLUMNS, AROWS, AMINES);
		return advancedBoard;
	}

	@Override
	public GameBoard startNewCustomBoard(int columns, int rows, int nMines) throws Exception {
		// TODO not yet
		validateGameSetup(columns, rows, nMines);
		throw new Exception("Not implemented yet!");
	}

	@Override
	public void validateGameSetup(int columns, int rows, int nMines) throws Exception {
		// Basic validation for now idk if this may be needed in a near future
		if (nMines > columns * rows) {
			logger.error("Number of mines bigger than the board");
			throw new Exception("Not valid configuration!");
		}

	}

	@Override
	public boolean hasABomb(Square square) throws Exception {
		return square.isHasAbomb();
	}

	@Override
	public GameBoard revealPosition(GameBoard game, int row, int column) throws Exception {
		// TODO clean up this code a little bit
		logger.info("Starting Reveal position");
		boolean isABomb = false;
		try {
			Square square = game.getSquares()[column][row];
			//if row is marked do not evalue the status and try to open it
			if(square.isMarked()) {
				logger.warn("it is already flagged!");
				return game;
			}
			startGameAndCheckStatus(game);
			// First check if has a Bomb
			isABomb = this.hasABomb(square);
			if (isABomb) {
				game.endFailedGame();
			} else {
				square.setOpened(true);
				// if the one I'm trying to open do not have a bomb close to, then try to open
				// close ones recursively
				revealMyNeighbors(game.getSquares(), square, game.getnColumns(), game.getnRows());
			}
		} catch (Exception ex) {
			throw ex;
		}
		return game;
	}
	
	@Override
	public GameBoard flag(GameBoard game, int row, int column) throws Exception {
		try {
			startGameAndCheckStatus(game);
			// First check if has a Bomb
			Square square = game.getSquares()[column][row];
			square.setMarked(!square.isMarked());
		} catch (Exception ex) {
			throw ex;
		}
		return game;
	}
	
	

	/**
	 * Start method if not started yet
	 * 
	 * @param game
	 * @throws ValidationException
	 */
	private void startGameAndCheckStatus(GameBoard game) throws ValidationException {
		String gameStatus = game.getGameStatus().toString();
		if (gameStatus.toString().equals(GameStatus.FINISHED_LOOSE.toString())
				|| gameStatus.toString().equals(GameStatus.FINISHED_WON.toString())) {
			logger.warn("The game is not in progress");
			throw new ValidationException("The game is not in progress");
		} else if (gameStatus.equals(GameStatus.PAUSED.toString())) {
			game.startGame();
		}

	}

	/**
	 * 
	 * @param startSquare
	 * @param squares
	 * @param rows
	 * @param columns
	 */
	private void revealMyNeighbors(Square[][] squares, Square startSquare, int columns, int rows) {
		int startColumn = startSquare.getcPosition();
		int startRow = startSquare.getrPosition();
		startSquare.setOpened(true);
		if (startSquare.getBombsCloseTo() == 0) {
			if (startColumn < columns) {
				revealMyNeighbors(squares, squares[startColumn + 1][startRow], columns, rows);
			}
			if (startRow < rows) {
				revealMyNeighbors(squares, squares[startColumn][startRow + 1], columns, rows);
			}
		}
	}

}

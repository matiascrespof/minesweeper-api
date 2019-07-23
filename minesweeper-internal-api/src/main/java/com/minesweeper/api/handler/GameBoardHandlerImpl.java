package com.minesweeper.api.handler;

/**
 * 
 * @author matias
 *
 */
public class GameBoardHandlerImpl implements GameBoardHandler {

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
		GameBoard begginerBoard = new GameBoard(BROWS, BCOLUMNS, BMINES);
		return begginerBoard;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public GameBoard startNewIntermediateBoard() {
		GameBoard intermediateBoard = new GameBoard(IROWS, ICOLUMNS, IMINES);
		return intermediateBoard;

	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public GameBoard startNewAdvancedBoard() {
		GameBoard advancedBoard = new GameBoard(AROWS, ACOLUMNS, AMINES);
		return advancedBoard;
	}

	@Override
	public GameBoard startNewCustomBoard(int height, int width, int nMines) throws Exception {
		// TODO not yet
		validateGameSetup(height, width, nMines);
		throw new Exception("Not implemented yet!");
	}

	@Override
	public void validateGameSetup(int heigth, int width, int nMines) throws Exception {
		// Basic validation for now idk if this may be needed in a near future
		if (nMines > heigth * width) {
			throw new Exception("Not valid configuration!");
		}

	}

	@Override
	public boolean hasABomb(GameBoard game, int row, int column) throws Exception {
		boolean isABomb = false;
		try {
			isABomb = game.getSquares()[column][row].hasAbomb();
		} catch (Exception e) {
			throw new Exception("Not valid configuration!");
		}
		return isABomb;
	}
}

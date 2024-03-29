package com.minesweeper.api.handler;

import com.minesweeper.domain.GameBoard;
import com.minesweeper.domain.Square;

public interface GameBoardHandler {

	public GameBoard startNewBegginerBoard() throws Exception;

	public GameBoard startNewIntermediateBoard() throws Exception;

	public GameBoard startNewAdvancedBoard() throws Exception;

	public GameBoard startNewCustomBoard(int height, int width, int nMines) throws Exception;

	public void validateGameSetup(int height, int width, int nMines) throws Exception;

	/**
	 * Limit the access to discover a bomb for a Game Board
	 * 
	 * @param game
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */
	public boolean hasABomb(Square square) throws Exception;
	
	/**
	 * Limit the access to discover a bomb for a Game Board
	 * 
	 * @param game
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */
	public GameBoard revealPosition(GameBoard game, int row, int column) throws Exception;
	
	/**
	 * Flag an Square
	 * 
	 * @param game
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */
	public GameBoard flag(GameBoard game, int row, int column) throws Exception;
}

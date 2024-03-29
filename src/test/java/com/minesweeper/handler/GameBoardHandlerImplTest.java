package com.minesweeper.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.minesweeper.api.handler.GameBoardHandler;
import com.minesweeper.api.handler.GameBoardHandlerImpl;
import com.minesweeper.domain.GameBoard;
import com.minesweeper.domain.Square;

public class GameBoardHandlerImplTest {

	private GameBoardHandler handler;

	@Before
	public void setup() {
		handler = new GameBoardHandlerImpl();
	}

	@Test
	public void testNewBeginnerGameNumberOfBombsCount() throws Exception {
		GameBoard gameBoard = handler.startNewBegginerBoard();
		int bombsFound = 0;
		int x = 0;
		for (Square[] sq : gameBoard.getSquares()) {
			int y = 0;
			for (Square sq2 : sq) {
				boolean isABomb =  handler.hasABomb(sq2);
				if (isABomb) {
					//System.out.println("BOMB FOUND ON array row " + y + " column " + x);
					bombsFound++;
				}
				y++;
			}
			x++;
		}
		assertEquals(10, bombsFound);
	}
	
	@Test
	public void testNewIntermediateGameNumberOfBombsCount() throws Exception {
		GameBoard gameBoard = handler.startNewIntermediateBoard();
		int bombsFound = 0;
		int x = 0;
		for (Square[] sq : gameBoard.getSquares()) {
			int y = 0;
			for (Square sq2 : sq) {
				boolean isABomb =  handler.hasABomb(sq2);
				if (isABomb) {
					System.out.println("BOMB FOUND ON array row " + y + " column " + x);
					bombsFound++;
				}
				y++;
			}
			x++;
		}
		assertEquals(40, bombsFound);
	}
	
	@Test
	public void testNewAdvancedGameNumberOfBombsCount() throws Exception {
		GameBoard gameBoard = handler.startNewAdvancedBoard();
		int bombsFound = 0;
		int x = 0;
		for (Square[] sq : gameBoard.getSquares()) {
			int y = 0;
			for (Square sq2 : sq) {
				boolean isABomb =  handler.hasABomb(sq2);
				if (isABomb) {
					System.out.println("BOMB FOUND ON array row " + x + " column " + y);
					bombsFound++;
				}
				y++;
			}
			x++;
		}
		assertEquals(99, bombsFound);
	}

}

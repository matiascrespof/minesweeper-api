package com.minesweeper.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minesweeper.api.handler.GameBoard;
import com.minesweeper.api.handler.GameBoardHandler;
import com.minesweeper.api.handler.GameBoardHandlerImpl;
import com.minesweeper.domain.GameSession;
import com.minesweeper.domain.User;
import com.minesweeper.helpers.RandomIdGenerator;
import com.minesweeper.repository.MinesweeperRepository;

@Service
public class GameService {

	private GameBoardHandler boardHandler;

	@Autowired
	private MinesweeperRepository minesweeperRepository;

	@PostConstruct
	public void init() {
		boardHandler = new GameBoardHandlerImpl();
	}

	public GameSession newGame(User user) throws Exception {
		GameBoard board = boardHandler.startNewBegginerBoard();
		GameSession gameSession = createSessionGame(user, board);
		minesweeperRepository.saveSessionGame(gameSession);
		return gameSession;
	};

	public GameSession getGame(String gameId) throws Exception {
		return minesweeperRepository.getSessionGame(gameId);
	};

	public GameSession revealPosition(String gameId, int rowP, int columnP) throws Exception {
		GameSession gameSession = minesweeperRepository.getSessionGame(gameId);
		GameBoard board = boardHandler.revealPosition(gameSession.getGameBoard(), rowP, columnP);
		gameSession.setGameBoard(board);
		minesweeperRepository.saveSessionGame(gameSession);
		return gameSession;
	};

	private GameSession createSessionGame(User user, GameBoard board) {
		GameSession game = new GameSession(RandomIdGenerator.getRandomId(), user, board);
		return game;
	}

	public void showSquare(String gameSessionId, int pX, int pY) {

	};
}

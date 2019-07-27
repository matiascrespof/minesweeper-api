package com.minesweeper.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minesweeper.api.handler.GameBoardHandler;
import com.minesweeper.api.handler.GameBoardHandlerImpl;
import com.minesweeper.domain.GameBoard;
import com.minesweeper.domain.GameSession;
import com.minesweeper.domain.User;
import com.minesweeper.helpers.RandomIdGenerator;
import com.minesweeper.repository.MinesweeperRepository;

@Service
public class GameService {
	Logger logger = LoggerFactory.getLogger(GameService.class);
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
		logger.info("Reveal Position for game on row and column", gameId, rowP, columnP);
		GameSession gameSession = minesweeperRepository.getSessionGame(gameId);
		GameBoard board = boardHandler.revealPosition(gameSession.getGameBoard(), rowP, columnP);
		gameSession.setGameBoard(board);
		minesweeperRepository.saveSessionGame(gameSession);
		return gameSession;
	};

	public GameSession flagSquare(String gameId, int rowP, int columnP) throws Exception {
		logger.info("FlagSquare for game on row and column", gameId, rowP, columnP);
		GameSession gameSession = minesweeperRepository.getSessionGame(gameId);
		GameBoard board = boardHandler.flag(gameSession.getGameBoard(), rowP, columnP);
		gameSession.setGameBoard(board);
		minesweeperRepository.saveSessionGame(gameSession);
		return gameSession;
	};

	private GameSession createSessionGame(User user, GameBoard board) {
		GameSession game = new GameSession(RandomIdGenerator.getRandomId(), user, board);
		return game;
	}

}

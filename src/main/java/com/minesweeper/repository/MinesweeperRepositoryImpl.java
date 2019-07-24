package com.minesweeper.repository;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.minesweeper.api.utils.GameStatus;
import com.minesweeper.domain.GameSession;

/**
 * This is just an storage simulation to keep alive certain games in memory TODO
 * : implement a repository game save action, consider use redis to store
 * elements
 * 
 * @author matias
 *
 */
public class MinesweeperRepositoryImpl implements MinesweeperRepository {

	Map<String, GameSession> gameSessionCache;
	public static final int MAX_CACHE_SIZE = 100;

	@PostConstruct
	public void init() {
		gameSessionCache = new HashMap<String, GameSession>();
	}

	@Override
	public boolean saveSessionGame(GameSession game) throws IOException {
		if (game != null && game.getGameId() != null) {
			saveGameInCache(game);
		} else {
			throw new IOException("Error Saving game");
		}
		return true;
	}

	@Override
	public GameSession getSessionGame(String gameSessionId) throws IOException {
		GameSession game = null;
		if (gameSessionId != null) {
			game = getGameFromCache(gameSessionId);
			if (game == null) {
				throw new IOException("Game for session id: " + gameSessionId + " not Found");
			}
		}

		return game;
	}

	private void saveGameInCache(GameSession game) {
		if (gameSessionCache.size() >= MAX_CACHE_SIZE) {
			// Cleaning some finished old games
			gameSessionCache.entrySet()
					.removeIf(g -> (Duration
							.between(g.getValue().getGameBoard().getStartDate().toInstant(), new Date().toInstant())
							.toHours() > 1)
							|| g.getValue().getGameBoard().getGameStatus() != GameStatus.IN_PROGRESS.toString());
		}
		gameSessionCache.put(game.getGameId(), game);
	}

	private GameSession getGameFromCache(String gameSessionId) {
		return gameSessionCache.get(gameSessionId);
	}

}

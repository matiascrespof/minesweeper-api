package com.minesweeper.repository;

import java.io.IOException;

import com.minesweeper.domain.GameSession;

//TODO not a repository yet @Repository
public interface MinesweeperRepository {

	boolean saveSessionGame(GameSession game) throws IOException;

	GameSession getSessionGame(String gameSessionId) throws IOException;

}

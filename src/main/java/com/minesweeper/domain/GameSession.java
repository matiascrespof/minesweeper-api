package com.minesweeper.domain;

public class GameSession {
	
	String gameId;
	User user;
	GameBoard gameBoard;
	
	public GameSession(String gameId, User user, GameBoard gameBoard) {
		this.gameId = gameId;
		this.user = user;
		this.gameBoard = gameBoard;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	

}

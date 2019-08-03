package com.minesweeper.domain;

/**
 * Transitive User
 * 
 * @author matias
 *
 */
public class User {
	String id;
	String userName;

	public User(String id, String userName) {
		this.userName = userName;
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

}

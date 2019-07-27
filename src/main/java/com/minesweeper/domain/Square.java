package com.minesweeper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author matias
 *
 */
public class Square {
	boolean hasAbomb;
	int rPosition;
	int cPosition;
	int bombsCloseTo;
	boolean opened;
	boolean marked;

	public Square(int cPosition, int rPosition, boolean hasAbomb) {
		this.hasAbomb = hasAbomb;
		this.opened = false;
		this.marked = false;
		this.cPosition = cPosition;
		this.rPosition = rPosition;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public int getBombsCloseTo() {
		return bombsCloseTo;
	}

	public void setBombsCloseTo(int bombsCloseTo) {
		this.bombsCloseTo = bombsCloseTo;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public int getrPosition() {
		return rPosition;
	}

	public void setrPosition(int rPosition) {
		this.rPosition = rPosition;
	}

	public int getcPosition() {
		return cPosition;
	}

	public void setcPosition(int cPosition) {
		this.cPosition = cPosition;
	}

	public void setHasAbomb(boolean hasAbomb) {
		this.hasAbomb = hasAbomb;
	}

	@JsonIgnore
	public boolean isHasAbomb() {
		return hasAbomb;
	}

}

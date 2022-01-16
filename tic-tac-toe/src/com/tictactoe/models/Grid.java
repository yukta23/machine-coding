package com.tictactoe.models;

import java.util.Arrays;

public class Grid {
	private int size;
	private char[][] gridMatrix;
	private int vacantSots;
	public Grid(int size) {
		this.size = size;
		this.vacantSots = size*size;
		this.gridMatrix = new char[size][size];
		for(char[] row : gridMatrix) {
			Arrays.fill(row, '-');
		}
	}
	public Grid() { // setting default grid size to 3
		this(3);
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}	
	public char[][] getGridMatrix() {
		return this.gridMatrix;
	}
	public void setGridMatrix(char[][] gridMatrix) {
		this.gridMatrix = gridMatrix;
	}
	public int getVacantSots() {
		return vacantSots;
	}
	public void setVacantSots(int vacantSots) {
		this.vacantSots = vacantSots;
	}
}
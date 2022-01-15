package com.snakeLadder.models;

import java.util.ArrayList;

public class Board {
	public int size;
	public ArrayList<Integer> board;
	
	public Board(int size) {
		this.size = (size * size);
		board = new ArrayList<Integer>(size + 1);
	}
}
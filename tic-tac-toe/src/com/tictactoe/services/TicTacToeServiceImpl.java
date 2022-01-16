package com.tictactoe.services;

import com.tictactoe.models.Grid;

public class TicTacToeServiceImpl implements TicTacToeService {

	@Override
	public void displayGrid(Grid grid) {
		char[][] gridMatrix = grid.getGridMatrix();
		for(char row[] : gridMatrix) {
			for(char element : row) 
				System.out.print(element + " ");
			System.out.println();
		}
	}

	@Override
	public int generateRandomPostionInGrid(int gridSize) {
		int min = 0, max = (gridSize * gridSize) - 1;
		return min + (int)(Math.random() * ((max-min) + 1));
	}

}

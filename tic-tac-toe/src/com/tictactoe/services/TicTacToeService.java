package com.tictactoe.services;

import com.tictactoe.models.Grid;

public interface TicTacToeService {
	void displayGrid(Grid grid);
	int generateRandomPostionInGrid(int gridSize);
}

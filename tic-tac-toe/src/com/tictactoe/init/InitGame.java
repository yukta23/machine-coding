package com.tictactoe.init;

import java.util.ArrayList;

import com.tictactoe.models.Grid;
import com.tictactoe.models.Player;
import com.tictactoe.services.TicTacToeService;
import com.tictactoe.services.TicTacToeServiceImpl;

public class InitGame {
	private static ArrayList<Player> players;
	private static Grid grid;
	private static TicTacToeService ticService = new TicTacToeServiceImpl();
	private static boolean isGameOver = false;
	public static void init(ArrayList<Player> pList, ArrayList<Integer> positionsInput) {
		players = pList;
		grid = new Grid();
		ticService.displayGrid(grid);
		for( int i = 0; i < positionsInput.size(); i += 2 ) {
			Player currPlayer = (i % 4 == 0) ? pList.get(0) : pList.get(1);
			char symbol = currPlayer.getSymbol();
 			validateMove(grid, positionsInput.get(i), positionsInput.get(i+1), symbol);
 			if (isGameOver) {
 				System.out.println(currPlayer.getName() + " won the game");
				break;
			}
 			if (grid.getVacantSots() == 0) {
 				System.out.println("Game Over");
 				break;
 			}
		}
	}
	
	public static void validateMove(Grid grid, int Xpos, int Ypos, char symbol) {
		char[][] gridMatrix = grid.getGridMatrix();
		if (Character.compare(gridMatrix[Xpos][Ypos], '-') == 0) {
			gridMatrix[Xpos][Ypos] = symbol;
		} else {
			System.out.println("Invalid move");
			return;
		}
		grid.setGridMatrix(gridMatrix);
		grid.setVacantSots(grid.getVacantSots() - 1);
		ticService.displayGrid(grid);
		isGameOver = checkForLine(gridMatrix, Xpos, Ypos, symbol);
	}
	
	private static boolean checkForLine(char[][] matrix, int x, int y, char symbol ) {
		boolean row = true, col = true, diag1 = true, diag2 = true;
		for( int i = 0; i < matrix.length; i++ ) {
			for( int j = 0; j < matrix[i].length; j++ ) {
				if (matrix[x][j] != symbol)
					row = false;
				if (matrix[i][y] != symbol)
					col = false;
				if ( i == j && matrix[i][j] != symbol)
					diag1 = false;
				if (i + j == matrix.length - 1 && matrix[i][j] != symbol)
					diag2 = false;
			}
		}
		return row || col || diag1 || diag2;
	}
}

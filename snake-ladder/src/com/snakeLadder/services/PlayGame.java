package com.snakeLadder.services;

import java.util.ArrayList;

import com.snakeLadder.models.Board;
import com.snakeLadder.models.Ladder;
import com.snakeLadder.models.Player;
import com.snakeLadder.models.Snake;

public class PlayGame {
	ArrayList<Snake> snakes;
	ArrayList<Ladder> ladders;
	ArrayList<Player> players;
	Board gameBoard;
	int numPlayersDone;
	public void init(ArrayList<Snake> snakes, ArrayList<Ladder> ladders, int boardSize, ArrayList<Player> players ) {
		this.snakes = snakes;
		this.ladders = ladders;
		this.players = players;
		this.gameBoard = new Board(boardSize);
		this.numPlayersDone = 0;
		this.playGame();
	}
	
	public void playGame() {
		Player currPlayer;
		int diceNum;
		while( numPlayersDone < players.size() - 1 ) {
			for( int i = 0; i < players.size(); i++ ) {
				currPlayer = players.get(i);
				diceNum = this.generateRandomNumber();
				System.out.println("Current Player: " + currPlayer.getName() + ", Number Generated " + diceNum);
				this.move(currPlayer, diceNum);
			}
		}
	}
	
	private int generateRandomNumber() {
		int Max = 6, Min = 1;
	    return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
	
	private void move(Player p, int num ) {
		if (p.getPos() + num > this.gameBoard.size) {
			return;
		} else if (p.getPos() + num == this.gameBoard.size) {
			System.out.println(p.getName() + " finished the game!!");
			this.numPlayersDone++;
			return;
		}
		p.setPos(p.getPos() + num);
		boolean nextMoveNeeded = true;
		while(nextMoveNeeded) {
			int changePos = this.checkForSnakeLadder(p.getPos());
			if (changePos != -1 ) {
				p.setPos(changePos);
			} else {
				nextMoveNeeded = false;
			}
		}
		System.out.println(p.getName() + " final position: " + p.getPos());
		if(p.getPos() == this.gameBoard.size) {
			System.out.println(p.getName() + " finished the game!!");
			this.numPlayersDone++;
		}
	}
	
	private int checkForSnakeLadder(int position) {
		for(int i = 0 ; i < snakes.size(); i++ ) {
			if (snakes.get(i).getHead() == position ) 
				return snakes.get(i).getTail();
		}
		for( int i = 0; i < ladders.size(); i++ ) {
			if (ladders.get(i).getHead() == position)
				return ladders.get(i).getTail();
		}
		return -1;
	}
}

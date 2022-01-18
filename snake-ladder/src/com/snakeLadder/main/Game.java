package com.snakeLadder.main;

import java.util.ArrayList;

import com.snakeLadder.models.Ladder;
import com.snakeLadder.models.Player;
import com.snakeLadder.models.Snake;


public class Game {
	private ArrayList<Snake> snakes;
	private ArrayList<Ladder> ladders;
	private ArrayList<Player> players;
	private int boardSize;
	private int numPlayersLeft;
	public Game(ArrayList<Snake> snakes, ArrayList<Ladder> ladders, ArrayList<Player> players, int size) {
		this.snakes = snakes;
		this.ladders = ladders;
		this.players = players;
		this.numPlayersLeft = players.size() - 1; // out of total 3, 2 should finish
		this.boardSize = size * size;
	}
	public void startGame() {
		int numPlayers = this.players.size();
		while(this.numPlayersLeft > 0) {
			for( int i = 0; i < numPlayers; i++ ) {
				if (this.numPlayersLeft == 0)
					break;
				Player currPlayer = this.players.get(i);
				int num = this.generateRandomNumber();
				this.playMove(currPlayer, num);
			}
		}
		System.out.println("Game Over!!");
	}
	
	private int generateRandomNumber() {
		int min = 1, max = 6;
		return (int)(Math.random() * (max - min + 1)) + min;  
	}
	
	private void playMove(Player p, int num) {
		int currPos = p.getPosition();
		if (currPos + num == this.boardSize ) {
			System.out.println(p.getName() + " has won !!");
			this.numPlayersLeft--;
			p.setPosition(currPos + num);
			return;
		}
		// skip the turn
		if (currPos + num > 100 ) {
			return;
		}
		int initialPos = p.getPosition();
		currPos += num;
		boolean isCheckNeeded = true;
		while( isCheckNeeded ) {
			int posChange = this.checkForSnakeLadder(currPos);
			if ( posChange == -1 ) 
				isCheckNeeded = false;
			else
				currPos = posChange;
		}
		p.setPosition(currPos);
		System.out.println(p.getName() + " rolled a " + num + " and moved from " + initialPos + " to " +
		currPos);
		if ( currPos == this.boardSize ) {
			System.out.println(p.getName() + " wins the game");
			this.numPlayersLeft--;
		}
	}
	
	private int checkForSnakeLadder(int pos) {
		for(Snake s : snakes) {
			if(s.getHead() == pos)
				return s.getTail();
		}
		for( Ladder l : ladders ) {
			if(l.getStart() == pos ) 
				return l.getEnd();
		}
		return -1;
	}
}

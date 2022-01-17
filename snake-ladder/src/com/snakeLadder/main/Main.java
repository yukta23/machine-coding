package com.snakeLadder.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.snakeLadder.models.Ladder;
import com.snakeLadder.models.Player;
import com.snakeLadder.models.Snake;
import com.snakeLadder.services.PlayGame;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number of snakes ");
		int numSnakes = sc.nextInt();
		int snakeHead, snakeTail;
		ArrayList<Snake> snakes = new ArrayList<Snake>();
		for( int i = 0; i < numSnakes; i++ ) {
			snakeHead = sc.nextInt();
			snakeTail = sc.nextInt();
			Snake newSnake = new Snake(snakeHead, snakeTail);
			snakes.add(newSnake);
		}
		int  numLadders = sc.nextInt();
		int ladderHead, ladderTail;
		ArrayList<Ladder> ladders = new ArrayList<Ladder>();
		for( int i = 0; i < numLadders; i++ ) {
			ladderHead = sc.nextInt();
			ladderTail = sc.nextInt();
			Ladder newLadder = new Ladder(ladderHead, ladderTail);
			ladders.add(newLadder);
		}
		int numPlayers = sc.nextInt();
		sc.nextLine();
		ArrayList<Player> players = new ArrayList<Player>();
		for( int i = 0; i < numPlayers; i++ ) {
			String playerName = sc.nextLine();
			Player newPlayer = new Player(playerName);
			players.add(newPlayer);
		}
		int boardSize = 10;
		PlayGame game = new PlayGame();
		game.init(snakes, ladders, boardSize, players);
		sc.close();
	}
}

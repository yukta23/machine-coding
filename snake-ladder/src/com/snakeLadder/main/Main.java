package com.snakeLadder.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.snakeLadder.models.Ladder;
import com.snakeLadder.models.Player;
import com.snakeLadder.models.Snake;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numSnakes = sc.nextInt();
		ArrayList<Snake> snakes = new ArrayList<>(numSnakes);
		int head, tail;
		for( int i = 0; i < numSnakes; i++ ) {
			head = sc.nextInt();
			tail = sc.nextInt();
			snakes.add(new Snake(head, tail));
		}
		int numLadders = sc.nextInt();
		ArrayList<Ladder> ladders = new ArrayList<>(numLadders);
		for( int i = 0; i < numLadders; i++ ) {
			head = sc.nextInt();
			tail = sc.nextInt();
			ladders.add(new Ladder(head, tail));
		}
		int numPlayers = sc.nextInt();
		String name;
		ArrayList<Player> players = new ArrayList<>(numPlayers);
		for( int i = 0; i < numPlayers; i++ ) {
			name = sc.next();
			players.add(new Player(name));
		}
		Game game = new Game(snakes, ladders, players, 10);
		game.startGame();
		sc.close();
	}
}

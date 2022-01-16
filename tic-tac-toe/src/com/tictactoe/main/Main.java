package com.tictactoe.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.tictactoe.init.InitGame;
import com.tictactoe.models.Player;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char symbol1 = sc.next().charAt(0);
		//next takes in the next token
		String player1 = sc.next();
		char symbol2 = sc.next().charAt(0);
		String player2 = sc.next();
		Player p1 = new Player(player1, symbol1);
		Player p2 =  new Player(player2, symbol2);
		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(p1, p2));
		ArrayList<Integer> positionsInput = new ArrayList<>();
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int val = sc.nextInt();
				positionsInput.add(val - 1);
			} else {
				String input = sc.next();
				if(input.equalsIgnoreCase("exit")) {
					System.out.println("Exiting");
					break;
				}
			}
		}
			for(int x : positionsInput)
				System.out.print(x + " ");
			System.out.println();
		InitGame.init(players, positionsInput);
		sc.close();
	}

}

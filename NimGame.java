//Tianqi ZHOU
//LoginID:tiazhou
//studientID:1015263
import java.util.Scanner;

public class NimGame {
	int stones_number;
	int upper_bound;
	NimPlayer player1;
	NimPlayer player2;

	void play(Scanner keyboard) {

		player1.played_games++;
		player2.played_games++;

		System.out.println();
		System.out.println("Initial stone count: " + stones_number);
		System.out.println("Maximum stone removal: " + upper_bound);
		System.out.println("Player 1: " + player1.given_name + " " + player1.family_name);
		System.out.println("Player 2: " + player2.given_name + " " + player2.family_name);
		System.out.println();

		printstars(stones_number);
		for (;;) {
			if (stones_number > 0)// player1's turn
			{
				System.out.println(player1.given_name + "'s turn - remove how many?");
				
				int removeNum = player1.removeStone(keyboard, stones_number, upper_bound);
				System.out.println();
	
				while (removeNum > upper_bound || removeNum > stones_number || removeNum <= 0) {
					System.out.println("Invalid move. You must remove between 1 and " + Math.min(upper_bound, stones_number) + " stones.");
					System.out.println();
					
					printstars(stones_number);
					System.out.println(player1.given_name + "'s turn - remove how many?");

					removeNum = player1.removeStone(keyboard, stones_number, upper_bound);
					System.out.println();
				}
				
				stones_number -= removeNum;
				printstars(stones_number);

				if (stones_number > 0) // player2's turn
				{
					System.out.println(player2.given_name + "'s turn - remove how many?");

					removeNum = player2.removeStone(keyboard, stones_number, upper_bound);
					System.out.println();
					
					while (removeNum > upper_bound || removeNum > stones_number || removeNum <= 0) {
						System.out.println("Invalid move. You must remove between 1 and " + Math.min(upper_bound, stones_number) + " stones.");
						System.out.println();
						
						printstars(stones_number);
						System.out.println(player2.given_name + "'s turn - remove how many?");
						
						removeNum = player2.removeStone(keyboard, stones_number, upper_bound);
						System.out.println();
					}

					stones_number -= removeNum;
					printstars(stones_number);

				} else {

					player2.won_games++;
					System.out.println("Game Over\n" + player2.given_name + " " + player2.family_name + " wins!\n");
					break;
				}
			} else {
				player1.won_games++;
				System.out.println("Game Over\n" + player1.given_name + " " + player1.family_name + " wins!\n");
				break;
			}
		}
	}

	void printstars(int number) {
		if (number <= 0) {
			return;
		} else {
			System.out.print(number + " stones left:");
			for (int i = 1; i <= number; i++) {
				System.out.print(" *");
			}
			System.out.print("\n");
		}
	}
}

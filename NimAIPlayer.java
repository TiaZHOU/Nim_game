//Tianqi ZHOU
//LoginID:tiazhou
//studientID:1015263
import java.util.Random;
import java.util.Scanner;

/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.

	public NimAIPlayer() {
		type = 1;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";

		return move;
	}

	@Override
	protected int removeStone(Scanner keyboard, int stonesLeft, int maxPick) {
		int upperbound = Math.min(stonesLeft, maxPick);

		for (int pick = 1; pick <= upperbound; pick++) {
			if (stonesLeft - pick == 1) {
				return pick;
			}
		}

		for (int pick = 1; pick <= upperbound; pick++) {
			if ((stonesLeft - pick - 1) % (maxPick + 1) == 0) {
				return pick;
			}
		}
		
		Random random = new Random(System.currentTimeMillis());
		return random.nextInt(maxPick) + 1;
	}
}

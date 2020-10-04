//Tianqi ZHOU
//LoginID:tiazhou
//studientID:1015263
import java.util.Scanner;

class NimHumanPlayer extends NimPlayer {
	
	public NimHumanPlayer() {
		type = 0;
	}
	
	@Override
	protected int removeStone(Scanner keyboard, int stonesLeft, int maxPick) {
		int remove_number = keyboard.nextInt();
		keyboard.nextLine();
		return remove_number;
	}
	
	
}

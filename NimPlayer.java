//Tianqi ZHOU
//LoginID:tiazhou
//studientID:1015263
import java.util.Scanner;


public abstract class NimPlayer {

	protected String username;

	protected String given_name;
	protected String family_name;
	protected int played_games = 0;
	protected int won_games = 0;
	protected int type = 0;
	
	public static NimPlayer parse(String data) {
		String[] items = data.split(",");
		
		if(items[0].equals("0")){
			NimPlayer player = new NimHumanPlayer();
			player.username = items[1];
			player.given_name = items[2];
			player.family_name = items[3];
			player.played_games = Integer.parseInt(items[4]);
			player.won_games = Integer.parseInt(items[5]);
			
			return player;
		}else {
			NimPlayer player = new NimAIPlayer();
			player.username = items[1];
			player.given_name = items[2];
			player.family_name = items[3];
			player.played_games = Integer.parseInt(items[4]);
			player.won_games = Integer.parseInt(items[5]);
			
			return player;
		}
	}
	
	@Override
	public String toString() {
		return type + "," + username + "," + given_name + "," + family_name + "," + played_games + "," + won_games;
	}

	protected abstract int removeStone(Scanner keyboard, int stonesLeft, int maxPick);
	
	public String getUsername() {
		return username;
	}

	public NimPlayer() {
		super();
	}


	public void printStatistics() {
		// lskywalker,Luke,Skywalker,3 games,3 wins
		System.out.println(
				username + "," + given_name + "," + family_name + "," + played_games + " games," + won_games + " wins");
	}

	public void printRank() {
		// 100% | 03 games | Luke Skywalker
		String percent = String.format("%d%%", Math.round((getRate() * 100)));
		System.out.println(String.format("%-5s| %02d games | %s %s", percent, played_games,
				given_name, family_name));
	}

	protected double getRate() {
		if (played_games == 0) {
			return 0;
		}
		return won_games*1.0 / played_games;
	}

}
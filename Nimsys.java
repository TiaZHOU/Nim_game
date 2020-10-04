//Tianqi ZHOU
//LoginID:tiazhou
//studientID:1015263
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class Nimsys {
	public static final String DATAFILE = "players.txt";
	
	public static NimPlayer[] players = new NimPlayer[100];
	public static int size = 0;

	//read players from file
	public static void readPlayersFromFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATAFILE));
			String line = reader.readLine();
			while(line != null) {
				if(!line.isEmpty()) {
					NimPlayer player = NimPlayer.parse(line);
					players[size++] = player;
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			//System.out.println("error happens when reading players from file.");
		}
	}
	
	//save players to file
	public static void savePlayersToFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATAFILE));
			for(int i=0;i<size;i++) {
				writer.write(players[i].toString() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("error happens when saving players to file.");
		}
	}
	
	public static void main(String[] args) {
		Scanner Keyboard = new Scanner(System.in);
		System.out.println("Welcome to Nim\n");

		//load data
		readPlayersFromFile();
		
		while (true) {
			System.out.print("$");

			String line = Keyboard.nextLine();
			String[] parts = line.split("\\s+");

			String cmd = parts[0].toLowerCase();

			if (cmd.equals("exit")) {
				System.out.println();
				//save data when exits.
				savePlayersToFile();
				return;
			} else if (cmd.equals("startgame")) {
				if(parts.length != 2) {
					System.out.println("Incorrect number of arguments supplied to command.\n");
				}else {
					String paras = parts[1];
					
					String[] tokens = paras.split(",");
					if(tokens.length != 4) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {
						int stones = Integer.parseInt(tokens[0]);
						int remove = Integer.parseInt(tokens[1]);
						String username1 = tokens[2];
						String username2 = tokens[3];

						int temp1 = getPlayer(username1);
						int temp2 = getPlayer(username2);

						if (temp1 == -1 || temp2 == -1) {
							System.out.println("One of the players does not exist.");
							System.out.println();
						} else {
							NimGame game = new NimGame();
							game.player1 = players[temp1];
							game.player2 = players[temp2];
							game.stones_number = stones;
							game.upper_bound = remove;

							game.play(Keyboard);
						}
					}
				}
			} else if (cmd.equals("addplayer")) {
				if(parts.length != 2) {
					System.out.println("Incorrect number of arguments supplied to command.\n");
				}else {
					String paras = parts[1];
					
					String[] tokens = paras.split(",");
					if(tokens.length != 3) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {
						NimHumanPlayer player = new NimHumanPlayer();
						player.username = tokens[0];
						player.family_name = tokens[1];
						player.given_name = tokens[2];

						int temp = getPlayer(player.username);
						if (temp == -1) {
							players[size++] = player;
						} else {
							System.out.println("The player already exists.");
						}

						System.out.println();
					}
				}
			} else if (cmd.equals("addaiplayer")) {
				if(parts.length != 2) {
					System.out.println("Incorrect number of arguments supplied to command.\n");
				}else {
					String paras = parts[1];
					
					String[] tokens = paras.split(",");
					if(tokens.length != 3) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {
						NimAIPlayer player = new NimAIPlayer();
						player.username = tokens[0];
						player.family_name = tokens[1];
						player.given_name = tokens[2];

						int temp = getPlayer(player.username);
						if (temp == -1) {
							players[size++] = player;
						} else {
							System.out.println("The player already exists.");
						}

						System.out.println();
					}
				}
				

			}  else if (cmd.equals("removeplayer")) {
				if (parts.length == 1) {
					System.out.println("Are you sure you want to remove all players? (y/n)");
					String s = Keyboard.next().toLowerCase();
					Keyboard.nextLine();
					if (s.charAt(0) == 'y') {
						size = 0;
					}
					
					System.out.println();
				} else {
					if(parts.length != 2) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {
						String paras = parts[1];
						
						String[] tokens = paras.split(",");
						if(tokens.length != 1) {
							System.out.println("Incorrect number of arguments supplied to command.\n");
						}else {
							String username = tokens[0];

							int temp = getPlayer(username);
							if (temp == -1) {
								System.out.println("The player does not exist.");
							} else {
								// remove the player
								for (int i = temp; i < size - 1; i++) {
									players[i] = players[i + 1];
								}
								size--;
							}
							
							System.out.println();
						}
					}
				}

			
			} else if (cmd.equals("editplayer")) {
				if(parts.length != 2) {
					System.out.println("Incorrect number of arguments supplied to command.\n");
				}else {
					String paras = parts[1];
					
					String[] tokens = paras.split(",");
					if(tokens.length != 3) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {

						NimPlayer player = new NimHumanPlayer();
						player.username = tokens[0];
						player.family_name = tokens[1];
						player.given_name = tokens[2];

						int temp = getPlayer(player.username);
						if (temp == -1) {
							System.out.println("The player does not exist.");
						} else {
							players[temp].username = player.username;
							players[temp].family_name = player.family_name;
							players[temp].given_name = player.given_name;
						}

						System.out.println();
					}
				}
			} else if (cmd.equals("resetstats")) {
				if (parts.length == 1) {
					System.out.println("Are you sure you want to reset all player statistics? (y/n)");
					String s = Keyboard.next().toLowerCase();
					Keyboard.nextLine();
					if (s.charAt(0) == 'y') {
						for (int i = 0; i < size; i++) {
							players[i].won_games = 0;
							players[i].played_games = 0;
						}
					}
					
					System.out.println();
				} else {
					if(parts.length != 2) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {
						String paras = parts[1];
						
						String[] tokens = paras.split(",");
						if(tokens.length != 1) {
							System.out.println("Incorrect number of arguments supplied to command.\n");
						}else {
							String username = tokens[0];

							int temp = getPlayer(username);
							if (temp == -1) {
								System.out.println("The player does not exist.");
							} else {
								// reset the player
								players[temp].won_games = 0;
								players[temp].played_games = 0;
							}
							
							System.out.println();
						}
					}
				}
			} else if (cmd.equals("displayplayer")) {
				if (parts.length == 1) {
					Comparator<NimPlayer> comparator = new Comparator<NimPlayer>() {
						@Override
						public int compare(NimPlayer p1, NimPlayer p2) {
							return p1.getUsername().compareTo(p2.getUsername());
						}
					};

					//sort players by user name
					for (int i = 0; i < size - 1; i++) {
						for (int j = 0; j < size - 1 - i; j++) {
							if (comparator.compare(players[j], players[j + 1]) > 0) {
								NimPlayer t = players[j];
								players[j] = players[j + 1];
								players[j + 1] = t;
							}
						}
					}
					
					for (int i = 0; i < size; i++) {
						players[i].printStatistics();
					}
					
					System.out.println();
				} else {
					
					if(parts.length != 2) {
						System.out.println("Incorrect number of arguments supplied to command.\n");
					}else {
						String paras = parts[1];
						
						String[] tokens = paras.split(",");
						if(tokens.length != 1) {
							System.out.println("Incorrect number of arguments supplied to command.\n");
						}else {
							String username = tokens[0];

							int temp = getPlayer(username);
							if (temp == -1) {
								System.out.println("The player does not exist.");
							} else {
								// print the player
								players[temp].printStatistics();
							}
							
							System.out.println();
						}
					}
				}
			} else if (cmd.equals("rankings")) {
				String order = "desc";

				if (parts.length == 1) {
					order = "desc";
				} else {
					String paras = parts[1];
					String[] tokens = paras.split(",");

					order = tokens[0];
				}

				final String orders = order;
				Comparator<NimPlayer> comparator = new Comparator<NimPlayer>() {

					@Override
					public int compare(NimPlayer p1, NimPlayer p2) {
						if (p1.getRate() == p2.getRate()) {
							return p1.username.compareTo(p2.username);
						} else if (p1.getRate() < p2.getRate()) {
							if (orders.equals("asc")) {
								return -1;
							} else {
								return 1;
							}
						} else {
							if (orders.equals("asc")) {
								return 1;
							} else {
								return -1;
							}
						}
					}
				};

				for (int i = 0; i < size - 1; i++) {
					for (int j = 0; j < size - 1 - i; j++) {
						if (comparator.compare(players[j], players[j + 1]) > 0) {
							NimPlayer t = players[j];
							players[j] = players[j + 1];
							players[j + 1] = t;
						}
					}
				}

				for (int i = 0; i < size && i < 10; i++) {
					players[i].printRank();
				}

				System.out.println();
			}else {
				System.out.println(String.format("'%s' is not a valid command.\n", cmd));
			}

		}
	}

	private static int getPlayer(String username) {
		for (int i = 0; i < size; i++) {
			if (players[i].username.equals(username)) {
				return i;
			}
		}
		return -1;
	}

}

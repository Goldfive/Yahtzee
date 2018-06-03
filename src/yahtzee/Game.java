package yahtzee;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	private static final int maxPlayers = 5;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static boolean activeGame = false;
	private static int currentTurn;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Game.introduction();
		Game.loop();
	}

	private static void introduction()
	{
		System.out.println("Welkom bij Yahtzee! \n\rOm te beginnen met spelen, typ !start\nOm het overzicht van alle commando's te zien, typ !help");
	}
	
	private static void loop()
	{
		boolean Continue = true;
		while (Continue)
		{
			if (activeGame)
			{
				System.out.println("Speler "+(currentTurn+1)+" ("+players.get(currentTurn).name+") is nu aan de beurt! Gebruik !gooi om de dobbelstenen te gooien, !lock om dobbelstenen te locken en !score om je score ergens onder te brengen. Help: !help");
				getCurrentPlayer().getScore().printScoreBoard();
				getCurrentPlayer().printDice();
				System.out.println("Aantal keer gooien: "+(2-getCurrentPlayer().getTurn()));
			}
				Continue = handleCommands(getArguments(readInput()));
		}
		sc.close(); //Close scanner at the end of the program
	}
	
	public static String[] getArguments(String s) //Every word is its own command
	{
		String[] arr = s.split(" ");
		return arr;
	}
	
	static String readInput()
	{
		return sc.nextLine();
	}
	
	public static boolean handleCommands(String[] t)
	{
	
		switch (t[0])
		{
			case "!help":
				help();
				break;
			case "!start":				
				if (t.length == 1 || (!isInteger(t[1])))
					start(1);
				else
					start(Integer.parseInt(t[1])); //Converts from string to int
				break;
			case "!gooi":
				if (!activeGame)
					System.out.println("Er is nog geen game gestart. Start een game met !start");
				else
				{
					getCurrentPlayer().throwDice();
					nextTurn();
				}
				break;
			case "!lock":
				if (!activeGame)
					System.out.println("Er is nog geen game gestart. Start een game met !start");
				else
				{
					getCurrentPlayer().lock(t);
				}
				break;
			case "!overview":
				if (t.length > 1 && !Game.isInteger(t[1]))
					getCurrentPlayer().getScore().printScoreBoard();
				else
					getPlayerById(Integer.parseInt(t[1])-1).getScore().printScoreBoard();
				break;
			case "!score":
				if (!Game.isInteger(t[1]))
					System.out.println("Stout! Dat is geen getal!");
				else
				{
					getCurrentPlayer().setScore = getCurrentPlayer().getScore().setScore(Integer.parseInt(t[1]));
					
					nextTurn();
				}
				
		}
		if (getCurrentPlayer().getTurn() >= 2 && getCurrentPlayer().setScore)
		{
			if (getCurrentPlayer().getScore().allDone())
				System.out.println("Alle vakjes zijn ingevuld - je bent klaar met spelen! Je kunt nog steeds je scoreformulier opvragen; als je een nieuw spel wilt starten, typ !start");
			getCurrentPlayer().setTurn(0);
			getCurrentPlayer().resetDice();
		}
		return true;
	}
	
	private static Player getCurrentPlayer()
	{
		return players.get(currentTurn);
	}
	
	private static Player getPlayerById(int id)
	{
		for (Player p: players)
		{
			if (p.playerId == id)
				return p;
		}
		return null;
	}
	
	private static void start(int totalPlayers)
	{
		players.clear();
		for (int i = 0; i < totalPlayers && totalPlayers <= maxPlayers; i++)
		{
			System.out.println("Speler "+(i+1)+", onder welke naam gaat u proberen de highscore te doorbreken?");
			String name = readInput();
			players.add(new Player(name, i));
		}
		
		activeGame = true;
		currentTurn = 0;
	}
	
	private static void nextTurn()
	{
		
		if (getCurrentPlayer().setScore)
		{
			getCurrentPlayer().resetTurn();
			nextPlayer();
		}
		else if (getCurrentPlayer().getTurn() == 0)
			nextPlayer();
			
	}
	
	private static void nextPlayer()
	{
		
		if (currentTurn++ >= players.size()-1)
		{
			currentTurn = 0;
		}

		boolean gameDone = true;
		for (Player p: players)
		{
			if (!p.getScore().allDone())
				gameDone = false;
		}
		if (gameDone)
		{
			activeGame = false;
			int[] scores = new int[players.size()];
			for (Player p: players)
			{
				scores[p.playerId] = p.getScore().getTotalScore();
			}
			System.out.println(players.get(Game.getIndexOfLargest(scores)).name+" heeft de hoogste score met "+players.get(Game.getIndexOfLargest(scores)).getScore().getTotalScore());
		}
	}
	


	public static int getIndexOfLargest( int[] array )
	{
	  if ( array == null || array.length == 0 ) return -1; // null or empty
	
	  int largest = 0;
	  for ( int i = 1; i < array.length; i++ )
	  {
	      if ( array[i] > array[largest] ) largest = i;
	  }
	  return largest; // position of the first largest found
	}


	
	private static void help()
	{
		System.out.println("Alle commando's zijn als volgt: \r\n \r\n !start <aantal spelers> - Voorbeeld: !start 2 (start het spel met 2 spelers) - standaardwaarde is 1 speler");
		System.out.println("!gooi - gooi alle niet-gelockte dobbelstenen.\n!lock <1-5> - Lock de dobbelsteen met positie X. Meerdere tegelijkertijd locken is ook mogelijk: !lock 1 3 5 om dobbelstenen met positie 1, 3 en 5 te locken. Gebruik dit commando ook om een dobbelsteen te un-locken."
				+"\n!overview <speler-id> - vraag het score formulier van een bepaalde speler op. Leeg laten geeft de speler die nu aan de beurt is."
				+ "\n!score <1 t/m 13> - zet je score neer in het daarvoor bestemde vakje. Dit commando eindigt je beurt.");
	}
	
	 public static boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s); // s is a valid integer
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // s is not an integer, do nothing
	      }
	 
	      return isValidInteger;
	   }
	}


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
				System.out.println("Speler "+(currentTurn+1)+"("+players.get(currentTurn).name+") is nu aan de beurt! Gebruik !gooi om de dobbelstenen te gooien!");
			Continue = handleCommands(getArguments(readInput()));
		}
		sc.close(); //Close scanner at the end of the program
	}
	
	private static String[] getArguments(String s) //Every word is its own command
	{
		String[] arr = s.split(" ");
		return arr;
	}
	
	private static String readInput()
	{
		return sc.nextLine();
	}
	
	private static boolean handleCommands(String[] t)
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
				
			case "!lock":
				if (!activeGame)
					System.out.println("Er is nog geen game gestart. Start een game met !start");
				
		}
		return true;
	}
	
	private static void start(int totalPlayers)
	{
		for (int i = 0; i < totalPlayers && totalPlayers <= maxPlayers; i++)
		{
			System.out.println("Speler "+(i+1)+", onder welke naam gaat u proberen de highscore te doorbreken?");
			String name = readInput();
			players.add(new Player(name, i));
		}
		
		activeGame = true;
		currentTurn = 0;
	}
	
	private static void help()
	{
		System.out.println("Alle commando's zijn als volgt: \r\n \r\n !start <aantal spelers> - Voorbeeld: !start 2 (start het spel met 2 spelers) - standaardwaarde is 1 speler");
		System.out.println("!gooi - gooi alle niet-gelockte dobbelstenen.\n!lock <1-5> - Lock de dobbelsteen met positie X. Meerdere tegelijkertijd locken is ook mogelijk: !lock 1 3 5 om dobbelstenen met positie 1, 3 en 5 te locken.");
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


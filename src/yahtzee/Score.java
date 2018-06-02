package yahtzee;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Score {
	
	Die[] dice;
	int[] counter = {0, 0, 0, 0, 0, 0};
	int[] overview = new int[14];
	
	public static void printScoreboard()
	{
		System.out.println(
				"------------Score overzicht-----------------"
				+ "\n(1) Eenen:        (7) Drie gelijke:      "
				+ "\n(2) Tweeën:       (8) Vier gelijke:"
				+ "\n(3) Drieën:       (9) Kleine straat:"
				+ "\n(4) Vieren:      (10) Grote straat:"
				+ "\n(5) Vijven:      (11) Full House"
				+ "\n(6) Zessen:      (12) Kans:"
				+ "\n                 (13) Yahtzee:"
				+ "\n-----------------------------------------"
				+ "\nPunten:          - Punten:"
				+ "\nTot bonus:"
				+ "\n-------------------------------------");

	}
	
	Score(Die[] dice)
	{
		this.dice = dice;
	}
	
	public void countEyes()
	{
		for (int i = 0; i < 5; i++)
		{
			System.out.println((dice[i].getValue()));
			counter[(dice[i].getValue()-1)]++;
		}
	}
	
	public void resetCounter()
	{
		Arrays.fill(counter, 0);
	}
	
	public int getScore(int choice)
	{
		boolean contains3 = IntStream.of(counter).anyMatch(x -> x == 3);
		if (contains3 && choice == 7)
		{
			
		}
	}
	
}

package yahtzee;

import java.util.Arrays;

public class Score {
	
	private Die[] dice;
	private Integer[] counter = {0, 0, 0, 0, 0, 0};
	private int[] overview = new int[14];
	private int sum_upper = 0;
	private int sum_lower = 0;
	
	public void printScoreBoard()
	{
		calculateTotal();
		System.out.println(
				"------------Score overzicht-----------------"
				+ "\n(1) Eenen: "+showScore(1)+"       (7) Drie gelijke: "+showScore(7)
				+ "\n(2) Tweeën: "+showScore(2)+"      (8) Vier gelijke: "+showScore(8)
				+ "\n(3) Drieën: "+showScore(3)+"      (9) Kleine straat: "+showScore(9)
				+ "\n(4) Vieren: "+showScore(4)+"     (10) Grote straat: "+showScore(10)
				+ "\n(5) Vijven: "+showScore(5)+"     (11) Full House: "+showScore(11)
				+ "\n(6) Zessen: "+showScore(6)+"     (12) Kans: "+showScore(12)
				+ "\n                  (13) Yahtzee: "+showScore(13)
				+ "\n-----------------------------------------"
				+ "\nPunten:  "+showScore(14)+"        - Punten: "+showScore(16)
				+ "\nTot bonus: "+showScore(15)+""
				+ "\n-------------------------------------");

	}
	
	Score(Die[] dice)
	{
		this.dice = dice;
		for (int i = 0; i < 14; i++)
		{
			overview[i] = -1;
		}
	}
	
	private String showScore(int choice)
	{
		if (choice == 14)
			return ""+sum_upper;
		else if (choice == 15)
			return ""+(63 - sum_upper);
		else if (choice == 16)
			return ""+sum_lower;
		else
			return (overview[choice-1] == -1 ? " " : ""+overview[choice-1]);
	}
	
	public int getTotalScore()
	{
		return sum_upper+sum_lower;
	}
	
	public boolean allDone()
	{
		for (int i = 0; i < 13; i++)
		{
			if (overview[i] == -1)
				return false;
		}
		return true;
	}
	
	private void calculateTotal()
	{
		for (int i = 0; i < 6; i++)
		{
			sum_upper += (overview[i] == -1 ? 0 : overview[i]);
		}
		
		if (sum_upper > 62)
			sum_upper += 35;
		
		for (int i = 6; i < 13; i++)
		{
			sum_lower += (overview[i] == -1 ? 0 : overview[i]);
		}
	}
	
	public void countEyes()
	{
		for (int i = 0; i < 5; i++)
		{
			counter[(dice[i].getValue()-1)]++;
		}
	}
	
	public boolean setScore(int choice)
	{
		if (overview[choice-1] != -1 && (choice != 13)) 
		{
			System.out.println("Dit vakje is al reeds ingevuld. Om het scorebord te bekijken, typ !overview");
			return false;
		}
		else
			overview[choice-1] = getScore(choice);
		return true;
	}
	
	public void resetCounter()
	{
		Arrays.fill(counter, 0);
	}
	
	public int getScore(int choice)
	{
		countEyes();
		int score = 0;
		int index = -1;

		switch (choice)
		{
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				score = choice*counter[choice-1];
				break;
			case 7:
				index = Arrays.asList(counter).indexOf(3);
				if (index > -1)
					score = sumDice();
				break;
			case 8: 
				index = Arrays.asList(counter).indexOf(4);
				if (index > -1)
					score = sumDice();
				break;
			case 9:
				if (isStraight(4))
					score = 30;
				break;
			case 10:
				if (isStraight(5))
					score = 40;
				break;
			case 11:
				if ((Arrays.asList(counter).indexOf(3) > -1 && Arrays.asList(counter).indexOf(2) > -1) || (Arrays.asList(counter).indexOf(5) > -1 && overview[13] != -1))
					score = 25;
				break;
			case 12:
				score = sumDice();
				break;
			case 13:
				if (Arrays.asList(counter).indexOf(5) > -1) //Yahtzee!
				{
					if (overview[12] > 0) //Already had yahtzee
						score = overview[12]+100;
					else
						score = 50;
				}
				break;
		}
		resetCounter();
		return score;
	}
	
	private boolean isStraight(int straightSize)
	{
		int in_a_row = 0;
		for (int c: counter)
		{
			if (c >= 1)
				in_a_row++;
			else
				in_a_row = 0;
		}
		return (in_a_row == straightSize);
	}
	
	private int sumDice()
	{
		int sum = 0;
		for (Die d: dice)
		{
			sum += d.getValue();
		}
		return sum;
	}
	
}

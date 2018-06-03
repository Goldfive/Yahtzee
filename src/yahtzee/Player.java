package yahtzee;

public class Player {
	private Score score;
	private Die[] dice = new Die[5];
	public String name;
	public int playerId;
	private int turn = 0;
	public boolean setScore = false;
	
	Player(String name, int pId)
	{
		this.name = name;
		this.playerId = pId;
		
		for (int i = 0; i < 5; i++)
		{
			dice[i] = new Die();
		}
		score = new Score(dice);
	}
	
	public Score getScore()
	{
		return score;
	}
	
	public void printDice()
	{
		System.out.print(name+" heeft gegooid: ");
		for (Die d: dice)
			System.out.print((d.getLocked() ? "(" : "")+d.getValue()+(d.getLocked() ? ")" : "")+" ");
		System.out.print("\r\n");
	}
	
	public int getTurn()
	{
		return turn;
	}
	
	public void resetDice()
	{
		for (Die d: dice) //Unlock all dice for next turn
		{
			d.reset();
		}
	}
	
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	
	public void throwDice()
	{
		if (turn == 0)
			setScore = false;
		if (turn >= 2)
			System.out.println("Je hebt al 3x gegooid. Vul een score in op het scoreblaadje.");
		else
		{
			score.resetCounter();
			for (Die d: dice)
				if (!d.getLocked()) //If die is not locked
					d.rollDie();
			printDice();
			nextTurn();
		}
	}
	
	public void lock(String[] t)
	{
		for (int i = 1; i < t.length; i++)
		{
			if (Integer.parseInt(t[i]) > 5)
				t[i] = "5";
			dice[Integer.parseInt(t[i])-1].changeDieState();
		}
		for (Die d: dice)
		{
			System.out.print((d.getLocked() ? "(" : "")+d.getValue()+(d.getLocked() ? ")" : "")+" ");
		}
		System.out.print("\n\r");
	}

	public void resetTurn()
	{
		this.turn = 0;
	}
	
	public int nextTurn() {;	
		return turn++;
	}
}

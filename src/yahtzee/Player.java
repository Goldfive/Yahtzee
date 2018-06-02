package yahtzee;

public class Player {
	private Score score;
	private Die[] dice = new Die[5];
	public String name;
	private int playerId;
	private int turn = 0;
	
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
	
	public void throwDice()
	{
		score.resetCounter();
		System.out.print(name+" heeft gegooid: ");
		for (Die d: dice)
		{
			if (!d.getLocked()) //If die is not locked
				d.rollDie();
			System.out.print((d.getLocked() ? "(" : "")+d.getValue()+(d.getLocked() ? ")" : "")+" ");
		}
		System.out.print("\r\n");
	}
	
	public void lock(String[] t)
	{
		for (int i = 1; i < t.length; i++)
		{
			dice[Integer.parseInt(t[i])-1].changeDieState();
		}
	}

	public int nextTurn() {
		this.turn++;
		if (turn >= 3)
		{
			System.out.println("Jouw beurt is nu over. Kies een score optie.");
			turn = 0;
			for (Die d: dice) //Unlock all dice for next turn
			{
				d.reset();
			}
		}
		
		return turn;
	}
}

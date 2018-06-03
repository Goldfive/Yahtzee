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
	
	public int nextTurn() {
		this.turn++;
		if (turn >= 3)
		{
			while (!setScore)
			{
				System.out.println("Je moet je score nog in een vakje zetten. Typ 1 t/m 13 om je score in het daardoor bestemde vakje te plaatsen.");
				String subs = Game.readInput();
				if (!Game.isInteger(subs))
					System.out.println("Stout! Dat is geen getal!");
				else
					setScore = score.setScore(Integer.parseInt(subs));
			}
			if (score.allDone())
				System.out.println("Alle vakjes zijn ingevuld - je bent klaar met spelen! Je kunt nog steeds je scoreformulier opvragen; als je een nieuw spel wilt starten, typ !start");
			turn = 0;
			for (Die d: dice) //Unlock all dice for next turn
			{
				d.reset();
			}
		}
		
		return turn;
	}
}

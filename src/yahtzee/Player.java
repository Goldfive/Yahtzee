package yahtzee;

public class Player {
	private Score scoreList;
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
	}
	
	public void throwDice()
	{
		for (Die d: dice)
	}

	public int nextTurn() {
		this.turn++;
		if (turn >= 3)
			turn = 0;
		
		return turn;
	}
}

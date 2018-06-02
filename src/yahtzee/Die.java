package yahtzee;

import java.util.Random;


public class Die {
	private Random gen = new Random();
	int value = 1;
	private boolean locked = false;
	
	public void rollDie()
	{
		this.value = gen.nextInt(6)+1;
	}
	
	public boolean getLocked()
	{
		return this.locked;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void changeDieState() //Locked goes to unlocked, unlocked to locked
	{
		this.locked = !this.locked;
	}
	
	public void reset()
	{
		this.locked = false;
	}
}

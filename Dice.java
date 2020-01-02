//Abigail Schafer 40094234 
//2018-11-28, Comp 248
// class allowing us to manipulate a virtual pair of dice in the game

import java.util.Random;

public class Dice {

// Instance variable 
	public int v1;
	public int v2;
	private Random rand = new Random();

//Default Constructor
	public Dice() 
	{
		v1 = 0;
		v2 = 0;
	}
//Accessor Methods
	public int getV1() 
	{
		return v1;
	}
	
	public int getV2() 
	{
		return v2;
	}
	
//rollDice()
	public int rollDice()
	{
		this.v1 = rand.nextInt(6)+1;
		this.v2 = rand.nextInt(6)+1;
		return (v1 + v2);  
	}
	
//String Method
	public String toString() {
		return ("you rolled a " + (v1 + v2) +"(Die 1: " + v1 + " Die 2: " + v2 +")");
	}
	
}

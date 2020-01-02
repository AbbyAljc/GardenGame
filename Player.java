//Abigail Schafer 40094234 
//2018-11-28, Comp 248
// creating methods to allow the letsplay class to use the garden methods in accordance with a player

public class Player {

//instance variables
	private String PlayerName;
	private Garden PlayerGarden = new Garden();

//constructor
	public Player(String name, int size) {
		PlayerName = name;
		PlayerGarden = new Garden(size);
	}
//accesor for the name
	
	public String getName() {
		return PlayerName;
	}
	
// methods
	
	public int howManyFlowersPossible() {
		return PlayerGarden.countPossibleFlowers();
	}
	
	public int howManyTreesPossible() {
		return PlayerGarden.countPossibleTrees();
	}
	
	public char whatIsPlanted(int r, int c){
		return PlayerGarden.getInLocation(r, c);
	}
	
	public void plantTreeInGarden(int r, int c){
		PlayerGarden.plantTree(r, c);
	}
	
	public void plantFlowerInGarden(int r, int c) {
		PlayerGarden.plantFlower(r, c);
	}
	
	public void eatHere (int r, int c) {
		PlayerGarden.removeFlower(r, c);
	}
	
	public boolean isGardenFull() {
		return PlayerGarden.gardenFull();
	}
	
	public Garden showGarden () {
		return PlayerGarden; 
	}
}


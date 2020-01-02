//Abigail Schafer 40094234 
//2018-11-28, Comp 248
//Garden class, creating the methods to allow the player to manipulate the garden

//import Scanner
import java.util.Scanner; 

	public class Garden {
		
//declare Scanner
		public static Scanner kb = new Scanner(System.in);
//array and instance variable
		private char[][] garden;
		public int size;
//a way to collect size
		public void size (int userInput){
			if (userInput > 2)
				this.size = userInput;
		}
//constructor for default garden
		public Garden() {
			garden = new char [3][3];
			initializeGarden(3);
		}
//constructor for customizable garden
		public Garden(int size) {
			garden = new char [size][size]; 
			initializeGarden(size);
		}
//initialize garden method
		public char[][] initializeGarden(int dim) {
			for (int i=0;i<dim;i++) {
				for (int j=0;j<dim;j++) {
					garden[i][j] = '-';
				}
			}
			return garden; 
		}
//getInlocation method
		public char getInLocation(int r, int c){
			return garden[r][c];
		}
//plantFlower method
		public char[][] plantFlower (int r, int c) {
			garden[r][c] = 'f';
			return garden;
		}
//plantTree method
		public char[][] plantTree (int r, int c) {
			garden[r][c] = 't';
			garden [r+1][c] = 't';
			garden [r][c+1] = 't';
			garden [r+1][c+1] = 't';
			
			return garden;
		}
//removeFlower
		public char[][] removeFlower (int r, int c){
			garden [r][c] = '-';
			return garden;
		}
//countPossibleTrees
		public int countPossibleTrees() {
			int possibleTrees = 0;
			for (int i=0; i < (garden.length-1);i++) {
				for (int j=0; j < (garden[i].length-1); j++) {
				if(garden[i][j] == '-' & garden[i+1][j] == '-' & garden[i][j+1] == '-' & garden[i+1][j+1] == '-') {
						possibleTrees++;
					}
				}
			}
			return possibleTrees; 
		}
//countPossibelFlowers
		public int countPossibleFlowers() {
			int possibleFlowers = 0;
			for (int i=0; i < garden.length;i++) {
				for (int j=0; j < garden[i].length; j++) {
					if(garden[i][j] == '-')
						possibleFlowers ++;
							}
			}
			return possibleFlowers;
		}
//check if garden is full
		public boolean gardenFull() {
			int l = 0;
			for (int i=0; i < garden.length;i++) {
				for (int j=0; j < garden[i].length; j++) {
					if (garden[i][j] == '-')
						l++;}}
			if (l != 0)
				return false;
			else
				return true;
					}
// to String return garden content
		public String toString() {
	
			String gardenSquare = "";
			
			for (int i=0; i < garden.length;i++) {
				for (int j=0; j < garden[i].length; j++) {
					gardenSquare += garden[i][j] + "  ";
				}
				gardenSquare+="\n";
			}
			return gardenSquare;
		}
		
		
	}



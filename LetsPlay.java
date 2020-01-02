//Abigail Schafer 40094234 
//2018-11-28, Comp 248
//This is the main class, which runs through the steps of the game from collecting names and numbers, to playing through and 
//repeating until a garden is filled. I chose to put a lot of the checking and accounting for acceptions in this class, although
//it could have also been done in the player class.


import java.util.Scanner;
import java.util.Random;

public class LetsPlay {
	
	public static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {

// Welcome banner + instructions
		System.out.println("-------****-------****-------****-------****-----****-----\n     Welcome to Crazy Nancy's Garden Game!  \n-------****-------****-------****-------****-----****----- \n");
		System.out.println("To win this game you need some luck with the dice and a bit of strategy. Your garden is an NxN lot. \nYou can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2). \nYou roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers. \r\n\n" + "Rolls and their outcome:\n ---------------------   \n3: plant a tree (2x2) and a flower (1x1)   \n6: plant 2 flowers (2 times 1x1)   \n12: plant 2 trees (2 times 2x2)  \n5 or 10: the rabbit will eat something that you have planted - might be a flower or part of a tree(1x1)   \nAny other EVEN rolls: plant a tree (2x2)   \nAny other ODD rolls: plant a flower (1x1) \r\n" + 
				" \r\n" + 
				"Minimum number of players: 2. \nMinimum garden size: 3x3. \nYou can only plant in empty locations. \nTo plant a tree you give the top left coordinates of the 2x2 space and I will check to make sure all 4 locations are free. \nOkay .. Let's start the game! May the best gardener win!!! \r\n" + 
				" \r\n" + 
				" \r\n" + 
				"The default garden size is a 3x3 square. \nYou can use this default board size or change the size Enter 0 to use the default garden size or -1 to enter your own size:" );

// declaring variables for future use
		int size = 0;
		Player[] players = null;
		Dice d = new Dice();
		
		int j = 0;
// checking for size of player gardens
		do {
		int userInput = kb.nextInt();
		
		if(userInput == 0) {
			size = 3;
			j++;
		}
		else if (userInput == -1) {
			do {
			System.out.println("What size?");
			int input = kb.nextInt();
			if (input >= 3) {
				size = input;
				j++;
			}
			else
				System.out.println("That's not a valid size.");
			j++;
			}
			while (size==0);
		}
		else 
			System.out.println("That wasn't one of the options.");} while (j==0);

//enter number of players from 2 to 10, and intialize array
		int i =  0;
		
		do {
	System.out.println("How many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
		
		int numOfPlayers = kb.nextInt();
		
		if (numOfPlayers >= 2 & numOfPlayers <= 10) {
			players = new Player[numOfPlayers];
			System.out.println("Great! " + numOfPlayers + " players it is!!");
			i++;
		}
		else 
		System.out.println("Not a valid number of players.");
		}
		while (i == 0);
	
		for (int k = 0; k<players.length; k++) {
	System.out.println("Name of player " +(k+1)+ " (no spaces): ");
		
	players[k] = new Player(kb.next(), size);
		}
	
//rolling dice
		int [] rolls = new int[players.length];
		System.out.println("Let's see who goes first...\n"); 		
		
		int u;
		
		do {
		u = 0;
			
		for (int f = 0; f<players.length;f++) {
		rolls[f] = d.rollDice(); 
		System.out.println(players[f].getName() + " rolled a " + rolls[f] + "!!");}
		
		for (int q = 0; q<rolls.length; q++) {
			for (int w = q+1; w<rolls.length; w++) {
				if (rolls[q] == rolls[w]) {
					u++;}
				}
			}
		if (u != 0)
			System.out.println("\nOh no! Two of the same number! Let's roll again.\n");
		} while(u != 0);
				
			
			
			
			
			
// find max value
		int max = rolls[0];
		int g = 0;
		
		for(int m=1; m<rolls.length;m++) {
			if(rolls[m] > max) {
				max = rolls[m];
				g = m;}
		}
		
		System.out.println(players[g].getName() + " goes first. \n\n Time to play!!!!!\n-----------------\n");
	
// Roll dice and print result
		int t = 0; 

//	Show player their options. First do while loop to make the simulation end when a garden is full. Create an outter loop to break
// out of whenever garden is filled
		OUTER_LOOP: 
		do {
		for(t=g; t<players.length;t++) {
			Dice move = new Dice();
			int variable = move.rollDice();
			System.out.println("\n\n" + players[t].getName() + " " + move.toString() );

//these are the different options of moves a player can make 
		if (variable == 3) {
			
			System.out.println("Plant a tree (2x2) and a flower (1x1)");
			
			System.out.println("Let's starts with the tree. You have " + players[t].howManyTreesPossible() + " places to do this.\n\n" + players[t].showGarden());

			//for the tree
			
			if(players[t].howManyTreesPossible() >= 1) {
			
				System.out.println("\nEnter the coordinates of where you want to plant a tree.");
			
				int r = 0;
				
				do {
					int c1 = kb.nextInt();
					int c2 = kb.nextInt();
					
					if (c1 >= size || c2 >= size) 
						System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
					else if (c1 == size-1 || c2 == size-1 || players[t].whatIsPlanted(c1, c2) != '-' | players[t].whatIsPlanted(c1+1, c2) != '-' | players[t].whatIsPlanted(c1, c2+1) != '-' | players[t].whatIsPlanted(c1+1, c2+1) != '-') 
						System.out.println("\nThere is no room to plant a tree there. Try again.");
					else if (players[t].whatIsPlanted(c1, c2) == '-' & players[t].whatIsPlanted(c1+1, c2) == '-'& players[t].whatIsPlanted(c1, c2+1) == '-'& players[t].whatIsPlanted(c1+1, c2+1) == '-') {
			players[t].plantTreeInGarden(c1, c2);
			r++;
			
			if (players[t].isGardenFull() == true)
				break OUTER_LOOP;
				}
		
			} while (r ==0);
			
		}
			else System.out.println("\nSorry, no room to plant a tree.");

			//for the flower
			
			System.out.println("\nNow for the flower. You have " + players[t].howManyFlowersPossible() + " places to do this.\n\n" + players[t].showGarden());
			
			if(players[t].howManyFlowersPossible() >= 1) {
			
				System.out.println("\nEnter the coordinates of where you want to plant a flower.");
			
				int r = 0;
				
				do {
					int c1 = kb.nextInt();
					int c2 = kb.nextInt();
					
					if (c1 >= size || c2 >= size) 
						System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
					else if (players[t].whatIsPlanted(c1, c2) == '-') {
			players[t].plantFlowerInGarden(c1, c2);
			r++;
				}
				else if (players[t].whatIsPlanted(c1, c2) != '-') 
					System.out.println("\nThere is already something planted there. Try again.");
		
			} while (r ==0);
			
				if (players[t].isGardenFull() == true)
					break OUTER_LOOP;
		}
		}
		
// if someone rolls a 6
			
		
		else if (variable == 6) {
			
			System.out.println("\nPlant 2 flowers (2 times 1x1)");
			
			System.out.println("\nLet's starts with the first flower. You have " + players[t].howManyFlowersPossible() + " places to do this.\n\n" + players[t].showGarden());
			
			System.out.println("\nEnter the coordinates of where you want to plant the first flower.");
			
			int r = 0;
			
			do {
				int c1 = kb.nextInt();
				int c2 = kb.nextInt();
				
				if (c1 >= size || c2 >= size) 
					System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
			else if (players[t].whatIsPlanted(c1, c2) == '-') {
		players[t].plantFlowerInGarden(c1, c2);
		r++;
			}
			
			else if (players[t].whatIsPlanted(c1, c2) != '-') 
				System.out.println("\nThere is already something planted there. Try again.");
	
		} while (r == 0 );
			
			if (players[t].isGardenFull() == true)
				break OUTER_LOOP;
		
			
			// second flower 
				
	System.out.println("\nNow the second flower. You have " + players[t].howManyFlowersPossible() + " places to do this.\n\n" + players[t].showGarden());
			
			System.out.println("\nEnter the coordinates of where you want to plant the second flower.");
			
			do {
				r=0;
				int c1 = kb.nextInt();
				int c2 = kb.nextInt();
			
				 if (c1 >= size || c2 >= size) 
						System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + (size-1) +". Please try again.");
				
			else if (players[t].whatIsPlanted(c1, c2) == '-') {
		players[t].plantFlowerInGarden(c1, c2);
		r++;
			}
			
			else if (players[t].whatIsPlanted(c1, c2) != '-') 
				System.out.println("\nThere is already something planted there. Try again.");
	
		} while (r == 0 );
			
			if (players[t].isGardenFull() == true)
				break OUTER_LOOP;
		}
			
		
//if they roll a 12
		
		else if (variable == 12) {
			System.out.println("\nPlant 2 trees (2 times 2x2)");
			
			System.out.println("\nYou have " + players[t].howManyTreesPossible() + " places to plant the first tree.\n\n" + players[t].showGarden());
			
			if(players[t].howManyTreesPossible() >= 1) {
			
				System.out.println("\nEnter the coordinates of where you want to plant the first tree.");
			
				int r = 0;
				
				do {
					int c1 = kb.nextInt();
					int c2 = kb.nextInt();
					
					if (c1 >= size || c2 >= size) 
						System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
					else if (c1 == size-1 || c2 == size-1 || players[t].whatIsPlanted(c1, c2) != '-' | players[t].whatIsPlanted(c1+1, c2) != '-' | players[t].whatIsPlanted(c1, c2+1) != '-' | players[t].whatIsPlanted(c1+1, c2+1) != '-') 
						System.out.println("\nThere is no room to plant a tree there. Try again.");
					else if (players[t].whatIsPlanted(c1, c2) == '-' & players[t].whatIsPlanted(c1+1, c2) == '-'& players[t].whatIsPlanted(c1, c2+1) == '-'& players[t].whatIsPlanted(c1+1, c2+1) == '-') {
			players[t].plantTreeInGarden(c1, c2);
			r++;
				}
				
		
			} while (r ==0);
				
				if (players[t].isGardenFull() == true)
					break OUTER_LOOP;
		}
			else System.out.println("\nSorry, no room to plant a tree.");
			
		//second tree
			System.out.println("\nYou have " + players[t].howManyTreesPossible() + " places to plant the second tree.\n\n" + players[t].showGarden());
			
			if(players[t].howManyTreesPossible() >= 1) {
			
				System.out.println("\nEnter the coordinates of where you want to plant this tree.");
			
				int r = 0;
				
				do {
					int c1 = kb.nextInt();
					int c2 = kb.nextInt();
					
					if (c1 >= size || c2 >= size) 
						System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
					else if (c1 == size-1 || c2 == size-1 || players[t].whatIsPlanted(c1, c2) != '-' | players[t].whatIsPlanted(c1+1, c2) != '-' | players[t].whatIsPlanted(c1, c2+1) != '-' | players[t].whatIsPlanted(c1+1, c2+1) != '-') 
						System.out.println("\nThere is no room to plant a tree there. Try again.");
					else if (players[t].whatIsPlanted(c1, c2) == '-' & players[t].whatIsPlanted(c1+1, c2) == '-'& players[t].whatIsPlanted(c1, c2+1) == '-'& players[t].whatIsPlanted(c1+1, c2+1) == '-') {
			players[t].plantTreeInGarden(c1, c2);
			r++;
				}
		
			} while (r ==0);
				
				if (players[t].isGardenFull() == true)
					break OUTER_LOOP;
		}
			else System.out.println("\nSorry, no room to plant a tree.");
		}
//if they roll a 5 or 10	
		
		else if (variable == 5 || variable == 10) {
				int m = 0;
				Random rand = new Random();
				int isEmpty = 0;
				
				for (int n =0; n<size; n++) {
					for (int z = 0; z<size; z++) {
						if (players[t].whatIsPlanted(n, z) != '-') 
							isEmpty++;
					}
				}
				
				if (isEmpty != 0) {
					System.out.println("\nThe rabbit will eat something that you have planted - might be a flower or a part of a tree(1x1)\n");
					
				do {
				int location1 = rand.nextInt(size-1);
				int location2 = rand.nextInt(size-1);
				
				if (players[t].whatIsPlanted(location1, location2) != '-') {
					players[t].eatHere(location1, location2);
					m++;}
				
			} while (m==0);
		
			System.out.println(players[t].showGarden());
				}
				else 
					System.out.println("There was nothing planted for the rabbit to eat!");
		}

// for any other even number
		
		else if (variable % 2 == 0) {
			System.out.println("\nPlant a tree (2x2)");
			
System.out.println("You have " + players[t].howManyTreesPossible() + " places to plant a tree.\n\n" + players[t].showGarden());
			
			if(players[t].howManyTreesPossible() >= 1) {
			
				System.out.println("\nEnter the coordinates of where you want to plant the tree.");
			
				int r = 0;
				
				do {
					int c1 = kb.nextInt();
					int c2 = kb.nextInt();
					
					if (c1 >= size || c2 >= size) 
						System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
					else if (c1 == size-1 || c2 == size-1 || players[t].whatIsPlanted(c1, c2) != '-' | players[t].whatIsPlanted(c1+1, c2) != '-' | players[t].whatIsPlanted(c1, c2+1) != '-' | players[t].whatIsPlanted(c1+1, c2+1) != '-') 
						System.out.println("\nThere is no room to plant a tree there. Try again.");
					else if (players[t].whatIsPlanted(c1, c2) == '-' & players[t].whatIsPlanted(c1+1, c2) == '-'& players[t].whatIsPlanted(c1, c2+1) == '-'& players[t].whatIsPlanted(c1+1, c2+1) == '-') {
			players[t].plantTreeInGarden(c1, c2);
			r++;
				}
		
			} while (r ==0);
				
				if (players[t].isGardenFull() == true)
					break OUTER_LOOP;
			
		}
			else System.out.println("\nSorry, no room to plant a tree.");
			
		}
		
// for any other odd number
		
		else if (variable % 2 == 1) {
			System.out.println("\nPlant a flower (1x1)");
			
System.out.println("\nYou have " + players[t].howManyFlowersPossible() + " places to plant a flower.\n\n" + players[t].showGarden());
			
			System.out.println("\nEnter the coordinates of where you want to plant the flower.");
			
			int r = 0;
			
			do {
				int c1 = kb.nextInt();
				int c2 = kb.nextInt();
				
				if (c1 >= size || c2 >= size) 
					System.out.println("\nEither one or both of your coordinates are not in the range of 0 to " + size +". Please try again.");
				else if (players[t].whatIsPlanted(c1, c2) == '-') {
		players[t].plantFlowerInGarden(c1, c2);
		r++;
			}
			
				else if (players[t].whatIsPlanted(c1, c2) != '-') 
				System.out.println("\nThere is already something planted there. Try again.");
	
				} while (r == 0 );
			
			if (players[t].isGardenFull() == true)
				break OUTER_LOOP;
			}
		
		if (players[t].isGardenFull() == false & t == (players.length -1 ) )
			t = -1; 
		
	
		}
		}while (players[t-1].isGardenFull() == false);
		
// print winner's garden full and congratulations note 
		
		System.out.println(players[t].showGarden());
		System.out.println("Congradulations! " + players[t].getName() + ", you win! \n\n What a beautiful garden! Hope you enjoyed planting it! :)");
		
		kb.close();
	}
}



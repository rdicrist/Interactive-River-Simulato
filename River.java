/*  Made by: Erica DiCristoforo
 *  Purpose of class: The class creates and modifies a simulated river ecosystem. It can create a 
 *  random eco system when given a length, or can read a file of desired slot allotment.  
 */


import java.io.IOException;
import java.util.Random;

public class River {
	//variables
	private int length;
	public Animal [ ] river;
	
	//random river, based off of given length, fills cells with null/bear/fish depending on the random generated
	public River (int l) {
		length = l;
		river = new Animal[length];
		for (int i = 0; i < river.length; i++) {
			 Random ran = new Random(); 
			 int x = ran.nextInt(3);
			 if (x == 0) {
				 river[i] = null;
			 }
			 else if (x == 1) {
				 river[i] = new Bear();
			 }
			 else if (x == 2) {
				 river[i] = new Fish();
			 }
		}
	}
	//creates river from file 
	public River (String text) throws IOException {
		//creates new ReadToFile
		ReadToFile file = new ReadToFile(text);
		//gets the length (and length of river is set)
		length = file.getLength();
		
		//river created of length of file 
		river = new Animal[length];
		for (int i = 0; i < length; i++) {
			//getValue used on each index, and split to determine what the animal should be 
			String f = file.getValue(i);

			if (f.substring(0, 1).equals("B")) {

				int a = Integer.parseInt(f.substring(2));
				
				String gen = null;
				if (f.substring(1, 2).equals("F")) {
						gen = "FEMALE";
					 }
				else if (f.substring(1, 2).equals("M")) {
						gen = "MALE";
					 }
				
				river[i] = new Bear(a, gen);

			}
			else if (f.substring(0, 1).equals("F")) {
				int a = Integer.parseInt(f.substring(2));
				
				String gen = null;
				if (f.substring(1, 2).equals("F")) {
						gen = "FEMALE";
					 }
				else if (f.substring(1, 2).equals("M")) {
						gen = "MALE";
					 }
				
				river[i] = new Fish(a, gen);
			}
			else {
				river[i] = null;
			}
		}
	}
	//getter for length of river
	public int getLength() {
		return length;
	}
	//getter for number of null cells
	public int numEmpty () {
		int nullCells = 0;
		for (int i = 0; i < river.length; i++) {
			if (river[i] == null) {
				nullCells += 1;
			}
		}
		return nullCells;
	}
	//getter for number of bear cells
	public int numBears () {
		int bearCells = 0;
		for (int i = 0; i < river.length; i++) {
			if (river[i] instanceof Bear) {
				bearCells += 1;
			}
		}
		return bearCells;
	}
	//getter for number fo fish cells
	public int numFish () {
		int fishCells = 0;
		for (int i = 0; i < river.length; i++) {
			if (river[i] instanceof Fish) {
				fishCells += 1;
			}
		}
		return fishCells;
	}
	//finds the next null value in the river 
	public int findNextNull (int index) {
		for (int i = index; i < river.length;i++) {
			if (river[i] == null) {
				return i;
			}
		}
		return -1;
	}
	//adds a new animal of same type given, and sets age to zero
	public boolean addRandom (Animal a, Animal[] l) {
		//if no cells, does nothing returns false
		if (this.numEmpty() == 0) {
			return false;
		}
		//if there is at least one empty cell
		else {
			//finds a random null cell
			int x = this.numEmpty();
		
			Random ran = new Random(); 
			int y = ran.nextInt(x);
		
			int k = this.findNextNull(0);
			
			if (y !=0) {
				for (int i = 1; i < y; i++) {
				 	k = this.findNextNull(k);
				} 
			}
			//creates random bear if given is bear, sets age to 0, places in decided cell
			if (a instanceof Bear) {
				Bear b = new Bear ();
				b.setAge(0);
				l[k] = b;
			}
			//creates random fish if given is fish, sets age to 0, places in decided cell
			else if (a instanceof Fish) {
				Fish f = new Fish ();				
				f.setAge(0);
				l[k] = f;
			}

		return true;
		}
	}
	
	
	public void updateCell (int i, Animal[] tempRiver) {
	
		
		//if cell does not contain an animal 
		if (river[i] == null) {	
			
		}
		
		//if the animal is a fish
		else if (river[i] instanceof Fish) {
			//temp fish created
			Fish f = (Fish) river[i];
			//random choice for action based off of 0/1/2
			Random ran = new Random(); 
			int x = ran.nextInt(3);
			//if 0, does nothing, placed into temp river 
			if (x == 0) {
				tempRiver[i] = f;
				river[i] = null; 
	
			}
			//if 1, moves to the left (depending on what direction, either tempRiver or River is checked for occupants)
			else if (x == 1) {
				
				//if it is the first cell of the river (moves to end of river)
				if (i == 0) {
				
					//if a fish is occupying that cell
					if (river[length-1] instanceof Fish) {
						if (river[length-1].getGen().equals(f.getGen())){
							//the moving fish will kill the current one if same gender
							river[length-1] = null;
							//placed into cell
							tempRiver[length-1] = f;
							river[i] = null;
						
						}
						else {
							//a new fish is born, and f stays in the same spot
							addRandom(f, tempRiver);
							//placed into cell
							tempRiver[i] = f;
							river[i] = null;
				
						}
						
					}
					//if a bear is occupying that cell
					else if (river[length-1] instanceof Bear) {
						//the bear will kill the fish
						tempRiver[i] = null;
						river[i]= null;
			
					}
					//if cell is null
					else if (river[length-1] == null) {
						//moved into that cell in temp
						tempRiver[length-1] = f;
						river[i] = null; 

					}
				}
				
				//if it is located anywhere besides the first cell
				else {
			
					//if a fish is occupying that cell
					if (tempRiver[i-1] instanceof Fish) {
						if (tempRiver[i-1].getGen().equals(f.getGen())){
							//the moving fish will kill the current one if same gender
							tempRiver[i-1] = f;
							river[i]= null;
				
						}
						else {
							//a new fish is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null;
							
						}
						
					}
					//if a bear is occupying that cell
					else if (tempRiver[i-1] instanceof Bear) {
						//the bear will kill the fish
						tempRiver[i] = null;
						river[i] = null;
						
					}
					//if the cell is null, placed into that cell in temp
					else if (tempRiver[i-1] == null) {
						tempRiver[i-1] = f;
						river[i] = null; 
					}
				}
			}
			//if 2, moves to the right 
			else if (x == 2) {

				//if it is the last cell of the river (moves to beginning of river)
				if (i == length-1) {
		
					//if a fish is occupying that cell
					if (tempRiver[0] instanceof Fish) {
						if (tempRiver[0].getGen().equals(f.getGen())){
							//the moving fish will kill the current one if same gender
							tempRiver[0] = f;
							river[i] = null;
						
						}
						else {
							//a new fish is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null; 
							
						}
						
					}
					//if a bear is occupying that cell
					else if (tempRiver[0] instanceof Bear) {
						//the bear will kill the fish
						tempRiver[i] = null; 
						river[i] = null;
					
					}
					//if cell is null, placed into that cell in temp
					else if (tempRiver[0] == null) {
						tempRiver[0] = f;
						river[i] = null; 
				
					}
					
					
				}
				
				//if it is located anywhere besides the last cell
				else {
		
					//if a fish is occupying that cell
					if (river[i+1] instanceof Fish) {
						if (river[i+1].getGen().equals(f.getGen())){
							//the moving fish will kill the current one if same gender
							river[i+1] = null;
							tempRiver[i+1] = f;
							river[i] = null; 
					
						}
						else {
							//a new fish is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null; 
					
						}
						
					}
					//if a bear is occupying that cell
					else if (river[i+1] instanceof Bear) {
						//the bear will kill the fish
						tempRiver[i] = null;
						river[i] = null;
				
					}
					//if cell is null, placed into that cell in temp
					else if (river[i+1] == null) {
						tempRiver[i+1] = f;
						river[i] = null; 
				
					}
				}
			}
		      
		}
		//if it is a bear
		else if (river[i] instanceof Bear) {
			Bear f = (Bear) river[i];
			Random ran = new Random(); 
			int x = ran.nextInt(3);
			//if 0, does nothing
			if (x == 0) {
				tempRiver[i] = f;
				river[i] = null;
			}
			//if 1, moves to the left
			else if (x == 1) {
				//if it is the first cell of the river (moves to end of river)
				if (i == 0) {
					
					//if a bear is occupying that cell
					if (river[length-1] instanceof Bear) {
						Bear b = (Bear) river[length-1];
						if (b.getGen().equals(f.getGen())){
							//the bears will fight if same gender
							//the miving bear kills the other one 
							if (f.getStrength() >= b.getStrength()) {
								river[length-1] = null;
								tempRiver[length-1] = f;
								river[i] = null; 
							}
							else {
								//the bear that is moving will die
								tempRiver[i] = null;
								river[i] = null;
							}
						}
						else {
							//a new bear is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null; 
						}
						
					}
					//if a fish is occupying that cell
					else if (river[length-1] instanceof Fish) {
						//the bear will kill the fish
						river[length-1] = null;
						tempRiver[length-1] = f;
						river[i] = null; 
					}
					//if the cell is null, plcaed into that cell in temp
					else if (river[length-1] == null) {
						tempRiver[length-1] = f;
						river[i] = null; 
					}
				}
				
				//if it is located anywhere besides the first cell
				else {
					//if a bear is occupying that cell
					if (tempRiver[i-1] instanceof Bear) {
						Bear b = (Bear) tempRiver[i-1];
						if (b.getGen().equals(f.getGen())){
							//the bears will fight if same gender
							if (f.getStrength() >= b.getStrength()) {
								
								tempRiver[i-1] = f;
								river[i] = null;
							}
							else {
								//the bear that is moving will die
								tempRiver[i] = null; 
								river[i] = null;
							}
						}
						else {
							//a new bear is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null; 
						}
						
					}
					//if a fish is occupying that cell
					else if (tempRiver[i-1] instanceof Fish) {
						//the bear will kill the fish
						
						tempRiver[i-1] = f;
						river[i] = null;
					}
					//if cell is null, placed into that cell in temp
					else if (tempRiver[i-1] == null) {
						tempRiver[i-1] = f;
						river[i] = null; 
					}
					
				}
			}
			//if 2, moves to the right 
			else if (x == 2) {
				//if it is the last cell of the river (moves to beginning of river)
				if (i == length-1) {
					
					//if a fish is occupying that cell
					if (tempRiver[0] instanceof Bear) {
						Bear b = (Bear) tempRiver[0];
						if (b.getGen().equals(f.getGen())){
							//the bears will fight if same gender
							if (f.getStrength() >= b.getStrength()) {
								tempRiver[0] = f;
								river[i] = null;
							}
							else {
								//the bear that is moving will die
								tempRiver[i] = null;
								river[i] = null;
							}
						}
						else {
							//a new bear is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null;
						}
					
					}
					//if a fish is occupying that cell
					else if (tempRiver[0] instanceof Fish) {
						//the bear will kill the fish
						tempRiver[0] = f;
						river[i] = null;
					}
					//if cell is null, moves into that cell in temp
					else if (tempRiver[0] == null) {
						tempRiver[0] = f;
						river[i] = null;
					}
				}
				
				//if it is located anywhere besides the last cell
				else {
					//if a bear is occupying that cell
					if (river[i+1] instanceof Bear) {
						Bear b = (Bear) river[i+1];
						if (b.getGen().equals(f.getGen())){
							//the bears will fight if same gender
							if (f.getStrength() >= b.getStrength()) {
								river[i+1] = null;
								tempRiver[i+1] = f;
								river[i] = null;
							}
							else {
								//the bear that is moving will die
								tempRiver[i] = null;
								river[i] = null;
							}
						}
						else {
							//a new bear is born, and f stays in the same spot
							addRandom(f, tempRiver);
							tempRiver[i] = f;
							river[i] = null;
						}
						
					}
					//if a fish is occupying that cell
					else if (river[i+1] instanceof Fish) {
						//the bear will kill the fish
						river[i+1] = null;
						tempRiver[i+1] = f;
						river[i] = null;
					}
					//if the cell null, placed into that cell in temp
					else if (river[i+1] == null) {
						tempRiver[i+1] = f;
						river[i] = null;
					}
				
				}
			}
		}
	}
	//updates the entire river 
	public void updateRiver() {
		//gets num of bears and fish
		int b = numBears();
		int f = numFish();
		//prints out the "last" ecosystem specifics 
		System.out.println("There were " + b + " bears and " + f + " fish in the river.");
		//temp array made and used to update the cells
		Animal [] temp;
		temp = new Animal [length];
		for (int i = 0; i < length; i++) {
			//for every cell, checks if reached max age and kils it if it has, and incrs age 
			if (river[i] != null) {
				boolean d = river[i].incrAge();
				if (d == false) {
					river[i] = null;
				}
			}
			//if bear, changes the stregenth depending on the age 
			if (river[i] instanceof Bear){
				Bear u = (Bear) river[i];
				u.updateStrength();
				river[i] = u;
			}
			//updates the cells, places them into the temp array
			updateCell (i, temp);
		}
		//copies the temp array back into river
		for (int i = 0; i < length; i++) {
			Animal a = temp[i];
			river[i] = a;
		}
		//checks for new stats on river 
		int b1 = numBears();
		int f1 = numFish();
		//prints out what has happened during the cycle based off of the diferences in bears and fish 
		if (b1 > b) {
			int k = b1 - b;
			System.out.println("There are now " + b1 + " bears in the river. \n" 
					+ k + " bears were born this year.");
		}
		else if (b > b1) {
			int k = b - b1;
			System.out.println("There are now " + b1 + " bears in the river. \n" 
					+ k + " bears died this year.");
		}
		else if (b == b1) {
			System.out.println("There are still " + b1 + " bears in the river. \n "
					+ "No bears were born or died this year.");
		}
		
		if (f1 > f) {
			int k = f1 - f;
			System.out.println("There are now " + f1 + " fish in the river. \n" 
					+ k + " fish were born this year.");
		}
		else if (f > f1) {
			int k = f - f1;
			System.out.println("There are now " + f1 + " fish in the river. \n" 
					+ k + " fish died this year.");
		}
		else if (f == f1) {
			System.out.println("There are still " + f1 + " fish in the river. \n "
					+ "No fish were born or died this year.");
		}
	}
	
	//overrides toString 
	@Override
	public String toString () {
		String s = "";
		for (int i = 0; i < length; i++) {
			s += "Cell " + (i+1) + ": " + river[i] + "\n" ;
		}
		return s;
	}
	//gives type of animals
	public String type(int a) {
		if (river[a] instanceof Bear) {
			return "B";
		}
		if (river[a] instanceof Fish) {
			return "F";
		}
		else {
			return "N";
		}
	}
	//gives the cellInfo
	public String cellInfo(int b) {
		return "" + river[b];
	}
}

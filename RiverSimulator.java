/*  Made by: Erica DiCristoforo
 *  Purpose of class: This class simulates the river.  
 */

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

public class RiverSimulator {


	public static void main(String[] args) throws IOException {
		//class variables 
		Scanner s = new Scanner(System.in);
		int cycles = 19;
		String choice;
		Random ran = new Random(); 
		int y = ran.nextInt(16);
		int length = y;
		String file;
		River r;
		
		//asks user if they would like to personalize or not
		System.out.println("Hello and welcome to River Simulator! Would you like to personalize the river \n"
				+ "ecosystem? (y or n)");
		choice = s.next();
		
		//if they do, asks for the file name and cycles and creates a river based off of that using River(string) method
		if (choice.equals("y")) {
			
			System.out.println("Excellant! Please enter the file name : \n");
			file = s.next();
			r = new River (file);
			length = r.getLength();
			
			System.out.println("And how many cycles would you like to run?");
			cycles = s.nextInt();
		}
		//if they do not, generates a random ecosystem with length (0<x<100
		else { 
			r = new River(length);
		}
		
		//prints the information of the simulated eco system
		System.out.println("\nOkay! Here is your starting ecosystem: \n");
		System.out.println("The river has " + r.getLength() + " spots. \n"
				+ r.numBears() + " of them are bears, " 
				+ r.numFish() + " of them are fish, and "
				+ r.numEmpty() +" of them are empty. \n"
				+ "Let's see what happens!");
		
		//displays GUI of the river using InteractiveRiver class
		InteractiveRiver river = new InteractiveRiver(r.getLength(), "Start River", r); 
		river.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		river.setLocationRelativeTo(null);
		river.pack();
		river.setVisible(true);
		
		//goes through each cycle (19 for random, user value for specialized)
		for (int i = 0; i < cycles + 1; i++) {
			//checks to see how many empty cells there are
			int f = r.numEmpty();
			//if there are no empty, simulation ends (full river)
			if (f == 0) {
				System.out.println("\nThe simulation has ended because the river is full of animals.");
				break;
			}
			//if there are all empty, simulation ends (empty river)
			if (f == (length)) {
				System.out.println("\nThe simulation has ended because the river is completely empty.");
				break;
			}
			//prints out what happens each year (using print statment and updateRiver() built in 
			System.out.println("\nYear " + (i+1) + ":\n");
			r.updateRiver();
			//writes each cycle to file
			WriteToFile filewriter = new WriteToFile(r.toString(), i+1);
			
		
		}
		//when the simulation ends, another GUI is displayed (for the last cycle of the river)
		System.out.println("The simulation is over, thank you for playing!\n"
				+ "Here is your river: \nYour full ecosystem documentation can be found in 'cycle.txt'.");
		InteractiveRiver EndRiver = new InteractiveRiver(r.getLength(), "End River", r); 
		EndRiver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EndRiver.setLocationRelativeTo(null);
		EndRiver.pack();
		EndRiver.setVisible(true);
		
	}

}

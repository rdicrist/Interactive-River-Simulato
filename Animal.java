/*  Made by: Erica DiCristoforo
 *  Purpose of class: This class is the abstract creator of animals, it can create an animal
 *  randomly or by user input.  
 */

import java.util.Random;

public abstract class Animal {
	
	private String gender ;
	private int age ;
	
	//create animal of random age and gender
	public Animal () {
		 Random ran = new Random(); 
		 int x = ran.nextInt(10);
		 age = x;
		 Random rand = new Random();
		 int y = rand.nextInt(2);
		 if (y == 0) {
			gender = "FEMALE";
		 }
		 else if (y == 1) {
			 gender = "MALE";
		 }
	}
	
	//create animal of given age and gender 
	public Animal (int a, String gen) {
		age = a;
		gender = gen;
	}
	//getter for age
	public int getAge () {
		return age;
	}
	//sets age
	public void setAge (int a) {
		age = a;
	}
	//getter for gender
	public String getGen() {
		return gender;
	}
	//override of tostring
	@Override
	public String toString () {
		String gen = gender.substring(0, 1);
		return "A" + gen + this.age;
	}
	//abstract methods 
	public abstract boolean maxAge ();
	public abstract boolean incrAge ();
	public abstract String type();
		

}

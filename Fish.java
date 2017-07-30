/*  Made by: Erica DiCristoforo
 *  Purpose of class: This class extends Animal to create a specific animal, Fish.  
 */

import java.util.Random;

public class Fish extends Animal{
	//variables
	public static int maxAge = 4;
	//random fish
	public Fish () {
		super();
		//if the age is higher than 5 (based on amimal method), changes it to random
		if (this.getAge() >= 5) {
			Random ran = new Random();
			int x = ran.nextInt(5);
			this.setAge(x);
		}
	}
	//pre made fish
	public Fish (int a, String gen) {
		super (a, gen);
	}
	
	//override of toString
	@Override
	public String toString () {
		String gen = this.getGen().substring(0, 1);
		return "F"+gen+this.getAge();
	}
	//tells if it has reached maxage
	public boolean maxAge () {
		if (this.getAge() == maxAge) {
			return true;
		}
		else {
			return false;
		}
	}
	//adds year, if at maxage, returns false
	public boolean incrAge () {
		if (this.getAge() < maxAge) {
			this.setAge(this.getAge() + 1);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//getter "F"
	public String type() {
		return "F";
	}
	



}

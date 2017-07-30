/*  Made by: Erica DiCristoforo
 *  Purpose of class: This class extends Animal to create a specific animal, Bear.  
 */
public class Bear extends Animal{
	//class variables
	public static int maxAge = 9;
	private int strength;
	
	public Bear () {
		//random bear, made by Animal
		super();
		//determines the strength of the bear
		if (this.getAge() < 4) {
			strength = this.getAge() + 1;
		}
		else if (this.getAge() == 4) {
			strength = 4;
		}
		else if (this.getAge() == 5) {
			strength = 4;
		}
		else if (this.getAge() == 6) {
			strength = 3;
		}
		else if (this.getAge() == 7) {
			strength = 2;
		}
		else if (this.getAge() == 8) {
			strength = 1;
		}
		else if (this.getAge() == 9) {
			strength = 0;
		}
	}
	
	public Bear (int a, String gen) {
		//non random bear, made by animal
		super (a, gen);
		//determines strength
		if (this.getAge() < 4) {
			strength = this.getAge() + 1;
		}
		else if (this.getAge() == 4) {
			strength = 4;
		}
		else if (this.getAge() == 5) {
			strength = 4;
		}
		else if (this.getAge() == 6) {
			strength = 3;
		}
		else if (this.getAge() == 7) {
			strength = 2;
		}
		else if (this.getAge() == 8) {
			strength = 1;
		}
		else if (this.getAge() == 9) {
			strength = 0;
		}
	}
	//getter for strength
	public int getStrength () {
		return strength;
	}
	
	//override to toString print B-F/M(gender-age
	@Override
	public String toString () {
		String gen = this.getGen().substring(0, 1);
		return "B"+gen+this.getAge();
	}
	
	//determines if bear is at max age
	public boolean maxAge () {
		if (this.getAge() == maxAge) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//incremtns age, returns false if reached max
	public boolean incrAge () {
		if (this.getAge() < maxAge) {
			this.setAge(this.getAge() + 1);
			return true;
		}
		else {
			return false;
		}
	}
	//updates strength
	public void updateStrength() {
		if (this.getAge() < 4) {
			strength = this.getAge() + 1;
		}
		else if (this.getAge() == 4) {
			strength = 4;
		}
		else if (this.getAge() == 5) {
			strength = 4;
		}
		else if (this.getAge() == 6) {
			strength = 3;
		}
		else if (this.getAge() == 7) {
			strength = 2;
		}
		else if (this.getAge() == 8) {
			strength = 1;
		}
		else if (this.getAge() == 9) {
			strength = 0;
		}
	}
	//returns type "B"
	public String type() {
		return "B";
	}
	

}

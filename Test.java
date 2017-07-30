/*  Made by: Erica DiCristoforo
 *  Purpose of class:  This class tests the methods of the other classes. 
 */
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import static org.junit.Assert.assertThat;
import junit.framework.*;
import junit.*;

public class Test extends TestCase{
	
	
	private Bear b1, b2;
	private Fish f1, f2;
	private River r1, r2, r3;
	private Animal[] a;
	//sets up before, and tears down after 
	protected void setUp() throws IOException {
		b1 = new Bear();
		b2 = new Bear(3, "FEMALE");
		f1 = new Fish();
		f2 = new Fish(4, "MALE");
		r1 = new River(1);
		r2 = new River(15);
		r3 = new River("eco.txt");
	}
	protected void tearDown() {
		b1 = null;
		b2 = null;
		f1 = null;
		f2 = null;
		r1 = null;
		r2 = null;
		r3 = null;
	}
	//testing Animal class(Bear and Fish)
	
	//Test case 1.1: verify that default sets variables correctly
	public void testDefaultInstanceAnimal () {
		assertTrue(0 <= b1.getAge() && b1.getAge() <= 9);
		assertTrue(0 <= f1.getAge() && f1.getAge() <= 5);
		assertTrue(b1.getGen() == "FEMALE" || b1.getGen() == "MALE");
		assertTrue(f1.getGen() == "FEMALE" || f1.getGen() == "MALE");
		assertTrue(0 <= b1.getStrength() && b1.getStrength() <= 4);
		
		assertEquals(3, b2.getAge());
		assertEquals("FEMALE", b2.getGen());
		assertEquals(4, b2.getStrength());
		
		assertEquals(4, f2.getAge());
		assertEquals("MALE", f2.getGen());
	}
	//Test case 1.2: verify toString method works properly
	public void testToString() {
		assertEquals(("B"+b1.getGen().substring(0, 1)+b1.getAge()), b1.toString());
		assertEquals(("F"+f1.getGen().substring(0, 1)+f1.getAge()), f1.toString());
		
		assertEquals("BF3", b2.toString());
		assertEquals("FM4", f2.toString());
	}
	//Test case 1.3: verify maxAge method works properly
	public void testMaxAge() {
		assertTrue(b1.maxAge() == true || b1.maxAge() == false);
		assertTrue(f1.maxAge() == true || f1.maxAge() == false);
		
		assertEquals(false, b2.maxAge());
		assertEquals(true,f2.maxAge());
	}
	//Test case 1.4: verify incrAge method works properly
	public void testIncrAge() {
		assertTrue(b1.incrAge() == true || b1.incrAge() == false);
		assertTrue(f1.incrAge() == true || f1.incrAge() == false);
		
		assertEquals(true, b2.incrAge());
		assertEquals(false,f2.incrAge());
	}
	
	//testing river class
	
	//Test case 2.1: verify that default sets variables correctly
	public void testDefaultInstanceRiver () {
		assertEquals(1, r1.getLength());
		assertEquals(15, r2.getLength());
		assertEquals(3, r3.getLength());
		
		assertTrue(r1.type(0) == "N" || r1.type(0) == "B" || r1.type(0) == "F");
		assertTrue(r2.type(0) == "N" || r2.type(0) == "B" || r2.type(0) == "F");
		assertEquals("F", r3.type(0));
	}
	//Test case 2.2: verify that count methods (numEmpty, numBears, numFish) all work properly
	public void testCountMethods() {
		assertTrue(0 <= r1.numEmpty() && r1.numEmpty() <= r1.getLength());
		assertTrue(0 <= r1.numBears() && r1.numBears() <= r1.getLength());
		assertTrue(0 <= r1.numFish() && r1.numFish() <= r1.getLength());
		
		assertTrue(0 <= r2.numEmpty() && r2.numEmpty() <= r2.getLength());
		assertTrue(0 <= r2.numBears() && r2.numBears() <= r2.getLength());
		assertTrue(0 <= r2.numFish() && r2.numFish() <= r2.getLength());
		
		assertEquals(1, r3.numEmpty());
		assertEquals(1, r3.numBears());
		assertEquals(1, r3.numFish());
	}
	//Test case 2.3: verify that findNextNull method works properly 
	public void testFindNextNull() {
		assertTrue(r1.findNextNull(0) == -1 || r1.findNextNull(0) < r1.getLength());
		assertTrue(r2.findNextNull(0) == -1 || r2.findNextNull(0) < r2.getLength());
		assertEquals(2, r3.findNextNull(0));
	}
	//Test case 2.4: verify that addRandom methos works properly
	public void testAddAnimal() {
		assertTrue(r1.addRandom(b1,a) == true ||r1.addRandom(b1,a) == false );
		assertTrue(r2.addRandom(b1,a) == true ||r2.addRandom(b1,a) == false );
		assertEquals(true, r3.addRandom(b1, a));
	}
	//Test case 2.4: verify that updateCell method works properly
	public void testUpdateCell() {
		r1.updateCell(0, a);
		assertTrue(r1.type(0) == "N");
		
		r2.updateCell(0, a);
		assertTrue(r2.type(0) == "N");
		
		r3.updateCell(0, a);
		assertTrue(r3.type(0) == "N");
	}
	//Test case 2.5: verify that toString method works properly (through cellInfo)
	public void testToStringRiver() {
		assertEquals(10, r1.cellInfo(0).length());
		assertEquals("Cell 1: ", r1.cellInfo(0).substring(0, 8));
		assertEquals(r1.type(0), r1.cellInfo(0).substring(8, 9));
		assertTrue(r1.cellInfo(0).substring(9, 10) == "M" || r1.cellInfo(0).substring(9, 10) == "F");
		assertTrue(0 <= Integer.parseInt(r1.cellInfo(0).substring(10)) && Integer.parseInt(r1.cellInfo(0).substring(10)) <= 9 );
		
		assertEquals(10, r2.cellInfo(0).length());
		assertEquals("Cell 1: ", r2.cellInfo(0).substring(0, 8));
		assertEquals(r2.type(0), r2.cellInfo(0).substring(8, 9));
		assertTrue(r2.cellInfo(0).substring(9, 10) == "M" || r2.cellInfo(0).substring(9, 10) == "F");
		assertTrue(0 <= Integer.parseInt(r2.cellInfo(0).substring(10)) && Integer.parseInt(r2.cellInfo(0).substring(10)) <= 9 );
		
		assertEquals("FF0", r2.cellInfo(0));
	}
	
	//runs the Test 
	public static void main(String args[]) {
	   junit.textui.TestRunner.main(new String[] {"Test"});
	}




}

/*  Made by: Erica DiCristoforo
 *  Purpose of class: This class allows other classes to write the river to a file.  
 */

import java.io.*;

public class WriteToFile {
	
	public WriteToFile(String s, int i) {
		//every time it is called, it will be written to the "cycle.txt" file 
		String fileName = "cycle.txt" ; {
			
			//writes the cycle index and the toString of the river
	        try {
	            BufferedWriter bufferedWriter =
	            		new BufferedWriter(new FileWriter(fileName, true));


	            bufferedWriter.write("Cycle " + i);
	            bufferedWriter.newLine();
	            bufferedWriter.write(s);
	            bufferedWriter.newLine();
	            
	            bufferedWriter.close();
	        }
	        //in case there is an error 
	        catch(IOException ex) {
	            System.out.println(
	                "Error writing to file '"
	                + fileName + "'");
	        }
	     }
}
}

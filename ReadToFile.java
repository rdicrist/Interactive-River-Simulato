/*  Made by: Erica DiCristoforo
 *  Purpose of class: This class allows other classes to read a file and make a river based off it.   
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadToFile {
	int i = 0;
	String line = null;
	String fileName;
	FileReader fileReader;
	BufferedReader bufferedReader;
	public ReadToFile (String name) {

        // The name of the file to open.
        fileName = name;
      
        try {
            // defines fileReader variable
        	 fileReader = 
                     new FileReader(fileName);
            bufferedReader = 
                new BufferedReader(fileReader);
            
            //adds 1 for each line (giving a line count)
            while((line = bufferedReader.readLine()) != null) {
            	i++;
            }   
                    
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
    }
	//gets length of file (how many lines)
	public int getLength () {
		return i;
	}
	//gets string value at designated index
	public String getValue (int i) throws IOException {
		String f = Files.readAllLines(Paths.get(fileName)).get(i);
		return  f;
	}
	//for getValue
	public Paths readAllLines(Path path) {
		// TODO Auto-generated method stub
		return null;
	}
}

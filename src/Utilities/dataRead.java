package Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*File reading*/
public class dataRead {
	public ArrayList<String> dataPull(String givenArtistName) {
		ArrayList<String> retArrayList = new ArrayList<String>(); 
		
		// The name of the file to open.
	    String fileName = "Table2.in";

	    // This will reference one line at a time
	    String line = null;

	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader("data/" + fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	        	String[] values = line.split("<SEP>");
	        	String artistName = "";
	        	String artistLocation = "";
	        	String artistTracks = "";
	        	
	        	artistName = values[2];
	        	artistLocation = values[3];
	        	artistTracks = values[4];
	            //System.out.println(line);
	            if (artistName.toUpperCase().trim().equals(givenArtistName.toUpperCase().trim())) {
	            	retArrayList.add(artistLocation + "<SEP>" + artistTracks);
	            	//System.out.println(artistLocation + "<SEP>" + artistTracks);
	            }
	        	//arr.add(Integer.parseInt(line));
	        }   

	        // Always close files.
	        bufferedReader.close();         
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
	        // Or we could just do this: 
	        // ex.printStackTrace();
	    }
	    return retArrayList;
	}
}

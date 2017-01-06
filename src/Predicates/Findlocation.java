package Predicates;

import java.util.ArrayList;

/*find location class*/
public class Findlocation {
	/*Method call*/
	public String getLocation(ArrayList<String> arr) {
		String artistLocation = "Location: \n";
    	for (String object : arr) {
    		String[] o = object.split("<SEP>");
    		artistLocation =  artistLocation + o[0] + "\n";
		}
		return artistLocation;
	}
}

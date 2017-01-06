package Predicates;

import java.util.ArrayList;

/*Get Songs class*/
public class GetSongs {

	/*Method calling*/
	public String getTracks(ArrayList<String> arr) {
		String artistTracks = "\nTracks: \n";
		for (String object : arr) {
			String[] o = object.split("<SEP>");
			if(o[1].contains("<I>")) {
				String[] s = o[1].split("<I>");
				for (String string : s) {
					artistTracks = artistTracks + string + "\n";
				}
			} else {
				artistTracks = o[1];
			}
			//System.out.println(artistTracks);
		}
		return artistTracks;
	}
}

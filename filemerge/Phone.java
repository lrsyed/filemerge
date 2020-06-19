package filemerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Phone {
	public HashMap<String, List<String>> getPhones(File file) {
	    String line = new String();
	    // Initialize reader object 
	    BufferedReader reader = null;
	    // hashmap for name_id to a set of phone numbers
	    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	    // try-catch block to read phone.csv data
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        // skip header line
	        reader.readLine();
	        
	        // loop through csv file until EOF
	        while((line=reader.readLine())!=null){
	        	// Split each column of line
		        String entry[] = line.split(",");
		        // for each name_id read, place the corresponding phone number into
		        // ArrayList to account for multiple numbers
		        map.computeIfAbsent(entry[2], k -> new ArrayList<String>()).add(entry[1]);
	        }
	    }
	    catch (FileNotFoundException fne) {
	    	// Catch block in case of file not found
	    	System.out.println(fne);
	    }
	    catch (IOException ioe) {
	    	// Catch block in case of IOexception error when opening file
	    	System.out.println(ioe);
	    }
	    finally {
	        if (reader != null) {
	        	// try-catch block to close phone.csv file
	            try {
	                reader.close();
	            }
	            catch (IOException ex) {
	            	// Catch block in case of IOexception error when closing file
	            	System.out.println(ex);
	            }
	        }
	    }
	    return map;
	}
	
	public int getMaxPhoneNumbers(HashMap<String, List<String>> map2) {
		// store length of first List value as current max
		int max = map2.values().iterator().next().size();
		
		// loop through remaining List values to find max number of 
		// phone numbers
		for(List<String> value: map2.values()) {
			//System.out.println(value.size());
			max = ((value.size() > max) ? value.size() : max);
		}
		
		return max;
	}
}

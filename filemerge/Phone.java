package filemerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class Phone {
	public Map<String, Set<String>> readPhones(File file) {
	    String line = new String();
	    // Initialize reader object 
	    BufferedReader reader = null;
	    // hashmap for name_id to a set of phone numbers
	    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
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
		        map.computeIfAbsent(entry[2], k -> new HashSet<String>()).add(entry[1]);
	        }
	    }
	    catch (FileNotFoundException fne) {
	    	System.out.println(fne);
	    }
	    catch (IOException ioe) {
	    	System.out.println(ioe);
	    }
	    catch (Exception e) {
			// Catch block for all remaining errors
			System.out.println(e);
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
	
}

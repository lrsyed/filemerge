package filemerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Name {
	public Map<String, String> readNames(File file) {
	    String line = new String();
	    // Initialize reader object
	    BufferedReader reader = null;
	    // hashmap for id to name
	    Map<String,String> map = new HashMap<String, String>();
	    // try-catch block to read name.csv data
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        // skip header line
	        reader.readLine();
	        
	        // loop through csv file until EOF
	        while((line=reader.readLine())!=null){
	        	// Split each column of line
		        String entry[] = line.split(",");
		        // place id and name to key-value pair respectively
		        map.put(entry[0], entry[1]);
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

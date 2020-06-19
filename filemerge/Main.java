package filemerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		// Access directory of name.csv file
		String namePath = System.getProperty("user.dir") + "/data/name.csv";
		File fName = new File(namePath);
		// Initialize Name object and call getNames method
		// which will return hashmap of name.csv
		Name n = new Name();
		HashMap<String, String> nameData = n.getNames(fName);
		
		// Access directory of phone.csv file
		String phonePath = System.getProperty("user.dir") + "/data/phone.csv";
		File fPhone = new File(phonePath);
		// Initialize Phone object and call getPhone method
		// which will return hashmap of phone.csv
		Phone p = new Phone();
		HashMap<String, List<String>> phoneData = p.getPhones(fPhone);
		
		// pass both name and phone hashmaps to merge matching name_ids
		// and write to output.csv
		mergeAndWrite(nameData,phoneData);
		System.out.println("Merge Completed");
	}
	
	public static void mergeAndWrite(HashMap<String, String> map1, HashMap<String, List<String>> map2) {
		String outputPath = System.getProperty("user.dir") + "/data/output.csv";
		String phoneList;
		// Initialize FileWriter object
		FileWriter csvWriter = null;
		// try-catch block to write csv data
		try {
			csvWriter = new FileWriter(outputPath);
			// Initialize Phone object and call getMaxPhoneNumbers method
			// to obtain max count of phone numbers for a single user
			Phone p = new Phone();
			int maxPN = p.getMaxPhoneNumbers(map2);
			
			// Write default headings along with maxPN phone columns
			csvWriter.append("id");
			csvWriter.append(",");
			csvWriter.append("name");
			for(int i=0;i<maxPN;i++) {
				csvWriter.append(",");
				csvWriter.append("phone_" + (i+1));
			}
			csvWriter.append("\n");
			
			// Loop through each entry in the phone hashmap
			for(Entry<String, List<String>> entry : map2.entrySet()) {
				// if name_id from phone hashmap matches with id of name hashmap,
				// write id, name and all phone numbers associated with the id respectively
				if(map1.containsKey(entry.getKey())) {
					csvWriter.append(entry.getKey());
					csvWriter.append(",");
					csvWriter.append(map1.get(entry.getKey()));
					csvWriter.append(",");
					// Convert List<String> to String where each phone number is separated
					// by a ","
					phoneList = String.join(",", entry.getValue());
					csvWriter.append(phoneList);
					csvWriter.append("\n");
				}
		    }
		} 
		catch (IOException e) {
			// Catch block in case of IOexception error when writing to file
			e.printStackTrace();
		}
		finally {
			// try-catch block to flush and close output.csv file
			try {
				csvWriter.flush();
				csvWriter.close();
			} catch (IOException e) {
				// Catch block in case of IOexception error when closing file
				e.printStackTrace();
			}
	    }
	}

}

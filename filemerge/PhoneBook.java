package filemerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class PhoneBook {

	public static void main(String[] args) {
		// Access directory of name.csv file
		String namePath = System.getProperty("user.dir") + args[0];
		File file_Name = new File(namePath);
		// Initialize Name object and call getNames method
		// which will return hashmap of name.csv
		Name name = new Name();
		Map<String, String> nameData = name.readNames(file_Name);
		
		// Access directory of phone.csv file
		String phonePath = System.getProperty("user.dir") + args[1];
		File file_Phone = new File(phonePath);
		// Initialize Phone object and call getPhone method
		// which will return hashmap of phone.csv
		Phone phone = new Phone();
		// Use set to prevent duplicate phone numbers
		Map<String, Set<String>> phoneData = phone.readPhones(file_Phone);
		
		// pass both name and phone hashmaps to merge matching name_ids
		// and write to output.csv
		mergeAndWrite(nameData,phoneData);
	}
	
	public static void mergeAndWrite(Map<String, String> map1, Map<String, Set<String>> map2) {
		String outputPath = System.getProperty("user.dir") + "/data/output.csv";
		String phoneList;
		// Initialize FileWriter object
		FileWriter csvWriter = null;
		// try-catch block to write csv data
		try {
			csvWriter = new FileWriter(outputPath);
			// call getMaxPhoneNumbers method to obtain max unique phone numbers
			// for a single user
			int maxPhoneNumbers = getMaxPhoneNumbers(map2);
			
			// Write default headings along with maxPhoneNumbers phone columns
			csvWriter.append("id");
			csvWriter.append(",");
			csvWriter.append("name");
			for(int i=0;i<maxPhoneNumbers;i++) {
				csvWriter.append(",");
				csvWriter.append("phone_" + (i+1));
			}
			csvWriter.append("\n");
			
			// Loop through each entry in the phone hashmap
			for(Entry<String, Set<String>> entry : map2.entrySet()) {
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
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			// Catch block for all remaining errors
			System.out.println(e);
		}
		finally {
			// try-catch block to flush and close output.csv file
			try {
				csvWriter.flush();
				csvWriter.close();
				System.out.println("Merge Completed");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			catch (Exception e) {
				// Catch block for all remaining errors
				System.out.println(e);
			}
	    }
	}

	public static int getMaxPhoneNumbers(Map<String, Set<String>> map2) {
		// set max to default of 0
		int max = 0;
		
		// loop through remaining List values to find max number of 
		// phone numbers
		for(Set<String> value: map2.values()) {
			//System.out.println(value.size());
			max = Math.max(max, value.size());
		}
		
		return max;
	}

}

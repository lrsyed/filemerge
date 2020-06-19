CSV File Merger P1

--Requirements--
Requires Java versions 8 and above. 

--Getting Started--
1. Traverse to the /data/ folder and update name.csv and phone.csv if needed.
2. Program is meant to be run through terminal using javac and java commands respectively
3. To run, navigate to src directory using the terminal and run the following: "javac filemerge/Main.java" 
4. Lastly, run "java filemerge/Main" and the results will be written to output.csv in the /data/ directory

--How it Works--
The program will look for name.csv and phone.csv in the data folder specifically. A hashmap of each of the tables are constructed and are merged 
based on their id and name_id column names in the files respectively. Only the common name_ids found in each file are written to the output.csv file 
in the same /data/ directory. 

--Authors--
Asad Syed : asad.syed@ryerson.ca - 6474089360
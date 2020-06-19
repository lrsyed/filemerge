CSV File Merger P1

--Requirements--
Requires Java versions 8 and above. 


--Getting Started--
1. Traverse to the /data/ folder and update name.csv and phone.csv if needed.
2. Program is meant to be run through terminal using javac and java commands respectively
3. Furthermore, the program takes 2 arguments with the first being the relative path to the name.csv file and phone.csv for the second.
3. To run, navigate to src directory using the terminal and run the following: "javac filemerge/PhoneBook.java" 
4. Lastly, run "java filemerge/PhoneBook /data/name.csv /data/phone.csv" and the results will be written to output.csv in the /data/ directory


--How it Works--
The program will look for name.csv and phone.csv in the data folder specifically. A hashmap of each of the tables are constructed and are merged 
based on their id and name_id column names in the files respectively. Only the common name_ids found in each file are written to the output.csv file 
in the same /data/ directory. 


--Potential Improvements for P2--
1. Hadoop Job - Map Reduce
My first idea was to implement a hadoop job and pass the name and phone csv files as inputs. A hadoop job would be configured appropriately such that
the input files would be broken into chunks and each chunck would be compared with the other. This would have overcome the issue of overloading the 
memory 

2. Update with new weekly data
The second solution I came up with, and that doesnt require the use of third party servers, is to use a similar solution as in part 1, but only with
the data added in the last week. For this proposal to work, an additional column in both name.csv and phone.csv will be required. This new column would
keep track of the date of the entry for the name or phone number. As the program has to run on a weekly basis, data from the past 7 days will be read
into the hashmap and the same matching operation would be performed. This would overcome the memory issues once again as only the newly added data would
be loaded into memory. The output.csv would be appeneded to with the new matches. 

However, in the case of a new phone entry matching a previous name entry, (for example, if a phone entry was added for john, who was in the name.csv file 
prior to the last week) the current algorithm would not account for it. Similar situation for a new name entry to a previous phone entry. Thus, to account 
for this situation, each new weekly entry for the phone file will have to be compared to all existing name entries to correctly match and append. 


--Authors--
Asad Syed : asad.syed@ryerson.ca - 6474089360
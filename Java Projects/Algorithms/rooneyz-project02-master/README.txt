Zach Rooney
CS 1C Project 2

project folder: rooneyz-project02


Brief description of submitted files:

src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset opens, reads, and parses a JSON file containing a list of songs and their information
      It stores the information in a array.

src/cs1c/SongEntry.java
    - One object of class SongEntry stores a simplified version of the information from MillionSongDataSubset.

src/cs1c/TimeConverter.java
    - One object of class TimeConverter converts a duration of time in seconds and converts to a string representation with
      hour, min, sec.

src/subsetsum/FoothillTunesStore.java
	- This is the test class for part 2 of project 2. It was given to us by professor Bita Mazloom.
	- An object of type FoothillTunesStore creates an object of type MillionSongDataSubset. The parsed data set from there
	  is then stored in an array of SongEntry objects. Next, it prompts the user for a desired play list duration.
 	  It takes the user's input and creates an object of type SubsetSum and uses SubsetSum's methods to make a play list of 
 	  SongEntry objects that best match the desired duration. 

src/subsetsum/GroceriesFileReader.java
	- This class opens, reads, and parses a text file written in CSV format. It will separate a list of
 	  items and their price into two different ArrayLists. It currently only returns the array of prices stored as doubles.

src/subsetsum/GroceryItem.java
	- One object of this class stores an item name and it's price. It is currently not used significantly. It was written to
	  allow ShoppingBag to return the names of the items as well as their prices if desired.

src/subsetsum/ShoppingBag.java
	- This is the test class for part 1 of project 2. It was given to us by professor Bita Mazloom.
	- An object of type ShoppingBag class prompts the user to input their shopping budget. Then it creates an object 
	  of type SubsetSum to find a best possible grocery shopping list within the given budget.

src/subsetsum/SubsetSum.java
	- This class was the main portion (concept) for this project. 
	- One object of this class stores a sum, a 'master' static array, and array of indices referring to the static 
	  array which represents one possible combination of items from the master array. 
	- This class is designed to use an algorithm to find the best combination from the master list for the user.
	- It is my introduction to algorithms and practice designing them. We had to keep in mind speed and memory usage while
	  writing this class.

src/subsetsum/TestGrocery.java
	- This class is currently not used. It was used to hold and test parts of code while writing the project.


resources/groceries.csv
    - A text file containing a list of grocery items and their price in CSV format. Currently used by GroceriesFileReader. 
      
resources/music_genre_subset.json
	- A JSON file storing a list of thousands of song titles and their information

resources/ShoppingBag_RUN.txt
    - console output of ShoppingBag.java
    
resources/FoothillTunesStore_RUN.txt
    - console output of FoothillTunesStore.java and ShoppingBag.java
    
README.txt
    - description of submitted files
    
    
Desired Improvements (aka if I had more time I would....)
	- Make the master list in SubsetSum a generic type so that only 1 instance variable is needed.
	- have the algorithm remove 'unnecessary' combinations once new ones are made
	- look into programming multiple streams and try to implement 
	- link the item names with prices in ShoppingBag results
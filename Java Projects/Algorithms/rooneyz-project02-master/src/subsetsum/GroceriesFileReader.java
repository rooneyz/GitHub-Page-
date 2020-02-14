package subsetsum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class opens, reads, and parses a text file. It will separate a list of
 * items and their price in to two different ArrayLists.
 * 
 * @author zachrooney
 *
 */
public class GroceriesFileReader {
	private ArrayList<GroceryItem> storeInvetory = new ArrayList<GroceryItem>();
	private ArrayList<Double> priceList = new ArrayList<Double>();

	/**
	 * Opens, reads, and sorts file. Returns the array list of prices
	 * 
	 * @param filePath
	 * @return priceList
	 */
	public ArrayList<Double> readFile(String filePath) {

		// create ArrayList to store the invetory objects
		ArrayList<GroceryItem> storeInvetory = new ArrayList<GroceryItem>();
		ArrayList<Double> priceList = new ArrayList<Double>();
		try {
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			// read the first line from the text file
			String nextLineCheck = br.readLine();

			// loop until all lines are read
			while (nextLineCheck != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] tokenize = nextLineCheck.split(",");

				// assume file is made correctly and make temporary variables
				// for the two types of data
				String tempItem = tokenize[0];
				double tempPrice = Double.parseDouble(tokenize[1]);

				// create temporary instance of Inventory object and load with
				// name and price
				GroceryItem tempObj = new GroceryItem(tempItem, tempPrice);

				// add to array list
				storeInvetory.add(tempObj);

				// read next line before looping
				nextLineCheck = br.readLine();
			}

			// close file stream
			br.close();
		}

		// handle exceptions
		catch (FileNotFoundException fnfe) {
			System.out.println("file not found");
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// create only a array list of doubles (prices) to return
		for (GroceryItem e : storeInvetory) {
			priceList.add(e.getPrice());
		}

		return priceList;
	}

}
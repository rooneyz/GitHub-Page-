package csvReader;
/**
 *  Tests the CSVReader class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVReader object to fill the CellularData class.
 *
 * @author Foothill College, [Zach Rooney]
 */
public class TestCSVReader
{

	/**
	 * Uses a CSVReader to parse a CSV file.
	 * Adds each parsed line to an instance of the CellularData class.
	 */
	public static void main(String[] args) 
	{	
		// NOTE: Make sure to use relative path instead of specifying the entire path 
		// (such as /Users/alicew/myworkspace/so_on_and_so_forth).
		//final String FILENAME = "resources/cellular.csv";	// Directory path for Mac OS X
		final String FILENAME = "resources\\cellular.csv";
		//final String FILENAME = "resources\\celluar_short_oneDecade.csv"; //For Testing
		
		
		// TODO: Create the class CSVReader to parse the CSV data file
		//       The class constructor should only take a string as argument
		//       for the name of the input file.
		//       The constructor should fill the array of country names, year labels, etc.
		// NOTE: Handle all exceptions in the constructor.
		//       For full credit, do *not* throw exceptions to main. 
		CSVReader parser = new CSVReader(FILENAME);

		// TODO: In class CSVReader the accessor methds should only return values
		//       at instance variables.
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		

		// Stores the 2D array of cellular data for all countries.
		CellularData datatable;
		int numRows = parsedTable.length;
		int numColumns = parser.getNumberOfYears();
		int startingYear = yearLabels[0];

		datatable = new CellularData(numRows, numColumns, startingYear);

		// From the array that stores parsed information,
		// add one country at a time to an object of type CellularData.
		for (int countryIndex = 0; countryIndex < countryNames.length; countryIndex++)
		{
			double [] countryData = parsedTable[countryIndex];
			datatable.addCountry(countryNames[countryIndex], countryData);					
		}


		System.out.printf(countryNames[14] + " (1960 to 2014): %.2f \n", datatable.getNumSubscriptionsInCountryForPeriod(countryNames[14],1960,2014));
		// the output is: 
		// Bahamas, The (1960 to 2014): 1080.69

		System.out.printf(countryNames[100] + " (1950 to 2000): %.2f \n", datatable.getNumSubscriptionsInCountryForPeriod(countryNames[100],1950,2000));
		// the output is: 
		// ERROR : requested year 1950  is less than 1960
		// Hong Kong SAR, China (1950 to 2000): -1.00 

		System.out.printf(countryNames[251] + " (1980 to 2014): %.2f \n", datatable.getNumSubscriptionsInCountryForPeriod(countryNames[251],1980,2014));
		// the output is: 
		// United States (1980 to 2014): 1271.06


        // TODO: For full credit, include test cases in addition to those provided.
		//
		// TODO: Also, make sure to test for other invalid requests for range of years.
		System.out.println("******************Personal Error Check****************");
		System.out.printf(countryNames[131] + " (1985 to 2015): %.2f \n", datatable.getNumSubscriptionsInCountryForPeriod(countryNames[131],1985,2015));
		System.out.printf(countryNames[200] + " (1980 to 2014): %.2f \n", datatable.getNumSubscriptionsInCountryForPeriod(countryNames[200],1980,2014));
		System.out.printf(countryNames[241] + " (1950 to 2000): %.2f \n", datatable.getNumSubscriptionsInCountryForPeriod(countryNames[241],1950,2000));
		
		System.out.println("\nDone with TestCSVReader.\n");
		
	}

}

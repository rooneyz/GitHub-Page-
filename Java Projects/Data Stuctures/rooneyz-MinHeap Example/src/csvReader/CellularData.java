package csvReader;

/**
 *  This Class creates a 1D array composed of countries and a parallel 2D array of the countries
 *  total cellular data subscriptions by year.
 * @author Zach Rooney
 *
 */

public class CellularData {
	
	private int numRows;
	private int numColumns;
	private int startingYear;
    private double [][] table;
    private String countryList [];
	private int currentRows = 0;
	private int currentColumns = 0;
	private int years [];
	
	
	
/**
 * Constructs a CellularData object with parameters given. The parameters are used to create
 * the 1D and 2D arrays.
 * @param numRows           number of rows in table
 * @param numColumns		number of columns in table
 * @param startingYear		the year the data starts
 */
	public CellularData(int newNumRows, int newNumColumns, int newStartingYear) {
		this.numRows = newNumRows;
		this.numColumns = newNumColumns;
		this.startingYear = newStartingYear;
		this.table = new double [numRows][numColumns];
		this.countryList = new String [numRows];
		this.years = new int [numColumns];	
	
	}
	/**
	 *  This method will add a new country to the 1D array "countryList"
	 *  and its data to the 2D array "table"
	 * 
	 * @param newCountry     country name to be added
	 * @param newData		 array of country's data to be added
	 */
	public void addCountry(String newCountry, double[] newData) {
		
		//System.out.println(numRows);
		//System.out.println(currentRows);
		
		if (numRows >= currentRows ){
			this.countryList[currentRows] = newCountry;
			this.table [currentRows] = newData;
			this.currentRows++;
			this.currentColumns++;
			//System.out.println(newData); //For Testing
		}	
	}
	/**
	 * This method returns a string value of a CellularData object.
	 * returns: aString
	 */
	 public String toString() {
		 String aString = "";
		 System.out.print("Country Year  ");
		 for(int i = 0; i < numColumns; i++) { // i = rows
			 System.out.print((startingYear + i)+" ");
		 }
		 System.out.println("");
		 for(int i = 0; i < table.length; i++) { // i = rows
		     aString += countryList[i] + "  ";
			 for(int j = 0; j < table[i].length; j++) { // j = columns
		        aString += "  " + (table[i][j]);
		     }
		        aString += "\r\n";
	      
		 }
		 return aString;
}
/**
 * This Method takes takes in 3 parameters. A string is taken as the country name, 
 * and int firstYear, and int lastYear. It then checks to see if there a match for the country in
 * 1D array "countryList". If a match is found it will return the sum of subscriptions within
 * the dates given (int firstYear and int lastYear)in string form. If the date parameters are invalid, an error 
 * message is given and a sum of any valid range within the parameters is returned.
 * 
 * @param string		name of country searching for
 * @param firstYear     first year of data range requested
 * @param lastYear		last year of data range requested
 * @return sumRange     the sum of the data within the given time frame
 */
	public double getNumSubscriptionsInCountryForPeriod(String desiredCountry, int firstYear, int lastYear) {
		int indexNum = 0;
		double sumRange = 0;
		int firstIndex = 0;
		int errorCheck = 0;
		int countryCheck = 0;
		int numYears = 0;
		for(int i = 0; i < countryList.length; i++) { // i = rows 
			//System.out.println(countryList[i]); // For Testing
			//System.out.println(string);		// For Testing
			if (countryList[i].equals(desiredCountry)){
			         indexNum = i;	
			         countryCheck = 1;
			         break;
			         }
		    }
			if (countryCheck == 0){
				System.out.println("");
				System.out.println ("Country " + desiredCountry +" not found in data");
			}
		if (countryCheck == 1)	{
		//System.out.println(indexNum); // For Testing
		    if (firstYear < this.startingYear){
			   firstIndex = 0;
			   firstYear = this.startingYear;
			   errorCheck = 1;
		     }
		    else if (firstYear > (startingYear + numColumns)){
				    firstIndex = (startingYear + numColumns);
				    firstYear = startingYear;
				    errorCheck = 1;
		    }
		    else {
				firstIndex = firstYear - startingYear;
			 }
		    if(lastYear > ((numColumns-1) + startingYear)){
				lastYear = 2014;   
		    	errorCheck =1;
		    }
		    if(lastYear < startingYear){
		    	lastYear = startingYear;   
			    errorCheck =1;
			   }
		    numYears = lastYear - firstYear;
		    if (numYears > (numColumns-1)){
			   numYears = numColumns-1;
			   errorCheck =1;
		   } 
		     
		   if (errorCheck == 1){
			   System.out.println("**ERROR** Some of the parameters entered for " + desiredCountry + " (below) are invalid.");
			   System.out.println("Valid Years are " + startingYear + " to " + (startingYear + numColumns -1) ); 
			   System.out.println("Showing the total subscriptions for valid numbers within the parameters:");
		   }
	       //System.out.println(numYears); //For Testing
	       //System.out.println(firstIndex); //For Testing
		   for(int j = 0; j <= numYears; j++) { // i = rows 
		     //System.out.println(indexNum);   // For Testing
		     //System.out.println(firstIndex); // For Testing 
		    // System.out.println(table[indexNum][firstIndex]); // For Testing
	    	   sumRange += table[indexNum][firstIndex];
			   firstIndex = firstIndex + 1;
		 }
		}
     return sumRange;
	}
}


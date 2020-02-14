package cellularData;

import java.io.*;
import java.util.*;

/**
 * This class will take a CSV file as a parameter then separate and store the information of
 * the file. It then has methods to return a list of countries in the data, the 
 * table of data, the years of the data, and the total years of data.
 * 
 * @author Zach Rooney
 *
 */
public class CSVReader {
	private String [] countryNames; 
	private int [] yearLabels; 
	private double [][] parsedTable;
	private Scanner scanner;
	private String tableName;
	private String variableName;
	private int countryCount;
	private int numYears;
	
	
	/** Constructs a CSVReader object. A file is taken as a parameter and the data is separated
	 * and stored by type. Information is stored as variables, arrays, or 2d arrays.
	 * 
	 * 
	 * @param inputCSVFile
	 */
	public CSVReader(String inputCSVFile) {
	   Scanner input;
	    try 
	    {
	    	File inFile = new File (inputCSVFile);
	        input = new Scanner (inFile);
	        
	        // record table name
	         this.tableName = input.nextLine();
	        // System.out.println(tableName); //For Testing
	        
	      //record number of countries
	        String aLine = input.nextLine();
	         this.countryCount = Integer.parseInt(aLine.split(",")[1]); 
	        //System.out.println(countryCount); //For Testing
	        
	      //Fill int [] yearLabels 
	        String[] years = input.nextLine().split(",");
	        this.numYears = years.length - 1;
	        this.yearLabels = new int[numYears];
	        for(int i = 0; i < numYears; i++){
	            this.yearLabels[i] = Integer.parseInt(years[i+1]); //i+1 to skip the first entry in the string 
	            //System.out.print(yearLables[i]); // For Testing
	        }
	        
	       
	        int count = 0;
	        this.countryNames = new String [countryCount];
	        this.parsedTable = new double [countryCount] [yearLabels.length];
	        
	        // constructs countryName[] and parsedTable [][]
	        while (input.hasNextLine()) 
	        {
	            String line = input.nextLine();
	          
	            String [] tokens = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
	            this.countryNames[count] = tokens[0];
	            //System.out.print(countryNames[count]); //For Testing
	            
	          	int secondCounter = 1;
	          	//System.out.println(tokens.length-1);
	            for (int i=0; i < (tokens.length - 1); i++ ){
	            	this.parsedTable [count][i] = Double.parseDouble(tokens[secondCounter]);
	            	secondCounter++;
	            	//System.out.print(parsedTable[count] [i]); //For Testing
	            }
	            count++;
	         }
	         input.close();
	    } 
	    catch (FileNotFoundException e) 
	    {
	        System.out.println("File " + inputCSVFile + " not found!");
	        System.exit(1);
	    }
		}
	
	/**
	 *  This method will return a string array of the countries involved in the data
	 * @return countryNames  array of countries involved
	 */
	public String[] getCountryNames() {
		return countryNames;
	}
	
	/**
	 * This method will return the year labels for the table (the years of the data)
	 * @return yearLabels   array of years that data was collected
	 */
	public int[] getYearLabels() {
		return yearLabels;
	}
	
	/**
	 * This method will return the table of cellular data. This only returns the data, not the 
	 * years or countries.
	 * 
	 * @return parsedTable table of cellular data
	 */
	public double[][] getParsedTable() {
		return parsedTable;
	}
	
	/**
	 * This method will return the number of years that data was collected .
	 * 
	 * @return numYears  number of years data covers
	 */
	public int getNumberOfYears() {
		return numYears;
	}

	}



	

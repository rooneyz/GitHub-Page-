package cellularData;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *  Tests the GraphView class by creating an object of type GraphView and adding components to it.
 *  Creates one container of type JFrame and adds an object of type GraphView.
 *
 * @author Foothill College, Timothy Hsu, Zach Rooney
 */
public class TestGraphView 
{
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 600;

	/**
	 * Builds a list of countries to debug.
	 */
	private void debugListOfCountries(Country [] allCountries)
	{
		// TODO: The purpose is to help you debug
		// Note: The implementation of method is optional.
	}

	/**
	 * Creates a generic linked list. Then based on the user's input,
	 * adds a random number of countries to the list.
	 * @param allCountries      An array of Country objects
	 */
	private LinkedList<Country> buildCountryList(Country [] allCountries)
	{	
		JFrame frame = new JFrame("Cellular Graph");

		int numberOfBadInputs = 0;
		String badInputMessage = "";
		
		String userInput = (String)JOptionPane.showInputDialog(
				frame,
				"Enter the number of countries to graph:\n",
				"Input",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"5");
	
		while(!userInput.matches("[-+]?\\d*\\.?\\d+") || Double.parseDouble(userInput)<=0 || Double.parseDouble(userInput) % 1 != 0) { // http://stackoverflow.com/questions/14206768/how-to-check-if-a-string-is-numeric
			numberOfBadInputs++;
			switch (numberOfBadInputs) {
			case 1:
				badInputMessage = "It has to be a positive integer. Try again.\n";
				break;
			case 2:
				badInputMessage = "I mean it. Enter a positive integer.\n";
				break;
			case 3:
				badInputMessage = "Come on, it's not that hard.\n";
				break;
			case 4:
				badInputMessage = "Are you serious?\n";
				break;
			case 5:
				badInputMessage = "Just enter something valid, ok?\n";
				break;
			case 6:
				badInputMessage = "Do you even read?\n";
				break;
			case 7:
				badInputMessage = "STOP MESSING UP! I can't take this pain anymore.\n";
				break;
			case 100:
				badInputMessage = "I gotta say, you're dedicated.\n";
				break;
			default:
				badInputMessage = "Number of screw-ups: " +numberOfBadInputs;
				break;
			}
			
			userInput = (String)JOptionPane.showInputDialog(
					frame,
					badInputMessage,
					"Input",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					"5");
            }
		
		if (userInput.length()>8) {
			userInput = userInput.substring(0, 8);
		}
		
		int requestedSize = (int)Double.parseDouble(userInput);

		// Build the list out of a random selection of countries.
		Random random = new Random();

		boolean bigRequestedSize = false;
		// A singly linked list of country data.
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++) {
			
			if (requestedSize>=allCountries.length && i<allCountries.length) {
				bigRequestedSize = true;
				selectedCountries.add(allCountries[i]);
			}
			
			else if (requestedSize<allCountries.length){
				int selectedIndex = random.nextInt(allCountries.length);
			
				while (selectedCountries.contains(allCountries[selectedIndex])!=null) {
					selectedIndex = random.nextInt(allCountries.length);
				}
				
				selectedCountries.add(allCountries[selectedIndex]);
			}	
			
		}
		
		if (bigRequestedSize) {
			JOptionPane.showMessageDialog(null, "There are the same or fewer countries in the file than the amount requested.\n"
					+ "Therefore, all the countries in the file will be displayed.");
		}

		return selectedCountries;
	}


	/**
	 * Initializes the GUI with two JPanels and populates them.
	 * One panel draws the data points, the second draws the legend.
	 * @param selectedCountries      A randomly selected list of countries.
	 *
	 * Note: You may add as many panels as you see fit.
	 */
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		
		int bottomPanelHeight = 45;
		
		JFrame frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenDim = tk.getScreenSize();
		frame.setLayout(new BorderLayout());
		
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(0,0,0));
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(0,0,0));
		
		// TODO: Select a layout for your frame
		//BorderLayout layout = new BorderLayout(0, 0);		
		//frame.setLayout(layout);
		frame.pack();
		
		// TODO: Specify the size of your graph view based on your other panels
		int graph_panel_size = (int) (FRAME_WIDTH*.82);
		
		
		// TODO: Define the class GraphView such that it will map the cellular data of a country to the 
		//       width and height of the panel.
		GraphView<Country> myPlots = new GraphView<Country>(graph_panel_size-frame.getInsets().left+5-frame.getInsets().right+5, FRAME_HEIGHT-frame.getInsets().top+5-frame.getInsets().bottom+5-bottomPanelHeight, selectedCountries);	
		myPlots.setPreferredSize(new Dimension(graph_panel_size, FRAME_HEIGHT-frame.getInsets().top+5-frame.getInsets().bottom+5-bottomPanelHeight));
		

		// Draw the legend
		graph_panel_size = (int) (FRAME_WIDTH*.17);
		LegendPanel<Country> myLegendPanel = new LegendPanel<Country>(graph_panel_size-frame.getInsets().left+5-frame.getInsets().right+5, FRAME_HEIGHT-frame.getInsets().top+5-frame.getInsets().bottom+5-bottomPanelHeight, myPlots);
		myLegendPanel.setPreferredSize(new Dimension(myLegendPanel.getLabelsWidth()-frame.getInsets().left+5-frame.getInsets().right+5, myLegendPanel.getLabelsHeight()-frame.getInsets().top+5-frame.getInsets().bottom+5));

		JScrollPane scrollPane = new JScrollPane(myLegendPanel);
		scrollPane.setBorder(null);
		
		scrollPane.setPreferredSize(new Dimension(graph_panel_size, FRAME_HEIGHT-frame.getInsets().top+5-frame.getInsets().bottom+5-bottomPanelHeight));

		
		northPanel.add(myPlots);
		northPanel.add(scrollPane);


		graph_panel_size = (int) (FRAME_WIDTH);
		
		BottomPanel<Country> myBottomPanel = new BottomPanel<Country>(graph_panel_size-frame.getInsets().left+5-frame.getInsets().right+5, bottomPanelHeight, myPlots);
		myBottomPanel.setPreferredSize(new Dimension(graph_panel_size, bottomPanelHeight));
		southPanel.add(myBottomPanel);
		
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		// Setup the frame to view.
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);	
		int xPos = (screenDim.width / 2) - (frame.getWidth() / 2);
		int yPos = (screenDim.height / 2) - (frame.getHeight() / 2);
		frame.setLocation(xPos, yPos);

	}

	/**
	 * Uses a CSV to parse a CSV file.
	 * Adds the data for each country to an array of Country objects.
	 * Adds a random selection of countries to a generic LinkedList object.
	 * Draws the list of countries to a JFrame.
	 */
	public static void main(String[] args) 
	{		
		//final String FILENAME = "resources/cellular_short_oneDecade.csv";	// test file with shorter number of countries and subscription years
		final String FILENAME = "resources/cellular.csv";	// test file with latest set of countries and subscription years

		// Parses the CSV data file
		// NOTE: Handle all exceptions in the constructor.
		//       For full credit, do *not* throw exceptions to main. 
		CSVReader parser = new CSVReader(FILENAME);

		// In class CSVReader the accessor methods only return values of instance variables.
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		// Holds the data for all Country object read from the input data file.
		Country [] countries;

		// Initializes the to the number of entries read by CSVReader.
		countries = new Country[countryNames.length];

		// Reference to a Country object
		Country current;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			// TODO: Initially convert your CountryList to a generic LinkedList and make sure that list builds 
			// 		 correctly using the original Country constructor which takes the numberOfYears to setup
			// 		 the array of subscriptions.
			// NOTE: Once you've verified that your generic LinkedList builds correctly,
			//       make sure to comment the line below before submitting.
			//current = new Country(countryNames[countryIndex], numberOfYears);		// version 1

			// TODO: Once you are successful in creating a generic LinkedList of countries, create a
			// 		 LinkedList of SubscriptionYear in the Country class.
			// 	     So, your Country class should no longer have an array of SubscriptionYear objects.
			current = new Country(countryNames[countryIndex]);	// version 2 and final version of Country constructor

			// Go through each year of cellular data read from the CSV file.
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}

			// Add the newly created country to the 1D array.
			countries[countryIndex] = current;
		}

		// Creates an object of our current application.		
		TestGraphView application = new TestGraphView();

		// TODO: Initially, to test your output you may hard-code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//application.debugListOfCountries(countries);

		// Tests the generic LinkedList class by adding a random number of entries
		// from the array of Country objects.
		LinkedList<Country> listOfCountries = application.buildCountryList(countries);

		// Create graphical representation of our data.
		application.initializeGui(listOfCountries);

		// flush the error stream
		System.err.flush();

		System.out.println("\nDone with TestGraphView.");
	}
}
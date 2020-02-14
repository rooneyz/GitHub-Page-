team04-project09

Brief description of submitted files:

- There are two new mouse features. GraphView was modified to only show point data when clicked and BottomPanel Class
  was added.

src/cellularData/Country.java
    - One object of class Country stores represents a single country. Each country has a string name, and an 
      LinkedList<SubscriptionYear> that holds all the years and cellular data for each year of that country
      
src/cellularData/LinkedList<T>.java
    - One object of class holds and manages a linked list of generic object type <T>
    - Implements Iterable so that it can be iterated
    - Contains Node<T> reference "head" that points to the first node in the linked list
    - Contains int listLength that keeps track of the size of the linked list
    - Has private helper class ListIterator that implements Iterator<T>. This class defines how to iterate 
      LinkedList
    - Contains a method that gets a Country object
    
src/cellularData/Node<T>.java
    - This class creates Node<T> objects that are used in by LinkedList
    - Contains generic object 'data' of type <T>
    - Contains Node<T> reference "next" that points to another Node<T> object 
    
src/cellularData/CSVReader.java
    - this class will take a CSV file as a parameter then separate and store the information of
      the file. It then has methods to return a list of countries in the data, the 
      table of data, the years of the data, and the total years of data.
    
src/cellularData/SubscriptionYear.java
    - One object of this class represents a single subscription year with 
      the year and number of subscriptions for that year.

src/cellularData/ColoredPoint.java
	- One object of this class represents one point. This class extends Point class. It holds the original x, original y
	  and color variables.
	
src/cellularData/GraphView.java
	- GraphView extends JPanel. It creates a JPanel with a graph of the subscriptionYear data held in each country
	  object of the countryList linked list (sent as a parameter).
	- Implements MouseListener, which is used when the user clicks on the graph to view labels. The implementation
	  of MouseListener was our 1st new feature. public void mouseClicked(MouseEvent e) was added at the end of the
	  constructor.

src/cellularData/LegendPanel.java
	- LegendPanel extends JPanel. It creates a JPanel that severs as a graph legend for the GraphView object that
	  is sent in as a parameter. It shows which color goes to each country.

src/cellularData/TestGraphView.java
	- Main test that creates a random linked list of countries. It then creates a JFrame to display the GraphView and 
	  LegenPanel objects.

src/cellularData/VerticalLabelUI.java
	- http://tech.chitgoks.com/2009/11/13/rotate-jlabel-vertically/
	- This class was gotten from the web site above. It allowed us to vertically rotate a JLabel for our Y axis label.

src/cellularData/BottomPanel.java
	- Creates a panel on the bottom of the GUI. It contains a button that allows the user to find cellular subscriptions 
	  for a country and a given time period.
	- Implements ActionListener for the button to function.

resources/cellular.csv
    - A CSV (Comma Separated Value) file.
    - Contains a list of countries and their cellular subscriptions data for a range of years
    
resources/RUN.txt
    - console output of TestCountryList.java

README.txt
	- description of submitted files
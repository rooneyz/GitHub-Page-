package cellularData;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class BottomPanel creates a panel on the bottom of the GUI. It contains a
 * button that allows the user to find cellular subscriptions for a country
 * and a given time period.
 * 
 * @author Timothy Hsu, Zach Rooney
 *
 * @param <T>	The name of the class that contains cellular data to be graphed
 */
public class BottomPanel<T> extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private Font font;
	private String [] countryNames;
	private LinkedList<T> countryLinkedList;
	  
	JButton pushButton = new JButton("Get Subscriptions");
	JLabel output = new JLabel();

	
	 /**
	  * Initializes a BottomPanel with its dimensions, country
	  * data points, and button action listener.
	  * 
	  * @param width	The width of the LegendPanel
	  * @param height	The height of the LegendPanel
	  * @param graph	A GraphView object that contains the data of the countries/cellular data
	  */
	 public BottomPanel (int width, int height, GraphView<T> graph) {
		  
		  this.width = width;
		  this.height = height;
		  this.countryNames = graph.getCountries();
		  this.setLayout(new FlowLayout(FlowLayout.LEADING));
		  font = new Font("Serif", Font.PLAIN, 14);
		  output.setText("Click here to view subscription data for a period of time.");
		  output.setFont(font);
		  
		  pushButton.addActionListener(new ActionListener()
		  {
		    public void actionPerformed(ActionEvent e)
		    {

		    	String[] countryList = graph.getCountries();
		    	Integer[] yearList = graph.getYearLabels();
				
				JComboBox<String> countryField = new JComboBox<>(countryList);
				JComboBox<Integer> startYearField = new JComboBox<>(yearList);
				JComboBox<Integer> endYearField = new JComboBox<>(yearList);

			      JPanel theDropdown = new JPanel();
			      theDropdown.setLayout(new BoxLayout(theDropdown, BoxLayout.Y_AXIS));
			      theDropdown.add(new JLabel("Country name:"));
			      theDropdown.add(countryField);
			      theDropdown.add(new JLabel("Starting year:"));
			      theDropdown.add(startYearField);
			      theDropdown.add(new JLabel("Ending year:"));
			      theDropdown.add(endYearField);
			      
			      int result = JOptionPane.showConfirmDialog(null, theDropdown, 
			               "Enter country/year range ", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) {
			    	  countryLinkedList = graph.getList();
			    	  Country temp = countryLinkedList.getCountry(countryField.getSelectedItem().toString());
			    	  int start = Integer.parseInt(startYearField.getSelectedItem().toString());
			    	  int end = Integer.parseInt(endYearField.getSelectedItem().toString());
			    	  
			    	  try {
			    	  Double countryData = temp.getNumSubscriptionsForPeriod(start,end);
			    	  String countryDataRounded = String.format("%.2f", countryData);
			    	  output.setText(countryField.getSelectedItem().toString()+" ("+startYearField.getSelectedItem().toString()+
			    			  "-"+endYearField.getSelectedItem().toString()+"): "+countryDataRounded);
			    	  } catch(IllegalArgumentException e1) {
			    		  output.setText("You've ended before you've begun. How is that possible?"); 
			    	  }
			      }    
		    }
		  });
		  
		 this.add(pushButton);
		 
	 }  // End of constructor	  
  
	
	/**
	 * Draws the output of the get subscriptions action listener to the window.
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawLine(0, 0, width, 0);
		output.setBounds(160, 0, (int) output.getPreferredSize().getWidth(),
				(int) output.getPreferredSize().getHeight() + 20);
		this.add(output);
	}

	/**
	 * Method we must override in order to implement ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}

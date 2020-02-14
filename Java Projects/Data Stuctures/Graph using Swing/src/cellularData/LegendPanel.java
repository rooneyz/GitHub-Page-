package cellularData;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class LegendPanel creates a list of country names represented on a
 * GraphView panel, along with the color that represents these countries.
 * 
 * @author Timothy Hsu, Zach Rooney
 *
 * @param <T>	The name of the class that contains cellular data to be graphed
 */
public class LegendPanel<T> extends JPanel {

	private static final long serialVersionUID = 1L;
	private int width;
	  private int height;
	  private double xMarg;
	  private double yMarg;
	  private String [] countryNames;
	  private ColoredPoint [] coloredPoint;
	  private int numberOfYears;
	  private int labelsHeight;
	  private int labelsWidth;
	  
	  JLabel label = new JLabel("Legend");
	
	  /**
	   * Initializes a LegendPanel with its dimensions and country
	   * data points.
	   * 
	   * @param width	The width of the LegendPanel
	   * @param height	The height of the LegendPanel
	   * @param graph	A GraphView object that contains the data of the countries/cellular data
	   */
	  public LegendPanel (int width, int height, GraphView<T> graph) {	
		  this.width = width;
		  this.height = height;
		  this.labelsWidth = width;
		  this.labelsHeight = height;
		  this.xMarg = 10;
		  this.yMarg = 10;
		  this.countryNames = graph.getCountries();
		  this.coloredPoint = graph.getColoredPoint();
		  this.numberOfYears = graph.getNumberOfYears();
		  if (countryNames.length*50+60 >=height) {
			  this.labelsHeight=countryNames.length*50+60;
		  }
		  
		  for (int i=0;i<countryNames.length; i++) {	
			  if (this.labelsWidth<40+getFontMetrics(getFont()).stringWidth(countryNames[i])) {
				  this.labelsWidth = 40+getFontMetrics(getFont()).stringWidth(countryNames[i]);
			  }
		  }

	  }  // End of constructor	  
  
	  /**
	   * Gets the real height of the LegendPanel. This height is dependent
	   * on the number of countries.
	   * 
	   * @return	The labels height
	   */
	  public int getLabelsHeight() {
		  return this.labelsHeight;
	  }
	  
	  /**
	   * Gets the real width of the LegendPanel. This width is dependent
	   * on the longest country name represented on the graph.
	   * 
	   * @return	The labels width
	   */
	  public int getLabelsWidth() {
		  return this.labelsWidth;
	  }
	
	/**
	 * Draws the LegendPanel to the window
	 * 
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		label.setBounds((int)xMarg+30, (int)yMarg, (int)label.getPreferredSize().getWidth(), (int)label.getPreferredSize().getHeight());
		this.add(label);
		
		int spacing = 0;
		int increment = 0;
		
		for (int i=0;i<countryNames.length; i++) {
		g.setColor(coloredPoint[increment].getColor());
		g.fillRect(15, 50+spacing, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString(countryNames[i], 35, 60+spacing);
		spacing= spacing+50;
		increment = increment + this.numberOfYears;
		}
		
	}
}
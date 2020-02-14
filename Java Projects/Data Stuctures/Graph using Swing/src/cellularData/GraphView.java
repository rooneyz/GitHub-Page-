package cellularData;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class GraphView creates a JPanel of a graph of cellular data of the number of
 * countries specified by the user.
 * 
 * @author Timothy Hsu, Zach Rooney
 *
 * @param <T>
 *            The name of the class that contains cellular data to be graphed
 */
public class GraphView<T> extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private double plottedXmin = 0;
	private double plottedXmax = 0;
	private double plottedYmin = 0;
	private double plottedYmax = 0;
	private double xMax = 0;
	private double xMin = 9999;
	private double yMax = 0;
	private double yMin = 9999;
	private double xMarg;
	private double yMarg;
	private Font font;
	private LinkedList<T> list;
	private double[] convertedX;
	private double[] convertedY;
	private double[] originalX;
	private double[] originalY;
	private ColoredPoint[] coloredPoint;
	private String countryNames[];
	private int numberOfYears;
	private int pointLabelXValue;
	private int pointLabelYValue;
	JPanel currenPanel;

	JLabel label = new JLabel("Cellular Subscription Data \n (click point to view data)");
	JLabel xAxisLabel = new JLabel("Year");
	JLabel yAxisLabel = new JLabel("Number of Subscriptions (per 100 people)");

	JLabel pointLabel = new JLabel("");

	/**
	 * Initializes the structure of the graph and the positions of all the
	 * points on the graph.
	 * 
	 * @param width
	 *            The width of the window of the GraphView panel
	 * @param height
	 *            The height of the window of the GraphView panel
	 * @param country
	 *            The linked list of country objects, along with their cellular
	 *            data
	 */
	public GraphView(int width, int height, LinkedList<T> country) {

		yAxisLabel.setUI(new VerticalLabelUI(false));

		this.width = width;
		this.height = height;
		this.xMarg = 50;
		this.yMarg = 30;
		this.plottedXmin = yMarg;
		this.plottedXmax = width - yMarg * 1.5;
		this.plottedYmin = height - xMarg;
		this.plottedYmax = xMarg / 2;
		this.countryNames = new String[country.size()];
		int countryNum = 0;
		setSize(width, height);
		this.list = country;
		super.addMouseListener(this);
		boolean firstIndicator = false;
		int counter = 0;

		for (T e : list) {

			if (e instanceof Country) {
				Country tempCountry;
				tempCountry = (Country) e;
				LinkedList<SubscriptionYear> subscriptions;
				subscriptions = tempCountry.getSubscriptions();
				countryNames[countryNum] = ((Country) e).getName();
				countryNum++;

				if (firstIndicator == false) {
					convertedX = new double[subscriptions.size() * country.size()];
					convertedY = new double[subscriptions.size() * country.size()];
					originalX = new double[subscriptions.size() * country.size()];
					originalY = new double[subscriptions.size() * country.size()];
					firstIndicator = true;
				}

				Iterator<SubscriptionYear> itr = subscriptions.iterator();
				SubscriptionYear current = null;

				while (itr.hasNext()) {
					current = itr.next();
					convertedX[counter] = current.getYear();
					convertedY[counter] = current.getSubscription();
					originalX[counter] = current.getYear();
					originalY[counter] = current.getSubscription();

					if (current.getYear() > this.xMax) {
						this.xMax = current.getYear();
					}

					if (current.getYear() < this.xMin) {
						this.xMin = current.getYear();
					}

					if (current.getSubscription() > this.yMax) {
						this.yMax = current.getSubscription();
					}

					if (current.getSubscription() < this.yMin) {
						this.yMin = current.getSubscription();
					}

					counter++;
				}

			} // End of instanceof

		} // End of enhanced loop

		// Convert Points to Mapped Points
		for (int i = 0; i < convertedX.length; i++) {
			convertedX[i] = map(convertedX[i], xMin, xMax, plottedXmin, plottedXmax);
			convertedY[i] = map(convertedY[i], yMin, yMax, plottedYmin, plottedYmax);
		}

		// Assign random color to ColoredPoint and build ColoredPoint array
		coloredPoint = new ColoredPoint[originalX.length];
		int num = 0;
		numberOfYears = originalX.length / list.size();

		for (int i = 0; i < list.size(); i++) {
			int R = (int) (Math.random() * 256);
			int G = (int) (Math.random() * 256);
			int B = (int) (Math.random() * 256);
			Color randomColor = new Color(R, G, B);
			for (int p = 0; p < numberOfYears; p++) {
				ColoredPoint point = new ColoredPoint(randomColor, convertedX[num], convertedY[num], originalX[num],
						originalY[num]);
				coloredPoint[num] = point;
				num++;
			}
		}

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				boolean labelMade = false;

				int xvar = e.getX();
				int yvar = e.getY();
				for (int i = 0; i < convertedX.length; i++) {
					int xDiff = (int) Math.abs(coloredPoint[i].getX() - xvar);
					int yDiff = (int) Math.abs(coloredPoint[i].getY() - yvar);
					if (xDiff < 5 && yDiff < 5) {
						pointLabel = new JLabel(coloredPoint[i].getLabel());
						pointLabelXValue = ((int) (convertedX[i] - 30));
						pointLabelYValue = (int) (convertedY[i] - 20);
						repaint();
						labelMade = true;
					} // If Statement

				} // For Loop

				if (labelMade == false) {
					pointLabel = new JLabel("");
					repaint();
				} // If Statement

			} // Method
		}); //Mouser
	} // End of Constructor

	/**
	 * Converts the coordinates of cellular data (year, number of subscriptions)
	 * into pixel values for positioning on a GUI graph.
	 * 
	 * @param value
	 *            The original value of the cellular data that is to be
	 *            converted
	 * @param dataMin
	 *            The lowest value in the entire original data
	 * @param dataMax
	 *            The highest value in the entire original data
	 * @param plottedMin
	 *            The minimum position of a point on the graph, in pixels
	 * @param plottedMax
	 *            The maximum position of a point on the graph, in pixels
	 * @return The converted pixel value for a point to be graphed
	 */
	static public final double map(double value, double dataMin, double dataMax, double plottedMin, double plottedMax) {
		return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax - dataMin));
	}

	/**
	 * Draws the cellular data graph and points onto the display
	 * 
	 */
	public void paintComponent(Graphics g) {

		removeAll();

		super.paintComponent(g);

		// Add Labels
		label.setBounds(this.width / 3 - 12, 0, (int) label.getPreferredSize().getWidth(),
				(int) label.getPreferredSize().getHeight() + 20);
		this.add(label);
		xAxisLabel.setBounds(this.width / 2 - 12, (int) (this.height - this.xMarg + 22),
				(int) xAxisLabel.getPreferredSize().getWidth(), (int) xAxisLabel.getPreferredSize().getHeight());
		this.add(xAxisLabel);
		yAxisLabel.setBounds((int) this.yMarg / 3, (int) (height / 3 - 20),
				(int) yAxisLabel.getPreferredSize().getWidth(), (int) yAxisLabel.getPreferredSize().getHeight());
		this.add(yAxisLabel);

		pointLabel.setBackground(Color.WHITE);
		pointLabel.setOpaque(true);
		pointLabel.setBounds(pointLabelXValue, pointLabelYValue,
				(int) pointLabel.getPreferredSize().getWidth(),
				(int) pointLabel.getPreferredSize().getHeight());
		this.add(pointLabel);

		font = new Font("Serif", Font.PLAIN, 11);
		g.setFont(font);
		g.setColor(Color.BLACK);

		// Y axis
		g.drawLine((int) (yMarg), (int) (plottedYmax), (int) (yMarg), (int) (plottedYmin));

		// X axis
		g.drawLine((int) (yMarg), (int) (plottedYmin), (int) (plottedXmax), (int) (plottedYmin));

		// get number of x axis tick marks
		int numberOfYears = convertedX.length / list.size();
		
		int iDistance = (int) plottedXmin;
		int iDistanceIncrement =  (int) ((plottedXmax - plottedXmin) / numberOfYears);
		// draw tick marks
		for (int i = 0; i < numberOfYears; i++) {
			g.drawLine((int) (convertedX[i]), (int) (height - xMarg - 4), (int) (convertedX[i]), (int) (height - xMarg + 4));

			if (i % 5 == 0) { // Uncomment for large cellular data file
				g.drawString(Integer.toString((int) (xMin + i)), (int) (convertedX[i] - 15), (int) (height - xMarg + 20));
			}
			iDistance = iDistance + iDistanceIncrement;
		}

		// For Points
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int p = 0; p < numberOfYears; p++) {
				g.setColor(coloredPoint[counter].getColor());
				g.fillOval((int) ((int) (convertedX[counter] - 4)), (int) ((convertedY[counter] - 4)), 8, 8);
				counter++;
			}
		}

		// Border at the right hand side
		g.setColor(Color.GRAY);
		g.drawLine(width+5, 0, width+5, height);
	} // End of Paint ()

	/**
	 * Gets the country names that represent the points on the graph.
	 * 
	 * @return The country names
	 */
	public String[] getCountries() {
		return countryNames;
	}

	/**
	 * Gets the number of years the GraphView is holding
	 * 
	 * @return The number of years on the graph
	 */
	public int getNumberOfYears() {
		return convertedX.length / list.size();
	}
	
	/**
	 * Gets the linked list of countries that was originally taken as a parameter
	 * 
	 * @return	The linked list of countries
	 */
	public LinkedList<T> getList ()
	{
		return this.list;
	}

	/**
	 * Gets the colored point data for all the points on the graph.
	 * 
	 * @return The colored point
	 */
	public ColoredPoint[] getColoredPoint() {
		return coloredPoint;
	}
	
	/**
	 * Gets the year labels of the graph as an integer array for
	 * the get subscriptions button in the BottomPanel class
	 * 
	 * @return	Integer array of year labels
	 */
	public Integer[] getYearLabels() {
		Integer[] yearsList = new Integer[numberOfYears];;
		for (int i=0;i<numberOfYears;i++) {
			
			yearsList[i] = (int)originalX[i];
		}
		
		return yearsList;
	}

	/**
	 * Method we must override to implement MouseListener
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method we must override to implement MouseListener
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method we must override to implement MouseListener
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method we must override to implement MouseListener
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method we must override to implement MouseListener
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
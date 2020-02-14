package cellularData;

import java.awt.Color;
import java.awt.Point;

/**
 * Class ColoredPoint creates a point for the cellular graph with a given
 * color and position (in terms of pixels) on the graph.
 * 
 * @author Timothy Hsu, Zach Rooney
 *
 */
public class ColoredPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	private Color color;
	private double originalX;
	private double originalY;
	
	/**
	 * Constructor that initializes the colored point for the cellular graph.
	 * 
	 * @param color		The color of the point
	 * @param mappedX	The x position of the point on the graph in terms of pixels
	 * @param mappedY	The y position of the point on the graph in terms of pixels
	 * @param originalX	The x position of the point on the graph in terms of the actual cellular data
	 * @param originalY	The y position of the point on the graph in terms of the actual cellular data
	 */
	public ColoredPoint (Color color, double mappedX, double mappedY, double originalX, double originalY ) {
		super ((int)mappedX, (int)mappedY);
		this.color = color;
		this.originalX = originalX;
		this.originalY = originalY;		
	}
	
	/**
	 * Gets the color of the point
	 * 
	 * @return	The color of the point
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Gets the label of the colored point to be placed directly above that
	 * point on the graph. The label contains the cellular data year and the
	 * number of subscriptions rounded to the nearest hundredth.
	 * 
	 * @return	The cellular label
	 */
	public String getLabel () {
		String labelString = "";
		labelString = "(" + (int)this.originalX + ", ";
		labelString += String.format("%.2f", this.originalY);
		labelString += ")";
		return labelString;
	}
	
	/**
	 * toString representation of a colored point object
	 */
	public String toString (){
		String temp = "Colored Point" + "\nOriginal X: " + this.originalX + "\nOriginal Y: " + this.originalY + 
				"\nMapped X: " + this.getX() + "\nMapped Y: " + this.getY() + "\nColor: " + this.getColor();
		return temp;
		
	}
	
}

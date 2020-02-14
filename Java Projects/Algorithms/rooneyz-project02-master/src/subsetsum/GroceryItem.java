package subsetsum;

/**
 * One object of this class represents a grocery item. It holds a String name and Double price
 * Created by zachrooney on 1/15/17.
 */

public class GroceryItem {
    private double price;
    private String item;

/**
 * Constructor to create a grocery item. It sets the instance variables equal to the String and Double parameters.
 * @param newItem
 * @param newPrice
 */
    public GroceryItem (String newItem, double newPrice)
    {
        this.price = newPrice;
        this.item = newItem;
    }

    /**
     * Constructor for item only having a name. Default price set to 0.
     * @param newItem
     */
    public GroceryItem (String newItem)
    {
        this.price = 0;
        this.item = newItem;
    }
    
    /**
     * returns objects price
     * @return price
     */
    public double getPrice() {
    	return this.price;	
    }

    /**
     * Returns string representation of the object with name and price
     */
    @Override
    public String toString(){
        return this.item + " : " + this.price;
    }
}

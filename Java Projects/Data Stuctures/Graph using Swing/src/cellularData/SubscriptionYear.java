package cellularData;

/**
 * One object of this class represents a single subscription year with the year
 * and number of subscriptions for that year saved.
 * 
 * @author Zach Rooney
 *
 */
public class SubscriptionYear {

	private int year; // for the year for a subscription data
	private double subscriptions; // stores the number of subscription for a
									// specific year

	/**
	 * Constructor creates a new SubscriptionYear object and sets the year and
	 * subscription data from the parameters given.
	 * 
	 * @param yearIn
	 *            int representing the year value
	 * @param numSubscriptions
	 *            doulbe representing the subscription data for that year
	 */
	public SubscriptionYear(int yearIn, double numSubscriptions) {
		year = yearIn;
		subscriptions = numSubscriptions;
	}

	/**
	 * This method takes an int year as a parameter and sets it to the
	 * SubscriptionYear object's int year
	 * 
	 * @param addYear
	 *            int of year wanted add
	 */
	public void setYear(int addYear) {
		year = addYear;
	}

	/**
	 * This method return's the objects int year
	 * 
	 * @return int year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * This method takes an double setSub as a parameter and sets it to the
	 * SubscriptionYear object's double subscriptions
	 * 
	 * @param setSub
	 *            double of subscription data
	 */
	public void setSubscription(double setSub) {
		subscriptions = setSub;

	}

	/**
	 * This method returns the subscription data
	 * 
	 * @return subscriptions double of subscription data for a year
	 */
	public double getSubscription() {
		return subscriptions;
	}

	/**
	 * This method returns a string value of the number of subscriptions
	 */
	public String toString() {
		return Double.toString(subscriptions);
	}

	/**
	 * 
	 * This method compares two objects of SubscriptionYear to see if they are
	 * equal. Returns 'true' or 'false'.
	 */
	@Override
	public boolean equals(Object tempSubscription) {
		if (tempSubscription instanceof SubscriptionYear) {
			if ((this.year == ((SubscriptionYear) tempSubscription).getYear())
					&& (this.subscriptions == ((SubscriptionYear) tempSubscription).getSubscription())) {
				return true;
			}
		}
		return false;
	}

}

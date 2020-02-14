package cellularData;

import java.util.Iterator;

/**
 * One object of this class represents a single country. Each country has a
 * string name and an array of SubscriptionYear that holds all the years and
 * cellular data for each year.
 * 
 * @author Zach Rooney
 *
 */
public class Country {
	private String name; // stores the name of the country
	private LinkedList<SubscriptionYear> subscriptions;
	private int minYear = 9999999;
	private int maxYear = 0;

	/**
	 * Constructor creates a new country object and uses parameters to set a
	 * string name for the country's name. Sets subscriptions to null.
	 * 
	 * @param countryName
	 *            String name of country
	 */
	public Country(String countryName) {
		this.name = countryName;
		subscriptions = new LinkedList<SubscriptionYear>();
	}

	/**
	 * This method returns the total subscription data for a range of years.
	 * Range of years is sent in as int. parameters.
	 * 
	 * @param startYear
	 *            Starting data year wanted
	 * @param endYear
	 *            Ending data year wanted
	 * @return Returns total of subscription data for the time frame
	 * @throws IllegalArgumentException
	 */
	public double getNumSubscriptionsForPeriod(int startYear, int endYear) throws IllegalArgumentException {
		if (startYear > endYear) {
			throw new IllegalArgumentException();
		}
		if (endYear < minYear || startYear > maxYear) {
			throw new IllegalArgumentException();
		}
		if (endYear > maxYear) {
			endYear = maxYear;
			System.out.println("Ending Year entered is greater than last year in list. Showing results from: "
					+ startYear + "-" + maxYear);
		}
		if (startYear < minYear) {
			startYear = minYear;
			System.out.println("Starting Year entered is less than first year in list. Showing results from: " + minYear
					+ "-" + endYear);
		}
		Iterator<SubscriptionYear> itr = subscriptions.iterator();
		int counter = 0;
		double subscriptionTotal = 0;
		// System.out.println("minYear : " + minYear); //For Debugging
		// System.out.println("maxYear : " + maxYear); //For Debugging
		// System.out.println("Start Year : " + startYear); //For Debugging
		// System.out.println("End Year : " + endYear); //For Debugging
		while (itr.hasNext()) {
			// System.out.println(counter); // For Debugging
			// System.out.println(subscriptions.getIndex(counter).getSubscription());
			// // For Debugging
			// System.out.println("Current Year: " + currentYear); // For
			// Debugging
			SubscriptionYear element = itr.next();
			int currentYear = element.getYear();
			if (startYear <= currentYear && currentYear <= endYear) {
				subscriptionTotal += subscriptions.getIndex(counter).getSubscription();
				// System.out.println("Increasing Total: " + subscriptionTotal);
				// // For Debugging
			}
			counter++;
		}
		return subscriptionTotal;
	}

	/**
	 * This method will add a subscription year and its data to the
	 * SubscriptionYear object (array of years and data)
	 * 
	 * @param addYear
	 *            //Year to be added
	 * @param data
	 *            //Data for the year to be added
	 */
	public void addSubscriptionYear(int addYear, double data) {
		// System.out.println(addYear + "," + data); //For Debugging
		SubscriptionYear newYear = new SubscriptionYear(addYear, data);
		this.subscriptions.add(newYear);
		if (newYear.getYear() > maxYear) {
			maxYear = newYear.getYear();
		}
		if (newYear.getYear() < minYear) {
			minYear = newYear.getYear();
		}
	}

	/**
	 * This method returns a string representation of the country name and all
	 * the year and data info
	 * 
	 * returns allCountryData // the string representation
	 */
	public String toString() {
		// System.out.println(this.name + " Number of Subscriptions: " +
		// subscriptions.size()); //For Debugging
		// System.out.println(subscriptions); //For Debugging
		String allCountryData = "";
		allCountryData = name + " ";
		for (SubscriptionYear e : subscriptions) {
			// System.out.println("In Enhanced For Loop"); //For Debugging
			allCountryData += e.getYear() + " : " + e.getSubscription() + " | ";
		}
		return allCountryData;
	}

	/**
	 * This method returns the name of the country in string format
	 * 
	 * @return name // string name of country
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method overrides the generic equals method. It compares the name of
	 * two country objects to see if they are the same. It returns 'true' if
	 * they are and 'false' if they aren't.
	 */
	@Override
	public boolean equals(Object tempCountry) {
		if (tempCountry instanceof Country) {
			if (this.name.toUpperCase().equals(((Country) tempCountry).getName().toUpperCase())) {
				return true;
			}
		}
		return false;
	}

}

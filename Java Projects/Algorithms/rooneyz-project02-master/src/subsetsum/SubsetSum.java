package subsetsum;

import java.util.ArrayList;
import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * One object of this class stores a sum, a 'master' static array, and array of
 * indices referring to the static array. It represent one possible combination
 * of items from the master array. This class is designed to use an algorithm to
 * find the best combination for the user. It has a method for searching a
 * grocery list and a library of songs.
 * 
 * @author zachrooney
 *
 */
public class SubsetSum {
	private double sum;
	private static ArrayList<Double> storeItems;
	private static ArrayList<SongEntry> masterSong;
	private ArrayList<Integer> indecies = new ArrayList<>();

	/**
	 * Default Constructor. Sets sum to 0.
	 */
	public SubsetSum() {
		this.sum = 0;
	}

	/**
	 * Constructor that takes an array list of integers for the indices and a
	 * double totaling the sum. It sets them to the instance variables.
	 * 
	 * @param newReference
	 * @param newSum
	 */
	public SubsetSum(ArrayList<Integer> newReference, Double newSum) {
		this.indecies = newReference;
		this.sum = newSum;
	}

	/**
	 * Returns the double sum
	 * 
	 * @return sum
	 */
	private double getSum() {
		return sum;
	}

	/**
	 * Returns the array list of integer references
	 * 
	 * @return indecies
	 */
	private ArrayList<Integer> getIndexArray() {
		return indecies;
	}

	/**
	 * Designed to take a list of grocery item prices and a budget. It then
	 * finds the first combination of items that equal the budget and returns
	 * them as a list. If the budget isn't met exactly it will find the first
	 * closest combination of items without going over the budget and return it.
	 * 
	 * @param shoppingList
	 * @param budget
	 * @return returnList
	 */
	public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget) {
		double shoppingListTotal = 0;
		double lowest = 99999;

		for (double x : shoppingList) {
			if (x < lowest) {
				lowest = x;
			}
			shoppingListTotal = shoppingListTotal + x;
		}
		if (shoppingListTotal <= budget) {
			System.out.println("You can buy everything on your list!");
			return shoppingList;
		}

		if (budget < lowest) {
			System.out.println("You don't have enough money to buy anything at the store");
			ArrayList<Double> noMoney = new ArrayList<>();
			return noMoney;
		}

		storeItems = shoppingList;
		ArrayList<SubsetSum> allCombinations = new ArrayList<SubsetSum>();
		SubsetSum first = new SubsetSum();
		allCombinations.add(first);
		SubsetSum closest = null;
		double check = 0;

		for (int j = 0; j < shoppingList.size(); j++) {
			int tempSize = allCombinations.size();
			for (int i = 0; i < tempSize; i++) {
				ArrayList<Integer> placeHolder = allCombinations.get(i).getIndexArray();
				ArrayList<Integer> tempList = (ArrayList<Integer>) placeHolder.clone();
				double newSum = allCombinations.get(i).getSum();
				newSum = newSum + shoppingList.get(j);
				tempList.add(j);
				SubsetSum newCombination = new SubsetSum(tempList, newSum);
				if (newSum == budget) {
					ArrayList<Double> returnList = new ArrayList<>();
					for (int d : tempList) {
						returnList.add(shoppingList.get(d));
					}
					System.out.println("Found an exact match for your budget!");
					return returnList;
				} else if (newSum < budget) {
					if (newSum > check) {
						check = newSum;
						closest = newCombination;
					}
					allCombinations.add(newCombination);
				}
			}
		}
		ArrayList<Double> returnList = new ArrayList<>();
		ArrayList<Integer> closestList = closest.getIndexArray();
		for (int d : closestList) {
			returnList.add(shoppingList.get(d));
		}
		System.out.println("Can't use your whole budget but here is the list closest to it" + " with sum of : "
				+ closest.getSum());
		return returnList;
	}

	/**
	 * Designed to take a list of songs and a desired play list duratio.. It
	 * then finds the first combination of songs that equal the duration and
	 * returns them as a list. If the duration isn't met exactly it will find
	 * the first closest combination of songs without going over the duration
	 * and return them. It will try 1 million possibilities without and exact
	 * match, if 1 million is hit then the closest found combination will be
	 * returned.
	 * 
	 * @param songList
	 * @param duration
	 * @return returnList
	 */
	public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration) {

		int durationListTotal = 0;
		double lowest = 99999;

		for (SongEntry x : songList) {
			if (x.getDuration() < lowest) {
				lowest = x.getDuration();
			}
			durationListTotal = durationListTotal + x.getDuration();
		}
		if (durationListTotal < duration) {
			System.out.println(" Your duration is too large. The total duration of all the songs is 1.4892082E7. (sec)"
					+ "\n Please enter something reasonable");
			return null;
		}

		if (duration < lowest) {
			System.out.println("There is no song shorter than " + lowest + "(sec)");
			return null;
		}

		masterSong = songList;
		ArrayList<SubsetSum> allCombinations = new ArrayList<SubsetSum>();
		SubsetSum first = new SubsetSum();
		allCombinations.add(first);
		SubsetSum closest = null;
		double check = 0;

		for (int j = 0; j < masterSong.size(); j++) {
			int nestedLoopSize = allCombinations.size();
			for (int i = 0; i < nestedLoopSize; i++) {
				ArrayList<Integer> placeHolder = allCombinations.get(i).getIndexArray();
				ArrayList<Integer> tempList = (ArrayList<Integer>) placeHolder.clone();
				double newSum = allCombinations.get(i).getSum();
				newSum = newSum + masterSong.get(j).getDuration();
				tempList.add(j);
				SubsetSum newCombination = new SubsetSum(tempList, newSum);
				if (newSum == duration) {
					ArrayList<SongEntry> returnList = new ArrayList<>();
					for (int d : tempList) {
						returnList.add(masterSong.get(d));
					}
					System.out.println("We Found a Perfect Playlist!! Duration(sec): " + newSum + "\nDuration(hrs): "
							+ TimeConverter.convertTimeToString((int) newSum));
					return returnList;
				} else if (newSum < duration) {
					if (newSum > check) {
						check = newSum;
						closest = newCombination;
					}
					allCombinations.add(newCombination);
				}

				if (i > 1000000) {
					ArrayList<SongEntry> returnList = new ArrayList<>();
					ArrayList<Integer> closestList = closest.getIndexArray();
					for (int d : closestList) {
						returnList.add(masterSong.get(d));
					}
					System.out.println("WE TRIED 1 MILLION POSSIBLE PLAYISTS AND THIS IS THE"
							+ "CLOSEST THAT WE FOUND: \n Total Duration: "
							+ TimeConverter.convertTimeToString((int) closest.getSum())); // closest.getSum());
					return returnList;
				}
			}

		}
		ArrayList<SongEntry> returnList = new ArrayList<>();
		ArrayList<Integer> closestList = closest.getIndexArray();
		for (int d : closestList) {
			returnList.add(masterSong.get(d));
		}
		System.out
				.println("PERFECT MATCH NOT FOUND. HERE IS THE CLOSEST TO IT: \n Total Duration: " + closest.getSum());
		return returnList;

	}

	/**
	 * Returns string representation of the object
	 */
	@Override
	public String toString() {
		return "Master List: " + this.storeItems + this.masterSong + "\n Index List: " + this.indecies + "\n Total: "
				+ sum;
	}

}

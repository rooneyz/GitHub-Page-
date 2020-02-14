package cellularData;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class holds and manages a linked list of Node<T> objects
 * 
 * @author Zach Rooney
 *
 */
public class LinkedList<T> implements Iterable<T> {

	private Node<T> head; // Node<T> reference that point to the start of the
							// linked list
	private int listLength = 0; // keeps track of the length of the linked list

	/**
	 * Private helper class ListIterator implements iterator<T>. It defines how to iterate over a
	 * LinkedList object.
	 * 
	 * @author Zach Rooney
	 */
	private class ListIterator implements Iterator<T> {
		private Node<T> current;

		/**
		 * Constructor for ListIterator sets Node<T> reference to Node<T> head
		 * (beginning of the LinkedList)
		 */
		public ListIterator() {
			current = head;
		}

		/**
		 * This method returns 'true' if there is another object in LinkedList
		 * or 'false' if there is not.
		 */
		public boolean hasNext() {
			if (current == null) {
				return false;
			}
			return true;
		}

		/**
		 * This method moves the iterator to the next element in the LinkedList
		 * and returns the generic object <T> of the current element.
		 */
		public T next() throws NoSuchElementException {
			if (current == null) {
				throw new java.util.NoSuchElementException();
			}
			T data = current.getData();
			current = current.getNext();
			return data;
		}
	}

	/**
	 * This method creates and returns a new instance of ListIterator.
	 */
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	/**
	 * Constructs a new LinkedList object. Sets new LinkedList object's Node
	 * <T> head to null and listLength to 0.
	 */
	public LinkedList() {
		this.head = null;
		this.listLength = 0;
	}

	/**
	 * This method adds takes a generic Object as a parameter and adds it to the
	 * end of the CountyList linked list. It takes the generic Object and
	 * creates a new Node<T> object with it in order to add it to the linked
	 * list of Node<T>s.
	 * 
	 * @param Country
	 *            countryToAdd // a new generic Object to be added to the list
	 */
	public void add(T newObject) {
		Node<T> current = new Node<T>(newObject);
		// System.out.println("Made new Node"); //For Debugging
		if (this.listLength == 0) {
			head = current;
			this.listLength++;
		} else {
			Node<T> walker = head;
			while (walker.getNext() != null) {
				walker = walker.getNext();
			}
			walker.setNext(current);
			this.listLength++;
		}
	}

	/**
	 * This method will take an int "indexNumber" and a generic Object
	 * "countryToAdd" as a parameters, creates a new Node<T> object with
	 * "countryToAdd", and adds the new Node<T> object to the specific index
	 * number in the linked list (LinkedList).
	 * 
	 * @param Country
	 *            countryToAdd // Country to add to linked list
	 * @param int
	 *            indexNumber // index number of LinkedList to add new Country
	 *            to.
	 * @throws IndexOutOfBoundsException
	 *             // thrown if indexNumber < 0
	 */
	public void insertCountryAtIndex(T objectToAdd, int indexNumber) throws IndexOutOfBoundsException {
		if (indexNumber < 0) {
			throw new IndexOutOfBoundsException();
		} else if (indexNumber > (this.listLength + 1)) {
			indexNumber = this.listLength;
		}
		Node<T> addNode = new Node<T>(objectToAdd);
		Node<T> walker = head;
		for (int i = 0; i < (indexNumber - 1); i++) {
			walker = walker.getNext();
		}
		addNode.setNext(walker.getNext());
		walker.setNext(addNode);
		this.listLength++;
	}

	/**
	 * This method takes and int index as a parameter and returns the Country
	 * stored at that index in LinkedList.
	 * 
	 * @param index
	 * @return Country walker.getCountry() // returns generic Object found at
	 *         index specified.
	 */
	public T getIndex(int index) {
		Node<T> walker = head;
		for (int i = 0; i < index; i++) {
			walker = walker.getNext();
		}
		return walker.getData();
	}

	/**
	 * This method returns the size of LinkedList. (The amount of Countries
	 * stored in the linked list)
	 * 
	 * @return int this.listLength // size of linked list
	 */
	public int size() {
		return this.listLength;
	}

	/**
	 * This method takes generic Object "tmpCountry" as a parameter and searches
	 * the linked list to see if there is a Country with a matching name stored
	 * in the list. If found it will return the Country and it's data. If it is
	 * not found it will return null.
	 * 
	 * @param T
	 *            tmpObject // generic Object searching for
	 * @return Country walker.getData() or null // will return generic Object if
	 *         found else returns null
	 */
	public T contains(T tmpObject) {
		Node<T> walker = head;
		while (walker != null) {
			if (walker.getData().equals(tmpObject)) {
				return walker.getData();
			}
			walker = walker.getNext();
		}
		return null;
	}

	/**
	 * This method returns a string concatenation of all the Country names
	 * stored in the linked list
	 * 
	 * @return String allCountries //string concatenation of all the Country
	 *         names stored in the linked list
	 */
	public String toString() {
		Node<T> walker = head;
		String allCountries = "";
		while (walker != null) {
			// System.out.println(walker.getCountry().getName()); //For Testing
			allCountries += walker.toString() + " \n ";
			walker = walker.getNext();
		}
		return allCountries;
	}
}

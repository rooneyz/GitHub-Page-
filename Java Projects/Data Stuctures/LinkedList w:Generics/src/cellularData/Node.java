package cellularData;

/**
 * This class creates CountryNode objects. Each object has a generic variable
 * <T> named 'data' and a parameterized variable "next". These are used in
 * constructing a linked list.
 * 
 * @author Zach Rooney
 *
 */
public class Node<T> {

	private T data; // Type of object
	private Node<T> next; // reference used for constructing a linked list. It
							// points to next CountryNode in the linked list

	/**
	 * Constructs a Node object with only a generic parameter. It sets this.data
	 * to the object T parameter "newData" and this.next to null.
	 * 
	 * @param newData
	 *            // Object to be set to data
	 */
	public Node(T newData) {
		this.data = newData;
		this.next = null;
	}

	/**
	 * Constructs a Country node object with a Country object and CountryNode
	 * object as parameters. It sets this.country to the Country parameter
	 * "name" and this.next to the CountryNode parameter "node".
	 * 
	 * @param name
	 *            //CountryNode object to be set as this.next
	 * @param node
	 *            //Country object to be set as this.country
	 */
	public Node(T newData, Node<T> newNextNode) {
		this.data = newData;
		this.next = newNextNode;
	}

	/**
	 * This method sets the "next" CountryNode reference to the parameter
	 * CountryNode "setNode"
	 * 
	 * @param setNode
	 *            // Node to be set to "next"
	 */
	public void setNext(Node<T> setNode) {
		this.next = setNode;
	}

	/**
	 * This method returns the CountryNode "next"
	 * 
	 * @return CountryNode next
	 */
	public Node<T> getNext() {
		return this.next;
	}

	/**
	 * This method returns generic object held in node.
	 * 
	 * @return
	 * @return Country country
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * This returns a string version of generic variable 'data'
	 * 
	 * @return data.toString() // Returns string representation of generic
	 *         variable 'data'
	 */
	public String toString() {
		return data.toString();
	}

}

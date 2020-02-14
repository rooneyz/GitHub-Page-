package cellularData;

public class PersonalTest {
	public static void main(String[] args) {
		Country c1 = new Country("Country A");
		Country c2 = new Country("Country B");
		Country c3 = new Country("Country C");

		LinkedList<Country> personalTest = new LinkedList<Country>();
		personalTest.add(c1);
		personalTest.add(c2);
		personalTest.add(c3);

		System.out.println(personalTest);

	}

}

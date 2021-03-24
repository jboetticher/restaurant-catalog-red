import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Stream;
import java.io.FileReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class contains a set of tests for the front end of the Movie Mapper project.
 */
public class BacnEndDeveloperTests {
	
	/*public static void main(String[] args) {
		(new FrontEndDeveloperTests()).runTests();
	}*/
	
	/**
	 * This method calls all of the test methods in the class and ouputs pass / fail
	 * for each test.
	 */

	/*
	public void runTests() {
		//System.out.print("Test enter 'x' to exit (WARNING: if 'x' does not exit app, test won't exit and run indefinitely!): ");
		if (this.testExitApp()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		
	}*/

	
    /**
	 * This class tests out whether the Backend getTopRestaurants() method works.
	 */
	@Test
	public void testGetTopRestaurants() {
		try {
			BackendInterface book = new Backend(new FileReader("Independence100.csv"));
			int count = 0;

			book.getTopRestaurants(10).filter(b -> b.getRank() <= 10).forEach(
					a -> count++);
			assertEquals(10, count);
			//if (count == 10) return true;
			//else return false;

		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			//return false;
			fail("Exception thrown");
		}
	}

	/**
	 * This test checks if the getRestaurantByState() method works with "D.C." as an example 
	 */

	@Test
	public void testGetRestaurantsByState() {
		try {
			BackendInterface book = new Backend(new FileReader("Independence100.csv"));
			int count = book.getRestaurantsByState("D.C.").count();
				//.forEach(
				//	a -> count++);
			assertEquals(9, count);
			//if (count == 10) return true;
			//else return false;

		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			//return false;
			fail("Exception thrown");
		}
	}

	/**
	 * This test checks if the getAllRestaurantNames() method works properly 
	 */
	@Test
	public void testGetAllRestaurantNames() {
		try {
			BackendInterface book = new Backend(new FileReader("Independence100.csv"));
			
			int count = book.getAllRestaurantNames().count();
			


			assertEquals(100, count);
			//if (count == 10) return true;
			//else return false;

		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			//return false;
			fail("Exception thrown");
		}
	}
	
	// TODO: Front End Developer, add at least 2 more tests
	/**
	 * This test checks to see if the getNumMealsServed method works
	 */
	@Test
	public void testGetNumMealsServed() {
		try {
			BackendInterface book = new Backend(new FileReader("Independence100.csv"));
			
			int count = book.getNumMealsServed().count();

			assertEquals(100, count);
			//if (count == 10) return true;
			//else return false;

		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			//return false;
			fail("Exception thrown");
		}	
	}

/**
	 * This test checks to see if the getAllNumSales() method works
	 */
	@Test
	public void testGetAllNumSales() {
		try {
			BackendInterface book = new Backend(new FileReader("Independence100.csv"));
			
			int count = book.getAllNumSales().count();

			assertEquals(100, count);
			//if (count == 10) return true;
			//else return false;

		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			//return false;
			fail("Exception thrown");
		}	
	}

}


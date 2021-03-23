import java.io.StringReader;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

//--== CS400 File Header Information ==--
//Name: <Kenneth Diao>
//Email: <kdiao2@wisc.edu>
//Team: <Red>
//Group: <KF>
//TA: <Keren Chen>
//Lecturer: <Gary Dahl>
//Notes to Grader: I'm using an abbreviated version here because to modify the entire String to fit with java's conventions would be far too tedious
// another note: I have tested the reader itself outside junit, but I have run out of time to convert it into junit

public class DataWranglerTests {
    DataReader reader;
    List<RestaurantInterface> test;
    @BeforeEach
      public void setup() {
	try{
		DataReader reader = new DataReader();
		StringReader inputStringReader = new StringReader("\"Rank\",\"Restaurant\",\"Sales\",\"Average Check\",\"City\",\"State\",\"Meals Served\"" + "\n" +
				"1,\"Carmine's (Times Square)\",39080335,40,\"New York\",\"N.Y.\",469803" + "\n" +
				"2,\"The Boathouse Orlando\",35218364,43,\"Orlando\",\"Fla.\",820819" + "\n" +
				"3,\"Old Ebbitt Grill\",29104017,33,\"Washington\",\"D.C.\",892830" + "\n" +
				(char)(65535) + (char)(-1));
		test = reader.readDataSet(inputStringReader);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void filler() {}
    /**
    * test testGetRestaurantName
    * tests the getRestaurantName function on three objects’ names from the list
    * succeeds if all three names match and fails otherwise
    */
    
    @Test
    public void testGetRestaurantName() {
	    try {
	        String name1 = "Carmine's (Times Square)";
	        String name2 = "The Boathouse Orlando";
	        String name3 = "Old Ebbitt Grill";
	        assertTrue(name1.equals(test.get(0).getRestaurantName()) && name2.equals(test.get(1).getRestaurantName()) && name3.equals(test.get(2).getRestaurantName()));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
    }

    /**
    * test testGetNumSales
    * tests the getNumSales function on three objects’ names from the list
    * succeeds if all three sales quantities match and fails otherwise
    */
    @Test
    public void testGetNumSales() {
	
	try {
	    Integer sale1 = 39080335;
	    Integer sale2 = 35218364;
	    Integer sale3 = 29104017;
	    assertTrue(sale1.equals(test.get(0).getNumSales()) && sale2.equals(test.get(1).getNumSales())
		   && sale3.equals(test.get(2).getNumSales()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
    * test testGetCity
    * tests the getCity function on three objects’ names from the list
    * succeeds if all three city names match and fails otherwise
    */
    @Test
    public void testGetCity() {
	try{
	    String city1 = "New York";
	    String city2 = "Orlando";
	    String city3 = "Washington";
	    assertTrue(city1.equals(test.get(0).getCity()) && city2.equals(test.get(1).getCity()) && city3.equals(test.get(2).getCity()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
    * test testGetState
    * tests the getState function on three objects’ names from the list
    * succeeds if all three state names match and fails otherwise
    */
    @Test
    public void testGetState() {
	try{
	    String state1 = "N.Y.";
	    String state2 = "Fla.";
	    String state3 = "D.C.";
	    assertTrue(state1.equals(test.get(0).getState()) && state2.equals(test.get(1).getState()) && state3.equals(test.get(2).getState()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
    * test testNumMealsServed
    * tests the numMealsServed function on three objects’ names from the list
    * succeeds if all three meals served quantities match and fails otherwise
    */
    @Test
    public void testNumMealsServed() {
	try {
	    Integer meals1 = 469803;
	    Integer meals2 = 820819;
	    Integer meals3 = 892830;
	    assertTrue(meals1.equals(test.get(0).numMealsServed()) && meals2.equals(test.get(1).numMealsServed())
		       && meals3.equals(test.get(2).numMealsServed()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
    * test testGetAverageCheck
    * tests the getAverageCheck function on three objects’ names from the list
    * succeeds if all three average checks match and fails otherwise
    */
    @Test
    public void testGetAverageCheck() {
	try {
	    Integer check1 = 40;
	    Integer check2 = 43;
	    Integer check3 = 33;
	    assertTrue(check1.equals(test.get(0).getAvgCheck()) && check2.equals(test.get(1).getAvgCheck())
		   && check3.equals(test.get(2).getAvgCheck()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
    * test testCompareTo
    * tests the compareTo function on three objects’ names from the list
    * succeeds if the compareTo function correctly compares the ranks of the objects and returns 
    * the proper corresponding number and fails otherwise
    */
    @Test
    public void testCompareTo() {
	try {
	    assertTrue(test.get(0).compareTo(test.get(2)) > 0);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

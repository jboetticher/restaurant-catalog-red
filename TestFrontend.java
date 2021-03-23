

// --== CS400 File Header Information ==--
// Name: Jaan Ots
// Email: jots@wisc.edu
// Team: purple
// Role: Frontend Developer
// TA: KF
// Lecturer: Gary Dahl
// Notes to Grader: 


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



//Testing the frontend of the restaurant project
public class TestFrontend {
	
	//main method to run tests
	public static void main(String[] args) {
		(new TestFrontend()).runTests();
	}
	
//runs the tests
	public void runTests() {
		System.out.print("Test enter 'q' to exit : ");
		if (this.enterQToExit()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print("Test frontend initially lists no restaurants: ");
		if (this.testFrontendInitialOutputsNothing()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}

		
		
	}
	
//tests that when 'q' entered program quits/ends
	public boolean enterQToExit() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an x to test of the program exists)
			String input = "q";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = new FrontEndDeveloper(); 

			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			if (frontend == null) {
				// test fails
				return false;
			} else {
				// test passed
				return true;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

//tests that when the program starts it doesn't output info.
	public boolean testFrontendInitialOutputsNothing() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an x to test of the program exists)
			String input = "q";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = new FrontEndDeveloper(); 

			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			if (frontend == null) {
				// test failed
				return false;
			} else {
				// test passed
				return true;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	//public boolean testFrontendInitialOutputsNothing() {	
	//}
	
	
	//tests the functionality of the buttons

}

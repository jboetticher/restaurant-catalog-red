
// --== CS400 File Header Information ==--
// Name: Jaan Ots
// Email: jots@wisc.edu
// Team: purple
// Role: Frontend Developer
// TA: KF
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.util.Scanner;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class FrontEndDeveloper {

	// Method that displays ONLY the TEXT of the interactive menu
	public static void homeScreen() {
		System.out.println("HOME SCREEN");
		System.out.println("Press \"r\" for the Rank Screen to view the whole list");
		System.out.println("Press \"m\" for the Map Screen to check locations");
		System.out.println("Press \"i\" for the individual restaurant Information");
		System.out.println("Press \"q\" to Quit Program\n");
		System.out.println("Enter one of the above options then press <ENTER>");
		System.out.print("Enter  option:");
	}

	// Method for the Rank screen---------------------------------------------------
	// arguments are the scanner object for button functionality and the linked List
	// of restaurant objects
	private static char rankScreen(Scanner scan, Backend book) {

		// List all Restaurants by Rank - Press "l"
		System.out.println("press \"l\" to List ALL Restaurant by rank");
		System.out.println("press \"s\" for a short list of the top 10 rated restaurants ");
		System.out.println("press \"h\" to return to the Home screen");
		System.out.println("Press \"q\" to Quit Program");
		System.out.println("Enter one of the above options then press <ENTER>");
		System.out.print("Enter  option:");

		// converts linked-list to stream with the .steam method and creates a stream
		// object
		// Stream<RestaurantInterface> printRanking = book.stream();

		// TODO: make sure get top restaurants works
		Stream<RestaurantInterface> printRanking = book.getTopRestaurants().stream();

		// gets input from user
		char localButton = scan.next().charAt(0);

		// displays all restaurants if "l" entered
		if (localButton == 'l') {
			// prints rank and name using stream object
			printRanking.forEach(
					a -> System.out.println("Rank: " + a.getRank() + "  -- restaurant Name: " + a.getRestaurantName()));
			return localButton;
		}

		// short list if "S" entered
		if (localButton == 's') {
			System.out.println("Top ten ranked restaurant");

			printRanking.filter(b -> b.getRank() <= 10).forEach(
					a -> System.out.println("Rank: " + a.getRank() + "  -- restaurant Name: " + a.getRestaurantName()));
			return localButton;
		}
		// quit program
		if (localButton == 'q') {
			return localButton;
		}

		// return to the home screen
		if (localButton == 'h') {
			System.out.println("");
			return localButton;
		} else {
			return 'x';
		}
	}

	// Method for the Map Screen---------------------------------------------------
	// arguments are the scanner object for button functionality and the linked List
	// of restaurant objects
	private static char mapScreen(Scanner scan, Backend book) {

		System.out.println(
				"press \"l\" to displays ALL the states that have restaurants in the top 100 ranking to see if your state has one of the top 100 places to eat!");
		System.out.println("press \"c\" to display ALL the cities that have restaurants in the top 100 ranking.");
		System.out.println("press \"s\" to display ALL restaurants by state");
		System.out.println("press \"h\" to return to the Home screen");
		System.out.println("Press \"q\" to Quit Program");
		System.out.println("Enter one of the above options then press <ENTER>");
		System.out.print("Enter  option:");

		// gets input from user
		char localButton = scan.next().charAt(0);

		// displays the states that have restaurants in the top 100
		if (localButton == 'l') {
			book.getAllStates().stream().distinct().forEach(b -> System.out.println(b));
			return 'l';
		}

		// displays the cities that have restaurants in the top 100
		else if (localButton == 'c') {
			book.getAllCities().stream().distinct().forEach(b -> System.out.println(b));
		}

		// enter a state to display list of restaurant in that state
		else if (localButton == 's') {

			//TODO: make sure that this function works & finish the rest of the function
			List<List<RestaurantInterface>> res = book.getRestaurantsByState();

			printStates.map(a -> a.getState()) // gets state and turns it into a string
					.distinct().forEach(b -> listOfStates.add(b));

			while (listOfStates.size() > 0) {
				String currentState = listOfStates.pop();
				System.out.println("\nRestaurants in: \"" + currentState + "\n listed below:");
				for (int i = 0; i < book.size(); i++) {
					String check = book.get(i).getState();
					if (currentState.equals(check)) {
						System.out.println(book.get(i).getRestaurantName() + " -- Rank: " + book.get(i).getRank());
					}
				}
			}
			return 's';
		}

		// quit program
		else if (localButton == 'q') {
			return localButton;
		}

		// return to the home screen
		else if (localButton == 'h') {
			System.out.println("");
		} else {
			return 'x';
		}
	}

	// Method for the info Screen---------------------------------------------------
	// arguments are the scanner object for button functionality and the linked List
	// of restaurant objects
	private static char infoScreen(Scanner scan, Backend book) {

		// info screen functionality
		System.out.println("Press \"n\" to display the information on a specific restaurant");
		System.out.println("press \"h\" to return to the Home screen");
		System.out.println("Press \"q\" to Quit Program");
		System.out.println("Enter one of the above options then press <ENTER>");
		System.out.print("Enter option:");

		// gets input from user
		char localButton = scan.next().charAt(0);

		// if the 'i' button is pressed, user must enter a number between (1-100)
		if (localButton == 'n') {

			// user enters a rank to get restaurant info.
			System.out.println(
					"\nEnter a rank number of a restaurant to display all the information we have on that establishment");
			System.out.println("Enter a rank number -only one digit between 1 and 100-");
			System.out.print("Enter rank number:");

			int numButton = 0;
			try {
				numButton = scan.nextInt();
				if (numButton >= 1 && numButton <= 100) {
					// have to subtract 1 to get the right rank, since it's indexed from 0.
					// TODO: Make sure that getting the restaurant like this works
					RestaurantInterface res = book.getRestaurant(numButton - 1);

					// prints information
					System.out.println("\nInformation for Restaurant Ranked Number: " + numButton);
					System.out.println("   Restaurant Name: \t\t " + res.getRestaurantName());
					System.out.println("   Location: \t\t\t City: " + res.getCity() + "\t\tState: "
							+ res.getState());
					System.out.println("   Total Sales in (Dollars): \t $" + res.getNumSales());
					System.out.println("   Total Number of Meals Served: " + res.numMealsServed());
					System.out.println("   Average cost Per meal: \t $" + res.getAvgCheck());
				} else
					throw new Exception("Not a valid rank");

			} catch(InputMismatchException e) {
				System.out.println("NOT A VALID COMMAND, PLEASE ENTER A NUMBER");
				return 'x';
			} catch(Exception e) {
				System.out.println("NO SUCH RESTAURANT OF RANK " + numButton);
				return 'x';
			}

			return localButton;
		}

		// quit program
		if (localButton == 'q') {
			return localButton;
		}

		// return to the home screen
		if (localButton == 'h') {
			System.out.println("");
			return localButton;
		} else {
			return 'x';
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, DataFormatException {

		// CREATING READER OBJECT AND INPUTING CSV FILE
		Backend book = new Backend(new FileReader("Independence100.csv"));
		// DataReader glasses = new DataReader();
		// List<RestaurantInterface> Book = glasses.readDataSet(new
		// FileReader("Independence100.csv"));

		// Below is the code that manages the Button Interaction to switch through
		// screens*---------------------------------------------------

		// scanner object for the button
		Scanner scnr = new Scanner(System.in);
		char userButton = 'x'; // menu gate button

		// greeting
		System.out.println("WELCOME TO THE TOP 100 RESTAURANT DIRECTORY!!");
		System.out.println("You will be able to view information on the top ranking restaurants in the US\n");

		// displays home menu to user
		homeScreen();

		// get userButton input from user
		userButton = scnr.next().charAt(0);

		// User functions behind Menu ("q" to quit menu)
		while (userButton != 'q') {

			// Rank Screen********************************************
			if (userButton == 'r') {

				System.out.println("\nRANK SCREEN");
				// runs the rankScreen method
				userButton = rankScreen(scnr, book);

				// returns user back to screen
				if (userButton == 'l' || userButton == 's') {
					userButton = 'r';
					continue;
				}
				continue; // starts at the beginning of the while loop
			}

			// Map Screen**********************************************
			if (userButton == 'm') {

				System.out.println("\nMAP SCREEN");
				// runs the map screen method
				userButton = mapScreen(scnr, book);

				// returns user back to screen
				if (userButton == 'l' || userButton == 's') {
					userButton = 'm';
					continue;
				}
				continue; // starts at the beginning of the while loop
			}
			// return to the info Screen********************************
			if (userButton == 'i') {

				System.out.println("\nINFORMOATION SCREEN");
				// runs the infoScreen Method
				userButton = infoScreen(scnr, book);

				// returns user back to screen
				if (userButton == 'n') {
					;
					userButton = 'i';
					continue;
				}
				continue; // starts at the beginning of the while loop
			}

			// return to the home Screen********************************
			if (userButton == 'h') {

				homeScreen();
				userButton = scnr.next().charAt(0);
				continue; // starts at the beginning of the while loop
			}

			// Quit program*********************************************
			if (userButton == 'q') {
				break;
			}

			// returns the user to the home screen if there is not a valid option
			else {
				System.out.println("\n***Not a valid option!***\n***Returned to HOME Screen***\n");
				homeScreen();
				userButton = scnr.next().charAt(0);
			}
		}
		scnr.close();
		System.out.println("\nPROGRAM HAS QUIT");
	}
}

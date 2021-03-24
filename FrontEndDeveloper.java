
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

	private static int numRestaurantsRanked = 100; // default

	// Method that displays ONLY the TEXT of the interactive menu
	public static void homeScreen() {
		System.out.println("HOME SCREEN");
		System.out.println("Press \"r\" for the Rank Screen to view the whole list");
		System.out.println("Press \"m\" for the Map Screen to check locations");
		System.out.println("Press \"i\" for the individual restaurant Information or adding a Restaurant to the List");
		System.out.println("Press \"q\" to Quit Program\n");
		System.out.println("Enter one of the above options then press <ENTER>");
		System.out.print("Enter  option:");
	}

	// Method for the Rank screen---------------------------------------------------
	// arguments are the scanner object for button functionality and the linked List
	// of restaurant objects
	private static char rankScreen(Scanner scan, BackendInterface book) {

		// List all Restaurants by Rank - Press "l"
		System.out.println("press \"l\" to List ALL Restaurant by rank");
		System.out.println("press \"s\" for a short list of the top 10 rated restaurants ");
		System.out.println("press \"h\" to return to the Home screen");
		System.out.println("Press \"q\" to Quit Program");
		System.out.println("Enter one of the above options then press <ENTER>");
		System.out.print("Enter  option:");

		Stream<RestaurantInterface> printRanking = book.getTopRestaurants(1000);

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
	private static char mapScreen(Scanner scan, BackendInterface book) {

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
			book.getAllStates().distinct().forEach(b -> System.out.println(b));
		}

		// displays the cities that have restaurants in the top 100
		else if (localButton == 'c') {
			book.getAllCities().distinct().forEach(b -> System.out.println(b));
		}

		// enter a state to display list of restaurant in that state
		else if (localButton == 's') {

			List<List<RestaurantInterface>> allRes = book.getAllStateRestaurants();

			for (List<RestaurantInterface> resInState : allRes) {
				if (allRes.size() <= 0)
					break;
				System.out.println("\nRestaurants in: \"" + resInState.get(0).getState() + "\" listed below:");

				for (RestaurantInterface res : resInState) {
					System.out.println(res.getRestaurantName() + " -- Rank: " + res.getRank());
				}
			}
		}

		// quit program
		else if (localButton == 'q')
			return localButton;

		// return to the home screen
		else if (localButton == 'h') {
			System.out.println("");
		}

		else {
			return 'x';
		}

		return localButton;
	}

	// Method for the info Screen---------------------------------------------------
	// arguments are the scanner object for button functionality and the linked List
	// of restaurant objects
	private static char infoScreen(Scanner scan, BackendInterface book) {

		// info screen functionality
		System.out.println("Press \"n\" to display the information on a specific restaurant");
		System.out.println("Press \"a\" to add a restaurant to the Rank");
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
			System.out.println("Enter a rank Number -only one digit between 1 and " + numRestaurantsRanked + "-");
			System.out.print("Enter rank number:");

			int numButton = 0;
			try {
				numButton = scan.nextInt();
				if (numButton >= 1 && numButton <= numRestaurantsRanked) {
					RestaurantInterface res = book.getRestaurant(numButton);

					// prints information
					System.out.println("\nInformation for Restaurant Ranked Number: " + numButton);
					System.out.println("   Restaurant Name: \t\t " + res.getRestaurantName());
					System.out.println("   Location: \t\t\t City: " + res.getCity() + "\t\tState: " + res.getState());
					System.out.println("   Total Sales in (Dollars): \t $" + res.getNumSales());
					System.out.println("   Total Number of Meals Served: " + res.numMealsServed());
					System.out.println("   Average cost Per meal: \t $" + res.getAvgCheck());
				} else
					throw new Exception("Not a valid rank");

			} catch (InputMismatchException e) {
				System.out.println("NOT A VALID COMMAND, PLEASE ENTER A NUMBER");
				return 'x';
			} catch (NoSuchElementException e) {
				System.out.println("NO SUCH RESTAURANT OF RANK " + numButton);
				return 'x';
			} catch (Exception e) {
				System.out.println("NO SUCH RESTAURANT OF RANK " + numButton);
				return 'x';
			}

			return localButton;
		}

		// adding a restaurant object
		if (localButton == 'a') {

			System.out.println("\nYOU ARE ADDING A RESTAURANT TO THE RANKING LIST!");

			Integer rank;
			String restaurantName;
			Integer numSales;
			Integer avgCheck;
			String city;
			String state;
			Integer numMealsServed;

			Scanner info = new Scanner(System.in);

			try {
				System.out.println("Enter the NAME of the the restaurent you want to add: (EXAMPLE: Patty's restaurant)");
				restaurantName = info.next();
				System.out.println("Enter the total sales ($): (EXAMPLE 435234)");
				info.nextLine();
				numSales = info.nextInt();
				System.out.println("Enter the Averge meal cost ($): (EXAMPLE 35)");
				avgCheck = info.nextInt();
				System.out.println("Enter the city the restaurant is Located in: (EXAMPLE Madison)");
				System.out.print("");
				city = info.nextLine();
				System.out.println("Enter the State the restaurant is Located in : (EXAMPLE W.I.)");
				state = info.nextLine();
				System.out.println("Enter the number of meals serviced in one year: (EXAMPLE 3242342)");
				numMealsServed = info.nextInt();
			} catch(Exception e) {
				System.out.println("INCORRECT INPUT, PLEASE TRY AGAIN");
				info.close();
				return 'x';
			}

			// increasing num of restaurants
			numRestaurantsRanked++;
			rank = numRestaurantsRanked;
			Restaurant addingPlace = new Restaurant(rank.toString(), restaurantName, numSales.toString(),
					avgCheck.toString(), city, state, numMealsServed.toString());

			// adding new restaurant from user into backend
			book.addRestaurant(addingPlace);

			// message to user
			System.out.println("\nYou Just added the following restaurant to the list!");
			System.out.println("Information for Restaurant Ranked Number: " + rank);
			System.out.println("   Restaurant Name: \t\t " + addingPlace.getRestaurantName());
			System.out.println(
					"   Location: \t\t\t City: " + addingPlace.getCity() + "\t\tState: " + addingPlace.getState());
			System.out.println("   Total Sales in (Dollars): \t $" + addingPlace.getNumSales());
			System.out.println("   Total Number of Meals Served: " + addingPlace.numMealsServed());
			System.out.println("   Average cost Per meal: \t $" + addingPlace.getAvgCheck());

			info.close();
			return 'i';
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
		BackendInterface book = new Backend(new FileReader("Independence100.csv"));
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
				if (userButton == 'l' || userButton == 's' || userButton == 'c') {
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
				if (userButton == 'n' || userButton == 'a') {
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

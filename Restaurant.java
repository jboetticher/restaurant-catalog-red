// --== CS400 File Header Information ==--
// Name: <Kenneth Diao>
// Email: <kdiao2@wisc.edu>
// Team: <Red>
// Group: <KF>
// TA: <Keren Chen>
// Lecturer: <Gary Dahl>
// Notes to Grader: none

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.LinkedList;

public class Restaurant implements RestaurantInterface {

	private Integer rank;
	private String name;
	private Integer sales;
	private Integer avgCheck;
	private String city;
	private String state;
	private Integer mealsServed;
	
	/**
	 * Constructor: takes parameters to create a Restaurant objects from DataReader
	 * @param rank
	 * @param name
	 * @param sales
	 * @param avgCheck
	 * @param city
	 * @param state
	 * @param mealsServed
	 */
	public Restaurant(String rank, String name, String sales, String avgCheck, String city, String state, String mealsServed) {
		this.rank = parseInt(rank);
		this.name = name;
		this.sales = parseInt(sales);
		this.avgCheck = parseInt(avgCheck);
		this.city = city;
		this.state = state;
		this.mealsServed = parseInt(mealsServed);
	}
	
	/**
	 * I made this because some numbers in the file were not in usual format (e.g 3e05)
	 * @param str the string format of the number
	 * @return the equivalent of the value represented by str
	 */
	private int parseInt(String str) {
		if(str.contains("e")) {
			String num = str.substring(0, str.indexOf("e"));
			String pow = str.substring(str.indexOf("e") + 1);
			int realInteger = (int) (Double.parseDouble(num) * Math.pow(10.0, Double.parseDouble(pow)));
			return realInteger;
		}
		return Integer.parseInt(str);
	}
	
	/**
	 * this method compares two RestaurantInterface objects by their ranks
	 * @param o is another RestaurantInterface to compare this one to
	 * @return a positive number if this rank is higher, a negative number if the other rank is higher, and zero if the two are equal
	 */
	@Override
	public int compareTo(RestaurantInterface o) {
		return o.getRank() - getRank();
	}
	
	/**
	 * I made this equals method specifically for the RestaurantInterface
	 * @param o is another RestaurantInterface to compare this one to
	 * @return true if every field in this RestaurantInterface is equal to the other and false otherwise
	 */
	public boolean equals(RestaurantInterface o) {
		if(getRank().equals(o.getRank()) &&
				getRestaurantName().equals(o.getRestaurantName()) &&
				getNumSales().equals(o.getNumSales()) &&
				getAvgCheck().equals(o.getAvgCheck()) &&
				getCity().equals(o.getCity()) &&
				getState().equals(o.getState()) &&
				numMealsServed().equals(o.numMealsServed())) {
			return true;
		}
		return false;
	}

	/**
	 * getter method
	 * @return the restaurant's rank
	 */
	@Override
	public Integer getRank() {
		return rank;
	}
	
	/**
	 * getter method
	 * @return the restaurant's name
	 */
	@Override
	public String getRestaurantName() {
		return name;
	}

	/**
	 * getter method
	 * @return this restaurant's number of sales
	 */
	@Override
	public Integer getNumSales() {
		return sales;
	}

	/**
	 * getter method
	 * @return this restaurant's average check
	 */
	@Override
	public Integer getAvgCheck() {
		return avgCheck;
	}
	
	/**
	 * getter method
	 * @return this restaurant's city
	 */
	@Override
	public String getCity() {
		return city;
	}

	/**
	 * getter method
	 * @return this restaurant's state
	 */
	@Override
	public String getState() {
		return state;
	}

	/**
	 * getter method
	 * @return this restaurant's number of meals served
	 */
	@Override
	public Integer numMealsServed() {
		return mealsServed;
	}

}

// --== CS400 File Header Information ==--
// Name: <Kenneth Diao>
// Email: <kdiao2@wisc.edu>
// Team: <Red>
// Group: <KF>
// TA: <Keren Chen>
// Lecturer: <Gary Dahl>
// Notes to Grader: none

public interface RestaurantInterface extends Comparable<RestaurantInterface> {
	public boolean equals(RestaurantInterface o);
	public Integer getRank();
	public String getRestaurantName();
	public Integer getNumSales();
	public Integer getAvgCheck();
	public String getCity();
	public String getState();
	public Integer numMealsServed();
}

import java.util.List;
import java.util.stream.Stream;

public interface BackendInterface {
  public Stream<String> getAllRestaurantNames();
  public Stream<Integer> getAllNumSales();
  public Stream<String> getAllCities();
  public Stream<String> getAllStates();
  public Stream<Integer> getNumMealsServed();
  public Stream<RestaurantInterface> getRestaurantsInState(String state);
  public Stream<RestaurantInterface> getTopRestaurants(int limit);
  public RestaurantInterface getRestaurant(String name);
  public RestaurantInterface getRestaurant(int rank);
  public List<List<RestaurantInterface>> getAllStateRestaurants();
//(return a list for restaurants with multiple locations)
  public void addRestaurant(RestaurantInterface restaurant);
}

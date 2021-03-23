import java.util.List;

public interface BackendInterface {
  public List<String> getAllRestaurantNames();
  public List<Integer> getAllNumSales();
  public List<String> getAllCities();
  public List<String> getAllStates();
  public List<Integer> getNumMealsServed();
  public List<RestaurantInterface> getRestaurantsInState(String state);
  public List<RestaurantInterface> getRestaurant(String name);
//(return a list for restaurants with multiple locations)
  public void addRestaurant(RestaurantInterface restaurant);
}

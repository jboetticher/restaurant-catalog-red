import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
//import RedBlackTree.Node<RestaurantInterface>;
//import RedBlackTree.Node;
import java.io.Reader;
import java.io.FileReader;
import java.io.StringReader;

//import javafx.util.Pair;

public class Backend implements BackendInterface {

  private RedBlackTree<RestaurantInterface> tree;
  private LinkedList<String> stateList;
  private LinkedList<String> cityList;

  public Backend(FileReader file) throws FileNotFoundException, IOException, DataFormatException {
    tree = new RedBlackTree<RestaurantInterface>();
    stateList = new LinkedList<String>();
    cityList = new LinkedList<String>();

    // instantiating the data wrangler object, using it to get a list of
    // restaurant objects, and then in a for-loop just add the restaurants to the
    // tree

    DataReader data = new DataReader();
    List<RestaurantInterface> restaurants = data.readDataSet(file);

    for (RestaurantInterface r : restaurants) {
      tree.insert(r);
      stateCityListValidation(r);
    }
  }

  /**
   * Traverser interface to use for the inOrderTraversalState class
   * 
   * @author rudyb
   *
   * @param <NodeType>
   */
  public static interface Traverser<NodeType> {
    public void visit(RedBlackTree.Node<NodeType> node);
  }

  /**
   * Traverses the tree in an inorder traversal
   * 
   * @param node - current node
   * @param t    - traverser object to provide visit rule for the method
   */
  private void inOrderTraversalState(RedBlackTree.Node<RestaurantInterface> node, Traverser t) {
    if (node.rightChild != null)
      inOrderTraversalState(node.rightChild, t);
    t.visit(node);
    if (node.leftChild != null)
      inOrderTraversalState(node.leftChild, t);
  }

  /**
   * Finds a restaurant by it's rank in the tree
   * 
   * @param id - the rank of the restaurant
   * @return - the restaurant at the specified rank
   */
  @Override
  public RestaurantInterface getRestaurant(int id) {
    RedBlackTree.Node<RestaurantInterface> current = tree.root;
    System.out.println("get restaurant id");
    if(current == null) System.out.println("WHY DAT ROOT NULL?");
    while (current != null) {
      if (current.data.getRank() == id) {
        return current.data;
      } else if (current.data.getRank() > id) {
        current = current.leftChild;
      } else {
        current = current.rightChild;
      }
    }

    System.out.println("returned null");
    return null;
  }

  /**
   * Traverses the tree and returns a list of the top restaurants
   * 
   * @param limit - the top number of restaurants to include
   * @return - a list of the top restaurants by rank
   */
  @Override
  public Stream<RestaurantInterface> getTopRestaurants(int limit) {
    LinkedList<RestaurantInterface> rs = new LinkedList<>();
    int current = 0;

    if (limit <= 0)
      return rs.stream();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      if (current < limit) {
        rs.add(((RestaurantInterface) node.data));
      }
    });

    // limits the bounds
    int newLimit = limit < tree.size() ? limit : tree.size();
    return rs.subList(0, newLimit).stream();
  }

  /**
   * Returns a String stream of all the restaurant names
   */
  @Override
  public Stream<String> getAllRestaurantNames() {
    LinkedList<String> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      rs.add(((RestaurantInterface) node.data).getRestaurantName());
    });

    return rs.stream();
  }

  /**
   * Returns an Integer stream of the number of sales for each restaurant in order
   * of rank
   */
  @Override
  public Stream<Integer> getAllNumSales() {
    LinkedList<Integer> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      rs.add(((RestaurantInterface) node.data).getNumSales());
    });

    return rs.stream();
  }

  /**
   * Returns a String stream of all the cities that restaurants are in
   */
  @Override
  public Stream<String> getAllCities() {
    return cityList.stream();
  }

  /**
   * Returns a String stream of all the states that restaurants are in
   */
  @Override
  public Stream<String> getAllStates() {
    return stateList.stream();
  }

  /**
   * Returns an Integer stream of the number of meals served for each restaurant
   */
  @Override
  public Stream<Integer> getNumMealsServed() {
    LinkedList<Integer> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      rs.add(((RestaurantInterface) node.data).getNumSales());
    });

    return rs.stream();
  }

  /**
   * Adds a restaurant to the Red Black Tree
   */
  @Override
  public void addRestaurant(RestaurantInterface restaurant) {
    tree.insert(restaurant);
    stateCityListValidation(restaurant);
  }

  /**
   * Validates that a restaurant's city and state are represented in the local
   * lists.
   * 
   * @param restaurant the restaurant to validate.
   */
  private void stateCityListValidation(RestaurantInterface restaurant) {
    if (!stateList.contains(restaurant.getState())) stateList.add(restaurant.getState());
    if (!cityList.contains(restaurant.getCity())) cityList.add(restaurant.getCity());
  }

  /**
   * Helper method for returning a list of all the restaurants in a particular
   * state
   * 
   * @param state the specified state
   * @return a list of all the restaurants in a particular state
   */
  private LinkedList<RestaurantInterface> stateHelper(String state) {
    LinkedList<RestaurantInterface> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      if (((RestaurantInterface) node.data).getState().equals(state))
        rs.add((RestaurantInterface) node.data);
    });

    // Bonus: an early implementation of an anonymous class for the traversal method
    // March 22nd, 2021

    /*
     * inOrderTraversalState(tree.root, new Traverser<RestaurantInterface,
     * RestaurantInterface>() {
     * 
     * @Override public void visit(RedBlackTree.Node<RestaurantInterface> node) { //
     * (node.data.getState().equals(state)) rs.add(node.data); } });
     */

    return rs;
  }

  /**
   * Returns a stream of restaurants in the given state
   */
  @Override
  public Stream<RestaurantInterface> getRestaurantsInState(String state) {
    return stateHelper(state).stream();
  }

  /**
   * Returns a stream of restaurant instance(s) by name (some restaurants have the
   * same name but are located in different cities)
   */
  @Override
  public RestaurantInterface getRestaurant(String name) {
    RestaurantInterface[] rs = new RestaurantInterface[0];

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      if (((RestaurantInterface) node.data).getRestaurantName().equals(name))
        rs[0] = (RestaurantInterface) node.data;
    });

    return rs[0];
  }

  /**
   * Returns a list of lists of all restaurants grouped by their state
   */
  @Override
  public List<List<RestaurantInterface>> getAllStateRestaurants() {
    LinkedList<List<RestaurantInterface>> list = new LinkedList<>();
    for (String state : stateList) {
      list.add(stateHelper(state));
    }
    return list;
  }

}

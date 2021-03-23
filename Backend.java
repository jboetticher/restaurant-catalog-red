import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
//import RedBlackTree.Node;
//import RedBlackTree.Node;
import java.io.Reader;
import java.io.FileReader;
import java.io.StringReader;

//import javafx.util.Pair;

public class Backend implements BackendInterface {
  
  public static void main(String[] args) {
    try {
      Backend b = new Backend(null);
      
      /*List<RestaurantInterface> list = b.getRestaurantsInState("Texas");
      for (RestaurantInterface r : list) {
        System.out.println(r.getRestaurantName());
      }*/
      
      /*List<String> list = b.getAllRestaurantNames();
      for (String r : list) {
        System.out.println(r);
      }*/
      
      List<RestaurantInterface> list = b.getRestaurant("Restaurant: 8");
      for (RestaurantInterface r : list) {
        System.out.println(r.getRestaurantName());
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private LinkedList<RestaurantInterface> restaurants;
  private RedBlackTree<RestaurantInterface> tree;
  private LinkedList<String> stateList;
  private LinkedList<String> cityList;
  
  public Backend(FileReader file) throws FileNotFoundException, IOException, DataFormatException {
    tree = new RedBlackTree<>();
    stateList = new LinkedList<>();
    cityList = new LinkedList<>();
    
    // I need to implement the data wrangler stuff here
    // This is just instantiating the data wrangler object, using it to get a list of
    // restaurant objects, and then in a for-loop just add the restaurants to the tree
    
    // ----- CHANGE THIS ----- //
    restaurants = new LinkedList<RestaurantInterface>();
    
    // rank, name, sales, avgCheck, city, state, mealsServed
    
    for (int i = 0; i < 10; i++) {
      restaurants.add(
          new Restaurant("" + i, "Restaurant: " + i, "" + i, "" + i, "" + i, "Texas", "" + i)
      );
    }
    
    //RestaurantDataReader data = new RestaurantDataReader();
    //restaurants = data.readDataSet(file);
    
    
    for (RestaurantInterface r : restaurants) {
      
      // All right. Now do I just add each restaurant? Probably.
      // Adding a restaurant to the salesTree is just adding them normally.
      
      tree.insert(r);
      if (stateList.contains(r.getState())) stateList.add(r.getState());
      if (cityList.contains(r.getCity())) cityList.add(r.getCity());
      
    }
  }
  
  public static interface Traverser<NodeType> {
    public void visit(RedBlackTree.Node<NodeType> node);
  }
  
  // Traverse the RedBlackTree in order
  private void inOrderTraversalState(RedBlackTree.Node<RestaurantInterface> node, 
      Traverser t) {
    if (node.rightChild != null) inOrderTraversalState(node.rightChild, t);
    t.visit(node);
    if (node.leftChild != null) inOrderTraversalState(node.leftChild, t);
  }
  
  @Override
  public List<String> getAllRestaurantNames() {
    // TODO Auto-generated method stub
    //return restaurantNames;
    
    LinkedList<String> rs = new LinkedList<>();
    
    inOrderTraversalState(tree.root, (RedBlackTree.Node node) ->
      { rs.add(((RestaurantInterface) node.data).getRestaurantName()); });
    
    return rs;
  }

  @Override
  public List<Integer> getAllNumSales() {
    // TODO Auto-generated method stub
    LinkedList<Integer> rs = new LinkedList<>();
    
    inOrderTraversalState(tree.root, (RedBlackTree.Node node) ->
      { rs.add(((RestaurantInterface) node.data).getNumSales()); });
    
    return rs;
  }

  @Override
  public List<String> getAllCities() {
    // TODO Auto-generated method stub
    return cityList;
  }

  @Override
  public List<String> getAllStates() {
    // TODO Auto-generated method stub
    return stateList;
  }

  @Override
  public List<Integer> getNumMealsServed() {
    // TODO Auto-generated method stub
    LinkedList<Integer> rs = new LinkedList<>();
    
    inOrderTraversalState(tree.root, (RedBlackTree.Node node) ->
      { rs.add(((RestaurantInterface) node.data).getNumSales()); });
    
    return rs;
  }

  @Override
  public void addRestaurant(RestaurantInterface restaurant) {
    // TODO Auto-generated method stub
    tree.insert(restaurant);
    
    if (stateList.contains(restaurant.getState())) stateList.add(restaurant.getState());
    if (cityList.contains(restaurant.getCity())) cityList.add(restaurant.getCity());
  }

  @Override
  public List<RestaurantInterface> getRestaurantsInState(String state) {
    // TODO Auto-generated method stub
    LinkedList<RestaurantInterface> rs = new LinkedList<>();
    
    inOrderTraversalState(tree.root, (RedBlackTree.Node node) ->
      { if (((RestaurantInterface) node.data).getState().equals(state))
        rs.add((RestaurantInterface) node.data); });
    
    /*inOrderTraversalState(tree.root, new Traverser<RestaurantInterface, RestaurantInterface>() {

      @Override
      public void visit(RedBlackTree.Node<RestaurantInterface> node) {
        // TODO Auto-generated method stub
        //System.out.println("Success");
        if (node.data.getState().equals(state)) rs.add(node.data);
      }
    });*/
    
    return rs;
  }
  
  @Override
  public List<RestaurantInterface> getRestaurant(String name) {
    // TODO Auto-generated method stub
    LinkedList<RestaurantInterface> rs = new LinkedList<>();
    
    inOrderTraversalState(tree.root, (RedBlackTree.Node node) ->
      { if (((RestaurantInterface) node.data).getRestaurantName().equals(name))
        rs.add((RestaurantInterface) node.data); });
    
    if (!rs.isEmpty()) return rs;
    else return null;
  }

}

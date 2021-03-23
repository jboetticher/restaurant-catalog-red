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

    DataReader data = new DataReader(file);
    LinkedList<RestaurantInterface> restaurants = data.readDataSet(file);

    for (RestaurantInterface r : restaurants) {
      tree.insert(r);
      if (stateList.contains(r.getState()))
        stateList.add(r.getState());
      if (cityList.contains(r.getCity()))
        cityList.add(r.getCity());
    }
  }

  public static interface Traverser<NodeType> {
    public void visit(RedBlackTree.Node<NodeType> node);
  }

  // Traverse the RedBlackTree in order
  private void inOrderTraversalState(RedBlackTree.Node<RestaurantInterface> node, Traverser t) {
    if (node.rightChild != null)
      inOrderTraversalState(node.rightChild, t);
    t.visit(node);
    if (node.leftChild != null)
      inOrderTraversalState(node.leftChild, t);
  }
  
  public Stream<RestaurantInterface> getRestaurantByRank(int id){
    RedBlackTree.Node<RestaurantInterface> current = tree.root;
    List<RestaurantInterface> list = new LinkedList<RestaurantInterface>();
    while(current!=null){
      if(current.data.getRank() == id){
        list.add(current.data);
        return list.stream();
      } else if(current.data.getRank() > id){
        current = current.leftChild;
      } else{
        current = current.rightChild;
      }
    }
    return null;
  }
  
  //@Override
  public Stream<RestaurantInterface> getTopRestaurants(int limit) {
    // TODO Auto-generated method stub
    // return restaurantNames;

    LinkedList<RestaurantInterface> rs = new LinkedList<>();
    int current = 0;

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      if (current < limit) { rs.add(((RestaurantInterface) node.data)); }
    });

    return rs.subList(0, limit).stream();
  }
  
  @Override
  public Stream<String> getAllRestaurantNames() {
    // TODO Auto-generated method stub
    // return restaurantNames;

    LinkedList<String> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      rs.add(((RestaurantInterface) node.data).getRestaurantName());
    });

    return rs.stream();
  }

  @Override
  public Stream<Integer> getAllNumSales() {
    // TODO Auto-generated method stub
    LinkedList<Integer> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      rs.add(((RestaurantInterface) node.data).getNumSales());
    });

    return rs.stream();
  }

  @Override
  public Stream<String> getAllCities() {
    // TODO Auto-generated method stub
    return cityList.stream();
  }

  @Override
  public Stream<String> getAllStates() {
    // TODO Auto-generated method stub
    return stateList.stream();
  }

  @Override
  public Stream<Integer> getNumMealsServed() {
    // TODO Auto-generated method stub
    LinkedList<Integer> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      rs.add(((RestaurantInterface) node.data).getNumSales());
    });

    return rs.stream();
  }

  @Override
  public void addRestaurant(RestaurantInterface restaurant) {
    // TODO Auto-generated method stub
    tree.insert(restaurant);

    if (stateList.contains(restaurant.getState()))
      stateList.add(restaurant.getState());
    if (cityList.contains(restaurant.getCity()))
      cityList.add(restaurant.getCity());
  }
  
  private LinkedList<RestaurantInterface> stateHelper(String state) {
    LinkedList<RestaurantInterface> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      if (((RestaurantInterface) node.data).getState().equals(state))
        rs.add((RestaurantInterface) node.data);
    });

    /*
     * inOrderTraversalState(tree.root, new Traverser<RestaurantInterface,
     * RestaurantInterface>() {
     * 
     * @Override public void visit(RedBlackTree.Node<RestaurantInterface> node) { //
     * TODO Auto-generated method stub //System.out.println("Success"); if
     * (node.data.getState().equals(state)) rs.add(node.data); } });
     */

    return rs;
  }
    
  @Override
  public Stream<RestaurantInterface> getRestaurantsInState(String state) {
    // TODO Auto-generated method stub
    return stateHelper(state).stream();
  }

  @Override
  public Stream<RestaurantInterface> getRestaurant(String name) {
    // TODO Auto-generated method stub
    LinkedList<RestaurantInterface> rs = new LinkedList<>();

    inOrderTraversalState(tree.root, (RedBlackTree.Node node) -> {
      if (((RestaurantInterface) node.data).getRestaurantName().equals(name))
        rs.add((RestaurantInterface) node.data);
    });

    return rs.stream();
  }
    
  public List<LinkedList<RestaurantInterface>> getAllStateRestaurants() {
    LinkedList<LinkedList<RestaurantInterface>> list = new LinkedList<>();
    for (String state : stateList) {
        list.add(stateHelper(state));
    }
    return list;
  }

}

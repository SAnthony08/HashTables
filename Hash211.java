import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Creates a Hash Table using an ArrayList of LinkedLists of HashNodes
 * 
 * @author Shawn Anthony
 */
public class Hash211<K, V> {
  private ArrayList<LinkedList<HashNode<K, V>>> HashMap;
  private boolean print;
  private int arraySize;


  /**
   * Constructor for HashTable
   * 
   * @param capacity the max capacity of the table
   * @param printTimes whether to print the time taken
   */
  public Hash211(int capacity, boolean printTimes) {
    this.HashMap = new ArrayList<LinkedList<HashNode<K, V>>>(capacity);
    this.print = printTimes;
    this.arraySize = capacity;
    
    for (int i = 0; i < capacity; i++) {
      HashMap.add(new LinkedList<HashNode<K, V>>());
    }
  }


  /**
   * Adds the node with key and value to the hash table
   * 
   * @param key the key to add
   * @param value the value to add
   * @return null or the old value
   */
  public V put(K key, V value) {
    if (key == null || value == null) {
      throw new NullPointerException("Parameters cannot be null");
    }
    long start = 0;
    if (print == true) {
      start = System.nanoTime();
    }
    int hash = Math.abs(key.hashCode()) % arraySize;
    if (getH(key) == null) { //checks for collision with identical key
      HashNode<K, V> newNode = new HashNode<K, V>(key, value);
      newNode.next = HashMap.get(hash).peek();
      if (print == true) {
        long endTime = System.nanoTime() - start;
        System.out.println(endTime);
      }
      return null;
    }
    else {
      HashNode<K, V> head = HashMap.get(hash).getFirst();
      while (head.next != null) {
        if (head.key.equals(key)) {
          V retVal = head.value;
          head.value = value;
          if (print == true) {
            long endTime = System.nanoTime() - start;
            System.out.println(endTime);
          }
          return retVal;
        }
      }
    }
    if (print == true) {
      long endTime = System.nanoTime() - start;
      System.out.println(endTime);
    }
    return null;
  }


  /**
   * Gets the value for the provided key, or null if the key is not found
   * 
   * @param key the key to check for
   * @return old value or null
   */
  public V get(Object key) {
    if (key == null) {
      throw new NullPointerException("Key cannot be null");
    }
    long start = 0;
    if (print == true) {
      start = System.nanoTime();
    }
    int hash = Math.abs(key.hashCode()) % arraySize; //gets hash
    if (HashMap.get(hash).size() > 0) { //checks if list at hash index is empty
      HashNode<K, V> head = HashMap.get(hash).peek(); //gets first node
      while (head != null) { //iterates while theres still a next object
        if (head.key.equals(key)) { //checks for equality of the keys
          if (print == true) { //prints if needed
            long endTime = System.nanoTime() - start;
            System.out.println(endTime);
          }
          return head.value; //returns our value & quits out
        }
        head = head.next; //advances our node
      }
    }
    if (print == true) {
      long endTime = System.nanoTime() - start;
      System.out.println(endTime);
    }
    return null;
  }
  
  /**
   * This is just used to call the method with no print statements for use in put method
   */
  public V getH(Object key) {
    if (key == null) {
      throw new NullPointerException("Key cannot be null");
    }
    int hash = Math.abs(key.hashCode()) % arraySize; //gets hash
    if (HashMap.get(hash).size() > 0) { //checks if list at hash index is empty
      HashNode<K, V> head = HashMap.get(hash).peek(); //gets first node
      while (head != null) { //iterates while theres still a next object
        if (head.key.equals(key)) { //checks for equality of the keys
          return head.value; //returns our value & quits out
        }
        head = head.next; //advances our node
      }
    }
    return null;
  }
  

  /**
   * Class to create Hash Nodes
   */
  private class HashNode<K, V> {
    private HashNode<K, V> next;
    private K key;
    private V value;


    /**
     * Constructor for HashNode
     * 
     * @param key the key
     * @param value the value
     */
    public HashNode(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }

  }
}

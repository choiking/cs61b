/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
 protected DList[] hashTable;
 protected int numberOfEntries;
 
 
  /** 
   *  Construct a new empty hash table ((INTENTED)) to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
     while(!isPrime(sizeEstimate)){
      sizeEstimate++;
     }
     hashTable = new DList[sizeEstimate];
    numberOfEntries=0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(101);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/


 /*private int findMaxPrime(int n) {
        for (int i = n ; i > 1 ; i -- ) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 2;
    }
*/
 private boolean isPrime(int n) {
    if (n%2==0 && n!=2) {
      return false;
    }
    for (int i=3; i*i<=n;i+=2) {
      if (n%i==0) {
        return false;
      }
    }
    return true;
  }



  int compFunction(int code) {
    // Replace the following line with your solution.
    int cv=0;
      cv = code%hashTable.length;
     if(cv<0) cv=cv+hashTable.length;

    return cv ;// length of array
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return numberOfEntries;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if (numberOfEntries==0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry entry=new Entry();
    entry.key=key;
    entry.value=value;
   
   int code=key.hashCode();
   int cv=compFunction(code);
   
   DList list;
   if(hashTable[cv]==null){
    list=new DList();
   }
   else 
    {
      list=hashTable[cv];
    }
   list.insertFront(entry);
   hashTable[cv]=list;

   numberOfEntries++;
   return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    
          int index = compFunction(key.hashCode());

        if (hashTable[index] == null)
            return null;
        else {
            DList list = hashTable[index];
            ListNode node = list.front();
            if (node.isValidNode()) {
                try {
                    while (true) {
                        Entry entry = (Entry) node.item();
                        if (entry.key.equals(key))//May the different keys mapped into same index
                            return entry;
                        else {
                            node = node.next();
                        }
                    }
                } catch (InvalidNodeException ex) {
                    return null;
                }
            }
        }
        return null;

  }
   
  

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
   try{
   int code=key.hashCode();
   int cv=compFunction(code);
  DList list=hashTable[cv];
  ListNode node=list.front();

  while(node.isValidNode()){
    if(((Entry)node.item()).key().equals(key)) {
      Entry foundKey=(Entry)node.item();
      node.remove();
      numberOfEntries--;
      return foundKey;
    }
    else node=node.next();
  }
  return null;
}
   catch (InvalidNodeException e) {
      System.out.println(e);
      return null;
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    hashTable=new DList[hashTable.length];
    numberOfEntries=0;
  }


   /* public String toString() {
    String string="[  ";
    try{
      for (int i=0; i<hashTable.length; i++) {
        int count=0;
        if (hashTable[i]!=null) {
          ListNode node=hashTable[i].front();
          while(node.isValidNode()) {
            node=node.next();
            count++;
          }
          string+=count+"  ";
        }
      }
    } catch (InvalidNodeException e) {
      System.out.println(e);
    }
    return string+"]";
    */
    /**
   * Print statistical info about the hashtable: the number of keys stored in each bucket and the total number of collisions.
   */
  public void printHistogram() {
    String histog = "";
    double ecols = 0;
    int cols=0;
    for (int i=0; i<hashTable.length; i++) {
      if (hashTable[i]==null) {
        histog = histog + "[0] ";
      } else {
        histog = histog + "[" + hashTable[i].length()+"]";
        cols = cols + hashTable[i].length() - 1;
      }
    }
    
    double i=1-1/(double)hashTable.length;
    double j=(double)hashTable.length*Math.pow(i,(double)numberOfEntries);
    ecols=(double)numberOfEntries-(double)hashTable.length+j;
    System.out.println(histog);
    System.out.println("Number of entries: " + numberOfEntries);
    System.out.println("Number of buckets: " + hashTable.length);
    System.out.println("Expected number of collisions: " + ecols);
    System.out.println("Actual number of collisions: " + cols);
  }
  


      public static void main(String[] args) {
        HashTableChained newTable = new HashTableChained();
        System.out.println("Hash table is empty: " + newTable.isEmpty());

        newTable.insert(3, "Hello");
        newTable.insert(100, "World");
        System.out.println("Hash table size should be 2: " + newTable.size() );
        
        newTable.printHistogram();

        Entry entry = newTable.find(3);
        System.out.println("The value for 3 is :" + entry.value);

        entry = newTable.find(100);
        System.out.println("The value for 100 is :" + entry.value);

        newTable.remove(100);
        System.out.println("The value for 100 is :" + newTable.find(100));
        System.out.println("The value for 3 is :" + newTable.find(3).value);

        newTable.makeEmpty();
        System.out.println("Hash table size should be 0:" + newTable.size());
        System.out.println("The value for 100 is :" + newTable.find(100));
        System.out.println("The value for 3 is :" + newTable.find(3));
    }

}
/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class LockDList extends DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

 
 

protected DListNode newNode(Object item, DListNode prev, DListNode next){
   return new LockDListNode(item,prev,next);
}


public void remove(DListNode node){
  
  if (((LockDListNode)node).isLocked==false) {
    if (((LockDListNode)node)!=null) {
      super.remove(node);
    }
  
}
 }

public void lockNode(DListNode node){
  
   ((LockDListNode)node).isLocked=true;
 
}

/*public LockDListNode front() {
  if(node instanceof LockDListNode){
    return (LockDListNode)(super.front());
}}

public LockDListNode back() {
  if(node instanceof LockDListNode){
    return (LockDListNode)(super.back());
}}
*/
}
 
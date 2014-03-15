/* DList.java */

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    //  Your solution here.
	 head = newNode(null, head, head);
	 size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    // Your solution here.
	if(isEmpty()){
		head.next = newNode(item, head, head);
		head.prev = head.next;
		size ++;
	}else{
		head.next = newNode(item, head, head.next);
		head.next.next.prev = head.next;
		size++;
	}
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    // Your solution here.
	if(isEmpty()){
		insertFront(item);
	}else{
		head.prev.next = newNode(item, head.prev, head);
		head.prev = head.prev.next;
		size++;
	}
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    // Your solution here.
	if(!isEmpty()){
		return head.next;
	}else{
		return null;
	}
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    // Your solution here.
	  if(!isEmpty()){
		  return head.prev;
	  }else{
		  return null;
	  }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    // Your solution here.
	  if(node == null){
		  return null;
	  }else if(node.next == head){
		  return null;  
	  }else{
		  return node.next;
	  }
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    // Your solution here.
	if(node == null){
		return null;
	}else if(node.prev == head){
		return null;
	}else{
		return node.prev;
	}
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    // Your solution here.
	if(node != null){
		node.next = newNode(item, node, node.next);
		node.next.next.prev = node.next;
		size++;
	}
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    // Your solution here.
	if(node != null){
		node.prev = newNode(item, node.prev, node);
		node.prev.prev.next = node.prev;
		size++;
	}
  }
  
  
  /**
   * isFront() checks if node is the first node in the DList
   * @param node
   * @return
   */
  public boolean isFront(DListNode node){
	  return node.prev == head;
  }
  
  
  /**
   * isBack() checks if node is the last node in the DList 
   * @param node
   * @return
   */
  public boolean isBack(DListNode node){
	  return node.next == head;
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
	if(node != null){
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
	}
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
  public static void main(String[] args){
	  DList ds = new DList();
	  for(int i = 0; i<10; i++){
		  ds.insertFront(i);
		  System.out.print(ds.head.next.item + " ");
	  }
	  System.out.println();
	  DListNode dummy = ds.head;
	  for(int i = 0; i<11; i++){
		  System.out.print(dummy.item + " ");
		  dummy = dummy.prev;
	  }
	  System.out.println("\n");
	  
	  dummy = ds.head;
	  for(int i = 0; i<11; i++){
		  System.out.print(dummy.item + " ");
		  dummy = dummy.next;
	  }
	  System.out.println("\n");
	  
	  System.out.println("Test front()\n the front is:");
	  System.out.println(ds.front());
	  System.out.println("with item: " + ds.front().item);
	  
	  DList dummyList = new DList();
	  System.out.print("Test front() with an empty list\n front() returned: ");
	  System.out.println(dummyList.front());
	  
	  System.out.print("Test front()\n the front is: ");
	  System.out.println(ds.front());
	  System.out.println("with item: " + ds.front().item);
	  
	  System.out.print("Test back()\n the back is: ");
	  System.out.println(ds.back());
	  System.out.println("with item: " + ds.back().item);
	  
	  System.out.println("testing next()");
	  dummy = ds.head;
	  for(int i = 0; i<11; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println("\n");
	  
	  System.out.println("testing prev()");
	  dummy = ds.head;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.prev(dummy);
	  }
	  System.out.println("\n");
	  
	  System.out.println("testing insertAfter() and traversing forward");
	  System.out.println("this is the DList befor insert:");
	  System.out.println("size: " + ds.size);
	  dummy = ds.head.next;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println("\n");
	  
	  dummy = ds.head.next.next;
	  ds.insertAfter(100, dummy);
	  System.out.println("this is the DList after inserting 100 after the second node:");
	  System.out.println("size: " + ds.size);
	  dummy = ds.head.next;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println("\n");
	  
	  System.out.println("testing insertBefore() and traversing forward");
	  System.out.println("this is the DList befor insert:");
	  System.out.println("size: " + ds.size);
	  dummy = ds.head.next;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println("\n");
	  
	  dummy = ds.head.prev.prev;
	  ds.insertBefore(200, dummy);
	  System.out.println("this is the DList after inserting 200 before the second to last node:");
	  System.out.println("size: " + ds.size);
	  dummy = ds.head.next;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println("\n");
	  
	  System.out.println("testing remove()");
	  System.out.println("this is the DList befor removal:");
	  System.out.println("size: " + ds.size);
	  dummy = ds.head.next;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println();
	  
	  
	  dummy = ds.head.prev.prev;
	  ds.remove(dummy);
	  dummy = ds.head.next.next;
	  ds.remove(dummy);
	  System.out.println("this is the DList after second to last and the second items:");
	  System.out.println("size: " + ds.size);
	  dummy = ds.head.next;
	  for(int i = 0; i<ds.size; i++){
		  System.out.print(dummy.item + " ");
		  dummy = ds.next(dummy);
	  }
	  System.out.println("\n");
	  
	  System.out.println("Trying to find errors");
	  DList ds2 = new DList();
	  DListNode naughty = ds.back();
	  naughty = ds2.next(naughty);
	  System.out.println(naughty == ds.head);
  }
}

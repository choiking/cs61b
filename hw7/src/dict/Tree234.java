/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

    /**
     * You may add fields if you wish, but don't change anything that
     * would prevent toString() or find() from working correctly.
     * <p>
     * (inherited)  size is the number of keys in the dictionary.
     * root is the root of the 2-3-4 tree.
     **/
    Tree234Node root;

    /**
     * Tree234() constructs an empty 2-3-4 tree.
     * <p>
     * You may change this constructor, but you may not change the fact that
     * an empty Tree234 contains no nodes.
     */
    public Tree234() {
        root = null;
        size = 0;
    }

    public Tree234(Tree234Node a) {
        root = a;
        size = 1;
    }

    /**
     * toString() prints this Tree234 as a String.  Each node is printed
     * in the form such as (for a 3-key node)
     * <p>
     * (child1)key1(child2)key2(child3)key3(child4)
     * <p>
     * where each child is a recursive call to toString, and null children
     * are printed as a space with no parentheses.  Here's an example.
     * ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
     * <p>
     * DO NOT CHANGE THIS METHOD.  The test code depends on it.
     *
     * @return a String representation of the 2-3-4 tree.
     **/
    public String toString() {
        if (root == null) {
            return "";
        } else {
      /* Most of the work is done by Tree234Node.toString(). */
            return root.toString();
        }
    }

    /**
     * printTree() prints this Tree234 as a tree, albeit sideways.
     * <p>
     * You're welcome to change this method if you like.  It won't be tested.
     **/
    public void printTree() {
        if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
            root.printSubtree(0);
        }
    }

    /**
     * find() prints true if "key" is in this 2-3-4 tree; false otherwise.
     *
     * @param key is the key sought.
     * @return true if "key" is in the tree; false otherwise.
     **/
    public boolean find(int key) {
        Tree234Node node = root;
        while (node != null) {
            if (key < node.key1) {
                node = node.child1;
            } else if (key == node.key1) {
                return true;
            } else if ((node.keys == 1) || (key < node.key2)) {
                node = node.child2;
            } else if (key == node.key2) {
                return true;
            } else if ((node.keys == 2) || (key < node.key3)) {
                node = node.child3;
            } else if (key == node.key3) {
                return true;
            } else {
                node = node.child4;
            }
        }
        return false;
    }

    /**
     * insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
     * already present, a duplicate copy is NOT inserted.
     *
     * @param key is the key sought.
     **/
    public void insert(int key) {
        // Fill in your solution here.
        /*
        if (find(key) == false) {
            size++;
            if (isEmpty()) root = new Tree234Node(null, key);
            Tree234Node node = root;
            while (node != null) {
                if (node.keys == 3) {
                    //whenever it encounters a 3 key node, middle key is placed in parent node.
                    if (node.keys == 3) {
                        if(node.parent==null){
                            Tree234Node newRoot = new Tree234Node(null, root.key2);
                            newRoot.child1 = new Tree234Node(newRoot, root.key1);
                            newRoot.child2 = new Tree234Node(newRoot, root.key3);
                            newRoot.child1.child1 = root.child1;
                            newRoot.child1.child2 = root.child2;
                            newRoot.child2.child1 = root.child3;
                            newRoot.child2.child2 = root.child4;
                            root = newRoot;
                        }
                        if (node.parent.keys == 2) {
                            if (node == node.parent.child1) {
                                Tree234Node newNode = new Tree234Node(node.parent.parent, node.key2, node.parent.key1, node.parent.key2);
                                newNode.child1 = new Tree234Node(newNode, node.key1);
                                newNode.child2 = new Tree234Node(newNode, node.key3);
                                newNode.child3 = node.parent.child2;
                                newNode.child4 = node.parent.child3;
                                if (!node.isLeaf()) {
                                    newNode.child1.child1 = node.parent.child1;
                                    newNode.child1.child2 = node.parent.child2;
                                    newNode.child2.child1 = node.parent.child3;
                                    newNode.child2.child2 = node.parent.child4;
                                }
                                node.parent = newNode;
                            }
                            if (node == node.parent.child2) {
                                Tree234Node newNode = new Tree234Node(node.parent.parent, node.parent.key1, node.key2, node.parent.key2);
                                newNode.child1 = node.parent.child1;
                                newNode.child2 = new Tree234Node(newNode, node.key1);
                                newNode.child3 = new Tree234Node(newNode, node.key3);
                                newNode.child4 = node.parent.child3;
                                if (!node.isLeaf()) {
                                    newNode.child2.child1 = node.parent.child1;
                                    newNode.child2.child2 = node.parent.child2;
                                    newNode.child3.child1 = node.parent.child3;
                                    newNode.child3.child2 = node.parent.child4;
                                }
                                node.parent = newNode;
                            }
                            if (node == node.parent.child3) {
                                Tree234Node newNode = new Tree234Node(node.parent.parent, node.key1, node.key2, node.key2);
                                newNode.child1 = node.parent.child1;
                                newNode.child2 = node.parent.child2;
                                newNode.child3 = new Tree234Node(newNode, node.key1);
                                newNode.child4 = new Tree234Node(newNode, node.key3);
                                if (!node.isLeaf()) {
                                    newNode.child3.child1 = node.parent.child1;
                                    newNode.child3.child2 = node.parent.child2;
                                    newNode.child4.child1 = node.parent.child3;
                                    newNode.child4.child2 = node.parent.child4;
                                }
                                node.parent = newNode;
                            }
                        }
                        if (node.parent.keys == 1) {
                            if (node == node.parent.child1) {
                                Tree234Node newNode = new Tree234Node(node.parent.parent, node.key2, node.parent.key1);
                                newNode.child1 = new Tree234Node(newNode, node.key1);
                                newNode.child2 = new Tree234Node(newNode, node.key3);
                                newNode.child3 = node.parent.child2;
                                if (!node.isLeaf()) {
                                    newNode.child1.child1 = node.parent.child1;
                                    newNode.child1.child2 = node.parent.child2;
                                    newNode.child2.child1 = node.parent.child3;
                                    newNode.child2.child2 = node.parent.child4;
                                }
                                node.parent = newNode;
                            }
                            if (node == node.parent.child2) {
                                Tree234Node newNode = new Tree234Node(node.parent.parent, node.parent.key1, node.key2);
                                newNode.child1 = node.parent.child1;
                                newNode.child2 = new Tree234Node(newNode, node.key1);
                                newNode.child3 = new Tree234Node(newNode, node.key3);
                                if (!node.isLeaf()) {
                                    newNode.child2.child1 = node.parent.child1;
                                    newNode.child2.child2 = node.parent.child2;
                                    newNode.child3.child1 = node.parent.child3;
                                    newNode.child3.child2 = node.parent.child4;
                                }
                                node.parent = newNode;
                            }
                        }
                    }

                    //changing position
                    if (key < node.key1) {
                        if(node.child1==null) node.child1=new Tree234Node(node.parent,key);
                        else node = node.child1;
                    } else if ((node.keys == 1) || (key < node.key2)) {
                        if(node.child1==null) node.child1=new Tree234Node(node.parent,key);
                        else if(node.child2==null) node.child2=new Tree234Node(node.parent,key);
                        else  node = node.child2;
                    } else if ((node.keys == 2) || (key < node.key3)) {
                        if(node.child3==null) node.child3=new Tree234Node(node.parent,key);
                        else node = node.child3;
                    } else {
                        if(node.child4==null) node.child4=new Tree234Node(node.parent,key);
                        else node = node.child4;
                    */
       if(find(key)) return;
        if(root==null) {  //can't use isEmpty, because insertion can't change size;
            root=new Tree234Node(null,key);
            return;
        }
        Tree234Node node=root;
        while(node!=null){
            if(node.keys==3){//when node's keys equal 3
                //firstly deal with the reference between this node and its childNode

                Tree234Node leftNode=new Tree234Node(node.parent,node.key1,node.child1,node.child2);
                Tree234Node rightNode=new Tree234Node(node.parent,node.key3,node.child3,node.child4);
                if(!node.isLeaf()){
                    node.child1.parent=leftNode;
                    node.child2.parent=leftNode;
                    node.child3.parent=rightNode;
                    node.child4.parent=rightNode;
                }


                if(node==root){// CASE when node equals root(deal with the reference between this node and its parent)
                    Tree234Node newRoot = new Tree234Node(null, node.key2);
                    leftNode.parent=newRoot;
                    rightNode.parent=newRoot;
                    newRoot.child1=leftNode;
                    newRoot.child2=rightNode;
                    root = newRoot;
                }
                else{//CASE when node is not the root
                    //deal with the reference between this node and its parent(depended on parent' keys(1 or 2))
                    //add middle key to parent

                    //preparation
                    Tree234Node parent=node.parent;
                    int middleKey=node.key2;
                    int location;// indicate where to put the key2 in

                    if (middleKey < parent.key1) {// decide the value of lccation(keys of parent can ONLY be 1 or 2)
                        location=0;
                    } else if ((parent.keys == 1) || (middleKey< parent.key2)) {
                        location=1;
                    } else  {
                        location=2;
                    }

                    //deal with the reference between this node and its parent(depended on parent' keys(1 or 2))
                    //add middle key to parent
                    if(parent.keys==1){
                        if(location==0){
                          //parent=new Tree234Node(parent.parent,middleKey,parent.key1,leftNode,rightNode,parent.child2);
                            parent.key2 = parent.key1;
                            parent.key1 = middleKey;
                            parent.child3 = parent.child2;
                            parent.child1 = leftNode;
                            parent.child2 = rightNode;
                        }
                        else{
                         //parent=new Tree234Node(parent.parent,parent.key1,middleKey,parent.child1,leftNode,rightNode);
                            parent.key2 = middleKey;
                            parent.child2 = leftNode;
                            parent.child3 = rightNode;
                        }
                    }
                    else{
                        if(location==0){
                           // parent=new Tree234Node(node.parent,middleKey,parent.key1,parent.key2,leftNode,rightNode,parent.child2,parent.child3);
                            parent.key3 = parent.key2;
                            parent.key2 = parent.key1;
                            parent.key1 = middleKey;
                            parent.child4 = parent.child3;
                            parent.child3 = parent.child2;
                            parent.child1 = leftNode;
                            parent.child2 = rightNode;
                        }
                        else if(location==1){
                            //parent=new Tree234Node(node.parent,parent.key1,middleKey,parent.key2,parent.child1,leftNode,rightNode,parent.child2);
                            parent.key3 = parent.key2;
                            parent.key2 = middleKey;
                            parent.child4 = parent.child3;
                            parent.child2 = leftNode;
                            parent.child3 = rightNode;
                        }
                        else{
                            //parent=new Tree234Node(node.parent,parent.key1,parent.key2,middleKey,parent.child1,parent.child2,leftNode,rightNode);
                            parent.key3 = middleKey;
                            parent.child3 = leftNode;
                            parent.child4 = rightNode;
                        }
                    }
                    parent.keys++;
                }// end of CASE when node is not the root

                //moving position when done dealing with 3 keys node
                if(key<node.key2){
                    node=leftNode;
                }
                else {
                    node=rightNode;
                }
            }// end of 3 keys node

            if(node.isLeaf()){// when node is the leaf, do the insertion
             int location;  //preparation
                if (key < node.key1) {// decide the value of lccation(keys of parent can ONLY be 1 or 2)
                    location=0;
                } else if ((node.keys == 1) || (key< node.key2)) {
                    location=1;
                } else {
                    location=2;
                }
             if(node.keys==1){
                  if(location==0){
                      node.key2=node.key1;
                      node.key1=key;
                  }
                  else{
                      node.key2=key;
                  }
             }
             else{
                 if (location==0){
                     node.key3=node.key2;
                     node.key2=node.key1;
                     node.key1=key;
                 }
                 else if (location==1){
                     node.key3=node.key2;
                     node.key2=key;
                 }
                 else{
                     node.key3=key;
                 }
             }
             node.keys++;
             break;//all the insetion work done!!!!!
            }
            else{// keep moving down
                if (key < node.key1) {// decide the value of lccation(keys of parent can ONLY be 1 or 2)
                    node=node.child1;
                } else if ((node.keys == 1) || (key< node.key2)) {
                    node=node.child2;
                } else {
                    node=node.child3;
                }
            }

        }


                }



    /**
     *  testHelper() prints the String representation of this tree, then
     *  compares it with the expected String, and prints an error message if
     *  the two are not equal.
     *
     *  @param correctString is what the tree should look like.
     **/
    public void testHelper(String correctString) {
        String treeString = toString();
        System.out.println(treeString);
        if (!treeString.equals(correctString)) {
            System.out.println("ERROR:  Should be " + correctString);
        }
    }

    /**
     *  main() is a bunch of test code.  Feel free to add test code of your own;
     *  this code won't be tested or graded.
     **/
    public static void main(String[] args) {
        Tree234 t = new Tree234();

        System.out.println("\nInserting 84.");
        t.insert(84);
        t.testHelper("84");

        System.out.println("\nInserting 7.");
        t.insert(7);
        t.testHelper("7 84");

        System.out.println("\nInserting 22.");
        t.insert(22);
        t.testHelper("7 22 84");

        System.out.println("\nInserting 95.");
        t.insert(95);
        t.testHelper("(7)22(84 95)");

        System.out.println("\nInserting 50.");
        t.insert(50);
        t.testHelper("(7)22(50 84 95)");

        System.out.println("\nInserting 11.");
        t.insert(11);
        t.testHelper("(7 11)22(50 84 95)");

        System.out.println("\nInserting 37.");
        t.insert(37);
        t.testHelper("(7 11)22(37 50)84(95)");

        System.out.println("\nInserting 60.");
        t.insert(60);
        t.testHelper("(7 11)22(37 50 60)84(95)");

        System.out.println("\nInserting 1.");
        t.insert(1);
        t.testHelper("(1 7 11)22(37 50 60)84(95)");

        System.out.println("\nInserting 23.");
        t.insert(23);
        t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

        System.out.println("\nInserting 16.");
        t.insert(16);
        t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

        System.out.println("\nInserting 100.");
        t.insert(100);
        t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

        System.out.println("\nInserting 28.");
        t.insert(28);
        t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

        System.out.println("\nInserting 86.");
        t.insert(86);
        t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

        System.out.println("\nInserting 49.");
        t.insert(49);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

        System.out.println("\nInserting 81.");
        t.insert(81);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

        System.out.println("\nInserting 51.");
        t.insert(51);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

        System.out.println("\nInserting 99.");
        t.insert(99);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

        System.out.println("\nInserting 75.");
        t.insert(75);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                "(99 100))");

        System.out.println("\nInserting 66.");
        t.insert(66);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                "(99 100))");

        System.out.println("\nInserting 4.");
        t.insert(4);
        t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                "((86)95(99 100))");

        System.out.println("\nInserting 80.");
        t.insert(80);
        t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                "(80 81))84((86)95(99 100)))");

        System.out.println("\nFinal tree:");
        t.printTree();
    }

}
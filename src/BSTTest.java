import java.util.Iterator;
import junit.framework.TestCase;

/**
 * Class where we create test cases for all of the methods in the BST class. We
 * verify that each method is functional and makes changes to the tree
 * accordingly as we expect.
 *
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */
public class BSTTest extends TestCase
{
    /**
     * Declares our tree that can hold Integers
     */
    private BST<Integer> tree;

    /**
     * Constructor for the class that creates the tree object that holds
     * Integers
     */
    public void setUp()
    {
        tree = new BST<Integer>();
    }

    /**
     * Tests the clear method by inserting a Integer into the tree and then
     * removing it
     */
    public void testClear()
    {
        // Create Integer
        Integer firstInteger = new Integer(0);

        // Inserts Integer into the tree
        tree.insert(firstInteger);

        // Tree is not empty because it contains a Integer
        assertEquals(false, tree.isEmpty());

        // Clear the tree
        tree.clear();

        // Check to see if the tree is empty after clearing it
        assertEquals(true, tree.isEmpty());
    }

    /**
     * Tests the find method by inserting Integers into the tree and verifying
     * that they can be found when called
     */
    public void testFind()
    {
        // Creates three Integers
        Integer middle = new Integer(1);
        Integer left = new Integer(0);
        Integer right = new Integer(2);

        // Inserts Integers into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Ensure that the middle node contains the
        // "middle" Integer
        assertEquals(middle, tree.find(middle));

        // Remove the "middle" Integer from the tree
        tree.remove(middle);

        // Create a new Integer, but never insert it into the tree
        Integer hello = new Integer(9);
        Integer nullInteger = null;

        // Verify that the Integer is not in the tree because
        // it was never inserted in the first place
        assertEquals(null, tree.find(hello));
        assertEquals(null, tree.find(nullInteger));
    }

    /**
     * Tests the insert method by seeing if the Integer nodes are in the correct
     * position in the tree
     */
    public void testInsert()
    {
        // Creates three Integers
        Integer middle = new Integer(1);
        Integer left = new Integer(0);
        Integer right = new Integer(2);
        Integer nullInteger = null;

        // Inserts Integers into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Verifies that the tree nodes are in the correct position
        assertEquals(middle, tree.getRoot().getElement());
        assertEquals(left, tree.getRoot().getLeft().getElement());
        assertEquals(right, tree.getRoot().getRight().getElement());
        assertEquals(false, tree.insert(nullInteger));

        BST<Integer> tree2 = new BST<Integer>();
        Integer firstInt = new Integer(1);
        Integer secondInt = new Integer(2);
        Integer thirdInt = new Integer(3);

        tree2.insert(firstInt);
        tree2.insert(secondInt);
        tree2.insert(thirdInt);
        assertEquals(firstInt, tree2.getRoot().getElement());
        assertEquals(secondInt, tree2.getRoot().getRight().getElement());
        assertEquals(thirdInt,
                tree2.getRoot().getRight().getRight().getElement());
    }

    /**
     * Tests if the Integers are actually being inserted into the tree
     */
    public void testInsertBoolean()
    {
        // Creates three Integers
        Integer firstInteger = new Integer(0);
        Integer secondInteger = new Integer(1);
        Integer thirdInteger = new Integer(2);

        // Inserts them into the tree
        Boolean insertedA = tree.insert(firstInteger);
        Boolean insertedB = tree.insert(secondInteger);
        Boolean insertedC = tree.insert(thirdInteger);

        // Checks that the Integers have been inserted
        assertEquals(true, insertedA.booleanValue());
        assertEquals(true, insertedB.booleanValue());
        assertEquals(true, insertedC.booleanValue());
    }

    /**
     * Tests if the tree has a next node, which means the stack is not empty
     */
    public void testIsEmpty()
    {
        // Creates Integer
        Integer firstInteger = new Integer(0);

        // Inserts them into the tree
        tree.insert(firstInteger);

        // Checks to see if the tree is empty, which it is not
        assertEquals(false, tree.isEmpty());

        // Remove Integer from the BST
        tree.remove(firstInteger);

        // Now the tree should be empty
        assertEquals(true, tree.isEmpty());

        // Case for an empty tree, which has nothing inserted
        BST<Integer> tree2 = new BST<Integer>();
        assertEquals(true, tree2.isEmpty());
    }

    /**
     * Tests the levels method to ensure that the tree has the correct amount of
     * levels, starting with level 0 and on
     */
    public void testLevels()
    {
        // Creates three Integers
        Integer middle = new Integer(1);
        Integer left = new Integer(0);
        Integer right = new Integer(2);

        // Inserts them into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Checks to see how many levels the tree has, which should be 2
        assertEquals(2, tree.levels());

        // Creates another Integer and inserts it into the tree, creating
        // another level
        Integer z = new Integer(10);
        tree.insert(z);

        // Checks to see if the levels has been updated
        assertEquals(3, tree.levels());

        // Case for an empty tree, which has nothing inserted
        BST<Integer> tree2 = new BST<Integer>();
        assertEquals(0, tree2.levels());
    }

    /**
     * Tests the remove method by inserting Integers into the tree and then
     * removing them. Ensures that the Integers are being removed and cannot be
     * found in the tree.
     */
    public void testRemove()
    {
        // Creates seven Integers
        Integer a = new Integer(0);
        Integer b = new Integer(1);
        Integer c = new Integer(2);
        Integer d = new Integer(3);
        Integer e = new Integer(4);
        Integer f = new Integer(5);
        Integer g = new Integer(6);
        Integer neverInserted = new Integer(9);
        Integer nullInteger = null;

        // Inserts them into the tree
        tree.insert(d);
        tree.insert(b);
        tree.insert(f);
        tree.insert(a);
        tree.insert(c);
        tree.insert(e);
        tree.insert(g);

        // The tree has seven Integers and three levels, so it is not empty
        assertEquals(false, tree.isEmpty());
        assertEquals(3, tree.levels());

        // Checks all the nodes of the BST before removing anything
        assertEquals(tree.getRoot().getElement(), d);
        assertEquals(tree.getRoot().getLeft().getLeft().getElement(), a);
        assertEquals(tree.getRoot().getLeft().getElement(), b);
        assertEquals(tree.getRoot().getLeft().getRight().getElement(), c);
        assertEquals(tree.getRoot().getRight().getLeft().getElement(), e);
        assertEquals(tree.getRoot().getRight().getElement(), f);
        assertEquals(tree.getRoot().getRight().getRight().getElement(), g);

        // Remove b from the tree
        tree.remove(b);

        // Tree still has 3 levels, even after removal of b
        assertEquals(3, tree.levels());

        // Check all the nodes of the BST after removing b
        assertEquals(tree.getRoot().getElement(), d);
        assertEquals(tree.getRoot().getLeft().getElement(), c);
        assertEquals(tree.getRoot().getLeft().getLeft().getElement(), a);
        assertEquals(tree.getRoot().getRight().getLeft().getElement(), e);
        assertEquals(tree.getRoot().getRight().getElement(), f);
        assertEquals(tree.getRoot().getRight().getRight().getElement(), g);

        // Remove f from the tree
        tree.remove(f);

        // Tree still has 3 levels, even after removal of f
        assertEquals(3, tree.levels());

        // Check all the nodes of the BST after removing f
        assertEquals(tree.getRoot().getElement(), d);
        assertEquals(tree.getRoot().getLeft().getLeft().getElement(), a);
        assertEquals(tree.getRoot().getLeft().getElement(), c);
        assertEquals(tree.getRoot().getRight().getElement(), g);
        assertEquals(tree.getRoot().getRight().getLeft().getElement(), e);
        assertEquals(false, tree.remove(nullInteger));
        assertEquals(false, tree.remove(neverInserted));

        // Case for an empty tree, which has nothing inserted
        BST<Integer> tree2 = new BST<Integer>();
        tree2.insert(a);
        assertEquals(true, tree2.remove(a));
        assertEquals(null, tree2.getRoot());

        tree.remove(d);
        tree.remove(f);
        tree.remove(a);
        tree.remove(c);
        tree.remove(e);
        tree.remove(g);
        assertEquals(true, tree.isEmpty());
        assertEquals(null, tree.getRoot());
    }

    /**
     * Tests that the iterator is created properly and the method inside of it
     * works
     */
    public void testIterator()
    {
        Integer integer1 = new Integer(1);
        tree.insert(integer1);

        Iterator<Integer> iterator = tree.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(integer1, iterator.next());
        assertEquals(false, iterator.hasNext());

        tree.remove(integer1);
        assertEquals(true, tree.isEmpty());
        assertEquals(null, iterator.next());
    }

    /**
     * Tests that the correct depth is retrieved from a passed in integer
     */
    public void testGetDepthFromInteger()
    {
        // Creates three Integers
        Integer middle = new Integer(1);
        Integer left = new Integer(0);
        Integer right = new Integer(2);

        // Inserts them into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        assertEquals(1, tree.getDepthFromHandle(left));
        assertEquals(1, tree.getDepthFromHandle(right));
    }
}
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
     * Declares our tree that can hold Handles
     */
    private BST<Handle> tree;

    /**
     * Constructor for the class that creates the tree object that holds
     * Handles
     */
    public void setUp()
    {
        tree = new BST<Handle>();
    }

    /**
     * Tests the clear method by inserting a Handle into the tree and then
     * removing it
     */
    public void testClear()
    {
        // Create Handle
        Handle firstHandle = new Handle(0, 0);

        // Inserts Handle into the tree
        tree.insert(firstHandle);

        // Tree is not empty because it contains a Handle
        assertEquals(false, tree.isEmpty());

        // Clear the tree
        tree.clear();

        // Check to see if the tree is empty after clearing it
        assertEquals(true, tree.isEmpty());
    }

    /**
     * Tests the equals method by checking that two trees with the same nodes
     * should be equal
     */
    public void testEquals()
    {
        // Creates a tree with nodes and inserts them
        Handle middle = new Handle(1, 1);
        Handle left = new Handle(0, 0);
        Handle right = new Handle(2, 2);

        // Inserts Handles into one tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Creates another tree with identical nodes
        BST<Handle> tree2 = new BST<Handle>();

        // Inserts Handles into another tree
        tree2.insert(middle);
        tree2.insert(left);
        tree2.insert(right);

        // Check to see if the two trees are equal and contain the same nodes
        assertEquals(true, tree.equals(tree2));
    }

    /**
     * Tests the find method by inserting Handles into the tree and verifying
     * that they can be found when called
     */
    public void testFind()
    {
        // Creates three Handles
        Handle middle = new Handle(1, 1);
        Handle left = new Handle(0, 0);
        Handle right = new Handle(2, 2);

        // Inserts Handles into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Ensure that the middle node contains the
        // "middle" Handle
        assertEquals(middle, tree.find(middle));

        // Remove the "middle" Handle from the tree
        tree.remove(middle);

        // Verify that the Handle is gone
        assertEquals(null, tree.find(middle));

        // Create a new Handle, but never insert it into the tree
        Handle hello = new Handle(9, 9);

        // Verify that the Handle is not in the tree because
        // it was never inserted in the first place
        assertEquals(null, tree.find(hello));
    }

    /**
     * Tests the insert method by seeing if the Handle nodes are in the
     * correct position in the tree
     */
    public void testInsert()
    {
        // Creates three Handles
        Handle middle = new Handle(1, 1);
        Handle left = new Handle(0, 0);
        Handle right = new Handle(2, 2);

        // Inserts Handles into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Verifies that the tree nodes are in the correct position
        assertEquals(middle, tree.root.element);
        assertEquals(left, tree.root.left.element);
        assertEquals(right, tree.root.right.element);
    }

    /**
     * Tests if the Handles are actually being inserted into the tree
     */
    public void testInsertBoolean()
    {
        // Creates three Handles
        Handle firstHandle = new Handle(0, 0);
        Handle secondHandle = new Handle(1, 1);
        Handle thirdHandle = new Handle(2, 2);

        // Inserts them into the tree
        Boolean insertedA = tree.insert(firstHandle);
        Boolean insertedB = tree.insert(secondHandle);
        Boolean insertedC = tree.insert(thirdHandle);

        // Checks that the Handles have been inserted
        assertEquals(true, insertedA.booleanValue());
        assertEquals(true, insertedB.booleanValue());
        assertEquals(true, insertedC.booleanValue());
    }

    /**
     * Tests if the tree has a next node, which means the stack is not empty
     */
    public void testIsEmpty()
    {
        // Creates Handle
        Handle firstHandle = new Handle(0,0);

        // Inserts them into the tree
        tree.insert(firstHandle);

        // Checks to see if the tree is empty, which it is not
        assertEquals(false, tree.isEmpty());

        // Remove Handle from the BST
        tree.remove(firstHandle);

        // Now the tree should be empty
        assertEquals(true, tree.isEmpty());
    }

    /**
     * Tests the levels method to ensure that the tree has the correct amount of
     * levels, starting with level 0 and on
     */
    public void testLevels()
    {
        // Creates three Handles
        Handle middle = new Handle(1, 1);
        Handle left = new Handle(0, 0);
        Handle right = new Handle(2, 2);

        // Inserts them into the tree
        tree.insert(middle);
        tree.insert(left);
        tree.insert(right);

        // Checks to see how many levels the tree has, which should be 2
        assertEquals(2, tree.levels());

        // Creates another Handle and inserts it into the tree, creating
        // another level
        Handle z = new Handle(10, 10);
        tree.insert(z);

        // Checks to see if the levels has been updated
        assertEquals(3, tree.levels());
    }

    /**
     * Tests the remove method by inserting Handles into the tree and then
     * removing them. Ensures that the Handles are being removed and cannot
     * be found in the tree.
     */
    public void testRemove()
    {
        // Creates seven Handles
        Handle a = new Handle(0, 0);
        Handle b = new Handle(1, 1);
        Handle c = new Handle(2, 2);
        Handle d = new Handle(3, 3);
        Handle e = new Handle(4, 4);
        Handle f = new Handle(5, 5);
        Handle g = new Handle(6, 6);

        // Inserts them into the tree
        tree.insert(d);
        tree.insert(b);
        tree.insert(f);
        tree.insert(a);
        tree.insert(c);
        tree.insert(e);
        tree.insert(g);

        // The tree has seven Handles and three levels, so it is not empty
        assertEquals(false, tree.isEmpty());
        assertEquals(3, tree.levels());

        // Checks all the nodes of the BST before removing anything
        assertEquals(tree.root.element, d);
        assertEquals(tree.root.left.left.element, a);
        assertEquals(tree.root.left.element, b);
        assertEquals(tree.root.left.right.element, c);
        assertEquals(tree.root.right.left.element, e);
        assertEquals(tree.root.right.element, f);
        assertEquals(tree.root.right.right.element, g);

        // Remove b from the tree
        tree.remove(b);

        // Tree still has 3 levels, even after removal of b
        assertEquals(3, tree.levels());

        // Check all the nodes of the BST after removing b
        assertEquals(tree.root.element, d);
        assertEquals(tree.root.left.element, c);
        assertEquals(tree.root.left.left.element, a);
        assertEquals(tree.root.right.left.element, e);
        assertEquals(tree.root.right.element, f);
        assertEquals(tree.root.right.right.element, g);

        // Remove f from the tree
        tree.remove(f);

        // Tree still has 3 levels, even after removal of f
        assertEquals(3, tree.levels());

        // Check all the nodes of the BST after removing f
        assertEquals(tree.root.element, d);
        assertEquals(tree.root.left.left.element, a);
        assertEquals(tree.root.left.element, c);
        assertEquals(tree.root.right.element, g);
        assertEquals(tree.root.right.left.element, e);
    }
}
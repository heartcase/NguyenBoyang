import java.util.Iterator;
import java.util.Stack;

/**
 * Class that has the implementations for a binary search tree and allows us to
 * traverse through the tree by utilization of an iterator.
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 * @param <T>
 */
public class BST<T extends Comparable<? super T>> implements Iterable<T>
{
    /**
     * BinaryNode root element
     */
    protected BinaryNode root;
    /**
     * Size
     */
    public int size;

    /**
     * Returns BST Iterator
     * 
     * @return BST Iterator
     */
    public Iterator<T> iterator()
    {
        return new BSTIterator<T>();
    }

    @SuppressWarnings("hiding")
    public class BSTIterator<T> implements Iterator<T>
    {
        /**
         * Stack object for the BST
         */
        public Stack<BinaryNode> stack;

        /**
         * Constructor for the BST Iterator to create a new stack and push the
         * root to left
         */
        public BSTIterator()
        {
            stack = new Stack<BinaryNode>();
            pushLeft(root);
        }

        /**
         * This is the helper method for next. Goes all the way to the left of
         * three
         */
        private void pushLeft(BinaryNode node)
        {
            while (node != null)
            {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Checks to see if the iterator has a next element, meaning that the
         * stack is not empty
         */
        @Override
        public boolean hasNext()
        {
            return (!this.stack.isEmpty());
        }

        /**
         * Allows us to get the next element in the stack
         */
        @SuppressWarnings("unchecked")
        @Override
        public T next()
        {
            if (stack.isEmpty())
            {
                return null;
            }
            BinaryNode node = stack.pop();
            pushLeft(node.right);
            return (T) node.getElement();
        }
    }

    /**
     * BinaryNode class that contains methods to set the nodes in the tree
     * accordingly
     */
    class BinaryNode
    {
        /**
         * The data in the node
         */
        T element;

        /**
         * Pointer to the left child
         */
        BinaryNode left;

        /**
         * Pointer to the right child
         */
        BinaryNode right;

        /**
         * Initialize a childless binary node
         * 
         * Pre: elem is not null
         * 
         * Post: (in the new node)
         * 
         * element == elem left == right == null
         * 
         * @param elem
         *            - element
         */
        public BinaryNode(T elem)
        {
            element = elem;
            left = right;
            right = null;
        }

        /**
         * Initializes a binary node with children
         * 
         * Pre: elem is not null
         * 
         * Post: (in the new node)
         * 
         * element == elem left = lt, right = rt
         * 
         * @param elem
         *            the element of the parent node
         * @param lt
         *            left node
         * @param rt
         *            right node
         */
        public BinaryNode(T elem, BinaryNode lt, BinaryNode rt)
        {
            element = elem;
            left = lt;
            right = rt;
        }

        /**
         * Sets the left node equal to node
         * 
         * @param node
         *            - BinaryNode
         */
        public void setLeft(BinaryNode node)
        {
            left = node;
        }

        /**
         * Sets the right node equal to node
         * 
         * @param node
         *            - BinaryNode
         */
        public void setRight(BinaryNode node)
        {
            right = node;
        }

        /**
         * Getter method to retrieve the element in the node
         * 
         * @return - element in the node
         */
        public T getElement()
        {
            return element;
        }
    }

    /**
     * This initializes an empty BST. This is the constructor for the BST.
     */
    public BST()
    {
        size = 0;
        root = null;
    }

    /**
     * This checks if and only if the tree has no nodes
     * 
     * @return true returns true if the the BST is empty
     */
    public boolean isEmpty()
    {
        return (root == null);
    }

    /**
     * This is the main method for find. It calls the helper method if f is not
     * null. It returns the pointer to matching data element, or null if no
     * matching element exists in the BST. Matching should be tested using the
     * data object's compareTo method.
     * 
     * Pre: f is null or points to a valid object of type T
     * 
     * Post: the binary tree is unchanged
     * 
     * @param f
     *            is null or points to a valid object of type T
     * @return pointer to matching data element or null if no match
     */
    public T find(T f)
    {
        if (f == null)
        {
            return null;
        }
        return (find(root, f));
    }

    /**
     * This is the helper method for find method It checks if the element of the
     * node matches the pointer.
     * 
     * @param rootNode
     * @param entry
     * @return returns the data element
     */
    private T find(BinaryNode rootNode, T entry)
    {
        if (rootNode == null)
        {
            return null;
        }
        int compResult = entry.compareTo(rootNode.element);

        if (compResult < 0)
        {
            return (find(rootNode.left, entry));
        }
        else if (compResult > 0)
        {
            return (find(rootNode.right, entry));
        }
        else
        {
            return rootNode.element;
        }
    }

    /**
     * Insert element b into BST, unless it is already stored. Returns true if
     * insertion is performed and false otherwise
     * 
     * Pre: b is not null or points to a valid object of type T
     * 
     * Post: the binary tree contains b
     * 
     * @param b
     *            is null or points to a valid object of type T
     * @return boolean to see if insertion was performed
     */
    public boolean insert(T b)
    {
        if (b == null)
        {
            return false;
        }
        else
        {
            root = insert(root, b);
            size++;
        }
        return true;
    }

    /**
     * Helper method for insert. Inserts a node at the entry.
     * 
     * @param rootNode
     * @param entry
     * @return returns the node that is being added
     */
    private BinaryNode insert(BinaryNode rootNode, T entry)
    {
        if (rootNode != null)
        {
            int result = entry.compareTo(rootNode.element);

            if (result <= 0)
            {
                rootNode.setLeft(insert((rootNode.left), entry));
            }
            else if (result > 0)
            {
                rootNode.setRight(insert((rootNode.right), entry));
            }
            return rootNode;
        }
        rootNode = new BinaryNode(entry);
        return rootNode;
    }

    /**
     * Deletes element matching n from the BST, if present. Returns true if
     * matching element is removed from tree, false otherwise
     * 
     * Pre: n is not null, or points to a valid object of type T
     * 
     * Post: the binary tree does not contain n
     * 
     * @param n
     *            matches the element to be removed
     * @return true when remove is successful
     */
    public boolean remove(T n)
    {
        if (n == null)
        {
            return false;
        }
        else
        {
            if (find(n) == null)
            {
                return false;
            }
            root = remove(root, n);
            size--;
        }
        return true;
    }

    /**
     * This is the helper method for remove
     * 
     * @param rootNode
     *            this is the node that is being removed
     * @param entry
     *            this is the element we want to remove
     * @return returns the node
     */
    private BinaryNode remove(BinaryNode rootNode, T entry)
    {
        // If there's no more subtree to examine
        if (rootNode == null)
        {
            return null;
        }
        int compResult = entry.compareTo(rootNode.element);

        // If value should be to the left of the root
        if (compResult < 0)
        {
            rootNode.left = remove(rootNode.left, entry);
        }
        // If value should be to the right of the root
        else if (compResult > 0)
        {
            rootNode.right = remove(rootNode.right, entry);
        }
        else if ((rootNode.left != null) && (rootNode.right != null))
        {
            BinaryNode temp = findNodeRep(rootNode.right);
            temp.right = remove(rootNode.right, temp.element);
            temp.left = rootNode.left;
            return temp;
        }
        // If there is only one child on the left
        else if (rootNode.left != null)
        {
            rootNode = rootNode.left;
        }
        // If there is only one child on the right
        else
        {
            rootNode = rootNode.right;
        }
        return rootNode;
    }

    /**
     * This is the helper method for the remove method. Searches for the minimum
     * node
     * 
     * @param node
     * @return the node
     */
    private BinaryNode findNodeRep(BinaryNode node)
    {
        if (node.left == null)
        {
            return node;
        }
        return findNodeRep(node.left);
    }

    /**
     * This method clears the whole tree. Returns the tree to an empty state
     * 
     * Pre: none
     * 
     * Post: the binary tree contains no elements
     */
    public void clear()
    {
        root = null;
    }

    /**
     * Return true if other is a BST that has the same physical structure and
     * stores equal data values in corresponding nodes. "Equal" should be tested
     * using the data object's equals() method.
     * 
     * Pre: other is null or points to a valid BST<> object, instantiated on the
     * same data type as the tree on which equals() is invoked
     * 
     * Post: both binary trees are unchanged
     * 
     * @param other
     *            this is being compared to the object
     * @return false if the object and other are not equal
     */
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other == this)
        {
            return true;
        }
        else if (this.getClass().equals(other.getClass()))
        {
            @SuppressWarnings("unchecked")
            BST<T> tree = (BST<T>) other;
            return (equals(tree.root, this.root));
        }
        return false;
    }

    /**
     * This is the helper method for the equals method compares two nodes, its
     * elements and its children
     * 
     * @param aNode
     *            First node to be compared
     * @param bNode
     *            Second node that is being compared (to aNode)
     * @return true if the two nodes are equal
     */
    private boolean equals(BinaryNode aNode, BinaryNode bNode)
    {
        if ((aNode == null) && (bNode == null))
        {
            return true;
        }
        if ((aNode == null && bNode != null)
                || (aNode != null && bNode == null))
        {
            return false;
        }
        if (aNode.element != bNode.element)
        {
            return false;
        }
        return (equals(aNode.left, bNode.left)
                && equals(aNode.right, bNode.right));
    }

    /**
     * This method returns the level of the tree. Returns the number of levels
     * in the tree. An empty tree has 0 levels
     * 
     * Pre: tree is a valid BST<> object Post: the binary tree is unchanged
     * 
     * Post: reports the number of levels in the tree
     * 
     * @return the number of levels in a tree
     */
    public int levels()
    {
        if (isEmpty())
        {
            return 0;
        }
        else
        {
            return levels(root);
        }
    }

    /**
     * This is the helper method for levels.
     * 
     * @param aNode
     * @return the height of the tree
     */
    private int levels(BinaryNode aNode)
    {
        int heightLeft = 0;
        int heightRight = 0;
        if (aNode.left != null)
        {
            heightLeft = levels(aNode.left);
        }
        if (aNode.right != null)
        {
            heightRight = levels(aNode.right);
        }
        if (heightLeft <= heightRight)
        {
            return heightRight + 1;
        }
        else
        {
            return heightLeft + 1;
        }
    }
    
    public int getDepthFromHandle(T handle)
    {
        int counter = 0;
        while ((find(handle)))
        return size;
        
    }
}
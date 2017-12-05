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
    private int myDepth = 0;

    /**
     * BinaryNode root element
     */
    private BinaryNode root;

    /**
     * @return the root
     */
    protected BinaryNode getRoot()
    {
        return root;
    }

    /**
     * Size
     */
    private int size;

    /**
     * Returns BST Iterator
     * 
     * @return BST Iterator
     */
    public Iterator<T> iterator()
    {
        return new BSTIterator<T>();
    }

    /**
     * Iterator class
     * 
     * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
     * @version 11.15.2017
     * @param <T>
     */
    @SuppressWarnings("hiding")
    public class BSTIterator<T> implements Iterator<T>
    {
        /**
         * Stack object for the BST
         */
        private Stack<BinaryNode> stack;

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
        private T element;

        /**
         * Pointer to the left child
         */
        private BinaryNode left;

        /**
         * Pointer to the right child
         */
        private BinaryNode right;

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

        /**
         * Getter method to retrieve the left element in the node
         * 
         * @return - left element in the node
         */
        public BST<T>.BinaryNode getLeft()
        {
            return left;
        }

        /**
         * Getter method to retrieve the right element in the node
         * 
         * @return - right element in the node
         */
        public BST<T>.BinaryNode getRight()
        {
            return right;
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
            myDepth++;
            return (find(rootNode.left, entry));
        }
        else if (compResult > 0)
        {
            myDepth++;
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
            else
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
    protected BinaryNode remove(BinaryNode rootNode, T entry)
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
            rootNode.element = temp.element;
            remove(temp, temp.element);
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
     * Get the depth in the BST at which a specific handle is at
     * 
     * @param handle
     *            - handle to inspect
     * @return - the depth of that handle
     */
    public int getDepthFromHandle(T handle)
    {
        myDepth = 0;
        find(handle);
        return myDepth;
    }
}
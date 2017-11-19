public class BSTNode<T extends Comparable<T>>
{
    private T key;
    private BSTNode<T> left;
    private BSTNode<T> right;
    
    public BSTNode(T k)
    {
        key = k;
    }

    /**
     * @return the left
     */
    protected BSTNode<T> getLeft()
    {
        return left;
    }

    /**
     * @param left the left to set
     */
    protected void setLeft(BSTNode<T> l)
    {
        left = l;
    }

    /**
     * @return the right
     */
    protected BSTNode<T> getRight()
    {
        return right;
    }

    /**
     * @param right the right to set
     */
    protected void setRight(BSTNode<T> r)
    {
        right = r;
    }

    /**
     * @return the key
     */
    protected T getKey()
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    protected void setKey(T k)
    {
        key = k;
    }
    
    
}

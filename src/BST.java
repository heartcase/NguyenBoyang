import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class BST<T extends Comparable<T>>
{
    private BSTNode<T> root;
    
    public BST()
    {
        root = new BSTNode<>(null);
        
    }
    
    public void insert(BSTNode<T> node, T value) {
        // TODO
    }
    
    public void remove(BSTNode<T> node, T value) {
        // TODO
    }
    
    public BSTNode<T> search(BSTNode<T> node, T value) {
        // TODO        
        return null;
    }
    
    public void rangeSearch(BSTNode<T> node, T value, List<BSTNode<T>> list) {
        // TODO
    }
    
    
}

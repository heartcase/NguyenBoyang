import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class BSTTest
{

    private void insert(BST<Integer> bst, int m)
    {
        for (int i = 0; i < m; i++)
        {
            bst.insert((int) (Math.random() * 100));
        }
    }

    private void insert(BST<Integer> bst, int m, List<Integer> list)
    {
        for (int i = 0; i < m; i++)
        {
            Integer integer = new Integer((int) (Math.random() * 100));
            bst.insert(integer);
            list.add(integer);
        }
    }

    @Test
    public void testIterator()
    {
        BST<Integer> bst = new BST<>();
        insert(bst, 3114);
        Iterator<Integer> iterator = bst.iterator();
        int sum = 0;
        while (true)
        {
            if (iterator.hasNext())
            {
                iterator.next();
                sum++;
            }
            else
            {
                assertEquals(iterator.next(), null);
                break;
            }
        }
        bst.insert(3);
        bst.insert(3);
        assertEquals(sum, 3114);
    }

    @Test
    public void testBST()
    {
        BST<Integer> bst = new BST<>();
        assertEquals(bst.getClass(), BST.class);
    }

    @Test
    public void testIsEmpty()
    {
        BST<Integer> bst = new BST<>();
        assertEquals(bst.isEmpty(), true);
        bst.insert(1);
        assertEquals(bst.isEmpty(), false);
    }

    @Test
    public void testFind()
    {
        BST<Integer> bst = new BST<>();
        assertEquals(bst.find(1), null);
        assertEquals(bst.find(null), null);
        Integer integer = new Integer(5);
        bst.insert(integer);
        assertEquals(bst.find(4), null);
        assertEquals(bst.find(6), null);
        assertEquals(bst.find(5), integer);
    }

    @Test
    public void testInsert()
    {
        BST<Integer> bst = new BST<>();
        Integer integer = new Integer(1);
        bst.insert(null);
        assertEquals(bst.getRoot(), null);
        bst.insert(integer);
        assertEquals(bst.getRoot().getElement(), integer);
    }

    @Test
    public void testRemove()
    {
        BST<Integer> bst = new BST<>();
        List<Integer> list = new ArrayList<>();
        bst.remove(null);
        bst.remove(200);
        insert(bst, 2000, list);
        bst.remove(bst.getRoot(), 200);
        for (Integer i : list)
        {
            bst.remove(i);
        }
        bst.remove(200);
        bst.remove(bst.getRoot(), null);
        assertEquals(bst.isEmpty(), true);
        bst.insert(10);
        bst.insert(5);
        bst.remove(10);
        assertEquals(bst.getRoot().getElement().intValue(), 5);
        bst.insert(4);
        bst.insert(3);
        bst.remove(3);
        bst.remove(4);
        assertEquals(bst.getRoot().getElement().intValue(), 5);
    }

    @Test
    public void testClear()
    {
        BST<Integer> bst = new BST<>();
        insert(bst, 1000);
        assertEquals(bst.isEmpty(), false);
        bst.clear();
        assertEquals(bst.isEmpty(), true);
    }

    @Test
    public void testBSTNode()
    {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        assertEquals(bst.getRoot().getLeft(), null);
        assertEquals(bst.getRoot().getRight(), null);
    }

    @Test
    public void testGetDepthFromHandle()
    {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        assertEquals(bst.getDepthFromHandle(10), 0);
    }

}

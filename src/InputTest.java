import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;

/**
 * Test class to ensure that all the methods in the Input class work as expected
 * and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */
public class InputTest
{

    /**
     * Test the Input Class constructor
     */
    @Test
    public void testInput()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        assertEquals(input.getClass(), Input.class);
    }

    /**
     * Tests that the readLine function is able to parse information from a file
     * 
     * @throws IOException
     */
    @Test
    public void testReadLine() throws IOException
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.readLine("P4_Input1_Sample");
        assertEquals(h1.getSize(), 258);
    }

    /**
     * Tests that the specified artist is removed from the appropriate BST and
     * corresponding marks are made in the memory
     */
    @Test
    public void testRemoveArtist()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.insert("abc", "dff");
        input.insert("acb", "def");
        input.removeArtist("abc");
        assertEquals(h1.getSize(), 1);
        assertEquals(h2.getSize(), 1);
        input.removeArtist("def");
        assertEquals(h1.getSize(), 1);
        assertEquals(h2.getSize(), 1);
        input.removeArtist("acb");
        assertEquals(h1.getSize(), 0);
        assertEquals(h2.getSize(), 0);

    }

    /**
     * Tests that the specified song is removed from the appropriate BST and
     * corresponding marks are made in the memory
     */
    @Test
    public void testRemoveSong()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.insert("abc", "dff");
        input.insert("acb", "def");
        input.removeSong("def");
        assertEquals(h1.getSize(), 1);
        assertEquals(h2.getSize(), 1);
        input.removeSong("abc");
        assertEquals(h1.getSize(), 1);
        assertEquals(h2.getSize(), 1);
        input.removeSong("dff");
        assertEquals(h1.getSize(), 0);
        assertEquals(h2.getSize(), 0);
    }

    /**
     * Tests that there is a complete listing of the artists contained in the
     * database
     */
    @Test
    public void testPrintArtist()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.printSong();
        assertEquals(h1.getSize(), 1);
    }

    /**
     * Tests that there is a complete listing of the songs contained in the
     * database
     */
    @Test
    public void testPrintSong()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.printSong();
        assertEquals(h2.getSize(), 1);
    }

    /**
     * Tests that an in-order traversal of the BST tree is printed
     */
    @Test
    public void testPrintTree()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.printTree();
        assertEquals(h1.getSize(), 1);
        assertEquals(h2.getSize(), 1);
    }

    /**
     * Tests that all songs recorded by the artist with a specified name is
     * listed
     */
    @Test
    public void testListArtist()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.listArtist("abc");
        input.listArtist("def");
        assertEquals(h1.getSize(), 1);
    }

    /**
     * Tests that all artists who have recorded the specified song is listed
     */
    @Test
    public void testListSong()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.listSong("abc");
        input.listSong("def");
        assertEquals(h2.getSize(), 1);
    }

    /**
     * Tests that a specific record for a particular song by a particular artist
     * is removed from the BST
     */
    @Test
    public void testDelete()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        input.delete("abc", "def");
        assertEquals(h1.getSize(), 0);
        assertEquals(h2.getSize(), 0);
    }

    /**
     * Tests that a artist/song is inserted into the appropriate artist/song
     * hashTable
     */
    @Test
    public void testInsert()
    {
        Memory m = new Memory(1024);
        BST<KVPair<Integer, Integer>> b1 = new BST<>();
        BST<KVPair<Integer, Integer>> b2 = new BST<>();
        HashTable h1 = new HashTable(100);
        HashTable h2 = new HashTable(100);
        Input input = new Input(m, b1, b2, h1, h2);
        input.insert("abc", "def");
        assertEquals(h1.getSize(), 1);
        assertEquals(h2.getSize(), 1);
    }
}
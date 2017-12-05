import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class InputTest
{

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

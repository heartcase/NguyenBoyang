import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest
{

    @Test
    public void testHashTable()
    {
        HashTable hashTable = new HashTable(1024);
        assertEquals(hashTable.getClass(), HashTable.class);
    }

    @Test
    public void testInsert()
    {
        Memory memory = new Memory(1024);
        HashTable hashTable = new HashTable(4);
        assertEquals(hashTable.getHashArray().length, 0);
        hashTable.insert(3, memory);
    }

    @Test
    public void testSearch()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRemove()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetHandle()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetHashArray()
    {
        fail("Not yet implemented");
    }

}

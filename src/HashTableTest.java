import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest
{

    @Test
    public void testHashTable()
    {
        HashTable hashTable = new HashTable(1024);
        assertEquals(hashTable.getClass(), HashTable.class);
        assertEquals(hashTable.getHashArray().length, 1024);
    }

    @Test
    public void testInsert()
    {
        HashTable hashTable = new HashTable(4);
        Memory memory = new Memory(1024);
        int j = 0;
        int i = memory.add(j, "Hello World");
        hashTable.insert(0, memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(j, memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(j, memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(j, memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(j, memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(j, memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(j, memory);
        i = memory.add(i, "CS 3114" + i);

        assertEquals(hashTable.getSize(), 7);
    }

    @Test
    public void testSearch()
    {
        Memory memory = new Memory(1024);
        memory.add(0, "Hello World");
        HashTable hashTable = new HashTable(4);
        hashTable.insert(0, memory);
        assertEquals(
                hashTable.getHandle((hashTable.search("Hello World", memory)))
                        .getIndex(),
                0);
        assertEquals(hashTable.getHandle((hashTable.search("BAD WORD", memory)))
                .getIndex(), -1);

    }

    @Test
    public void testRemove()
    {
        Memory memory = new Memory(1024);
        memory.add(0, "Hello World");
        HashTable hashTable = new HashTable(4);
        hashTable.insert(0, memory);
        assertEquals(hashTable.getSize(), 1);
        hashTable.remove("Hello World>", memory);
        hashTable.remove("Hello World", memory);
        assertEquals(hashTable.getSize(), 0);
    }
}

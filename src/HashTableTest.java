import student.TestCase;

/**
 * Test class to ensure that all the methods in the HashTable class work as
 * expected and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */
public class HashTableTest extends TestCase
{
    /**
     * Tests to see if a HashTable object is able to be created
     */
    public void testHashTable()
    {
        HashTable hashTable = new HashTable(1024);
        assertEquals(hashTable.getClass(), HashTable.class);
        assertEquals(hashTable.getHashArray().length, 1024);
    }

    /**
     * Tests to see if a Handle is able to be added to the HashArray
     */
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

    /**
     * Tests to see if the correct hash index is returned from a given string
     */
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

    /**
     * Tests to see if a Handle is properly removed from the HashArray
     */
    public void testRemove()
    {
        Memory memory = new Memory(1024);
        memory.add(0, "Hello World");
        HashTable hashTable = new HashTable(4);
        hashTable.insert(0, memory);
        assertEquals(hashTable.getSize(), 1);
        hashTable.remove("Hello World>", memory);
        hashTable.remove("Hello World", memory);
        assertEquals(hashTable.getSize(), 1);
    }
}

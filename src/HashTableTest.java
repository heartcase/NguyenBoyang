import student.TestCase;

/**
 * Class where we create test cases for all of the methods in the HashTable
 * class. We verify that each method is functional and acts accordingly as
 * expected.
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class HashTableTest extends TestCase
{
    /**
     * Insert comment
     */
    public void testInsertHandleMemory()
    {
        HashTable hashTable = new HashTable(4);
        Memory memory = new Memory(1024);
        int j = 0;
        int i = memory.add(j, "Hello World");
        hashTable.insert(new Handle(0, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        i = memory.add(i, "CS 3114" + i);

        assertEquals(hashTable.search("Hello World", memory),
                hashTable.hash("Hello World", 16));
        assertEquals(hashTable.search("BAD VALUE", memory), -1);
        assertEquals(hashTable.getHashArray().length, 32);

    }

    /**
     * Insert comment
     */
    public void testSearchIntIntMemory()
    {
        HashTable hashTable = new HashTable(4);
        Memory memory = new Memory(1024);
        int j = 0;
        int i = memory.add(j, "Hello World");
        int k;
        hashTable.insert(new Handle(0, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        j = i;
        i = memory.add(j, "CS 3114" + i);
        hashTable.insert(new Handle(j, 0), memory);
        k = j;
        j = i;
        i = memory.add(j, "CS 3114" + i);

        assertEquals(
                hashTable.getHandle(hashTable.search(k, 0, memory)).getValue(),
                0);
        assertEquals(
                hashTable.getHandle(hashTable.search(0, k, memory)).getValue(),
                -1);
    }

    /**
     * Insert comment
     */
    public void testSearchStringStringMemory()
    {
        HashTable hashTable = new HashTable(4);
        Memory memory = new Memory(1024);
        int j = 0;
        memory.add(j, "Hello World");
        hashTable.insert(new Handle(0, 0), memory);
        assertEquals(hashTable
                .getHandle(
                        hashTable.search("Hello World", "Hello World", memory))
                .getValue(), 0);
        assertEquals(hashTable
                .getHandle(hashTable.search("Hello World", "BAD VALUE", memory))
                .getValue(), -1);
    }

    /**
     * Insert comment
     */
    public void testRemove()
    {
        HashTable hashTable = new HashTable(4);
        Memory memory = new Memory(1024);
        int j = 0;
        memory.add(j, "Hello World");
        hashTable.insert(new Handle(0, 0), memory);
        assertEquals(hashTable
                .getHandle(
                        hashTable.search("Hello World", "Hello World", memory))
                .getValue(), 0);
        hashTable.remove(0, 0, memory);
        assertEquals(hashTable
                .getHandle(
                        hashTable.search("Hello World", "Hello World", memory))
                .getValue(), -1);
    }
}

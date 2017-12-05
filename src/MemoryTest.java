import student.TestCase;

/**
 * Class where we create test cases for all of the methods in the Memory class.
 * We verify that each method is functional and acts accordingly as expected.
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class MemoryTest extends TestCase
{

    /**
     * Tests to see if the record information is read in the memory at the given
     * address
     */
    public void testRead()
    {
        Memory memory = new Memory(16);
        int i = 0;
        int j = 0;
        i = memory.add(i, "Project 4");
        assertEquals(memory.read(j), "Project 4");
    }

    /**
     * Tests to see if a new recorded is added to the memory at the given
     * addresss
     */
    public void testAdd()
    {
        int i = 0;
        Memory memory = new Memory(16);
        i = memory.add(i, "Hello World");
        assertEquals(i, 14);
        i = memory.add(i, "CS 3114");
        assertEquals(i, 24);

    }

    /**
     * Tests to see if the record has been deleted from memory and the flag byte
     * has been deactivated as needed
     */
    public void testDelete()
    {
        int i = 0;
        int j = 0;
        Memory memory = new Memory(16);
        i = memory.add(i, "Hello World");
        assertEquals(memory.isActived(j), true);
        memory.delete(j);
        assertEquals(memory.isActived(j), false);
    }

}

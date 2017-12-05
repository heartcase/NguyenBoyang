import static org.junit.Assert.*;

/**
 * Test class to ensure that all the methods in the Memory class work as
 * expected and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */
public class MemoryTest
{
    /**
     * Test to make sure the Memory object is created properly
     */
    public void testMemory()
    {
        Memory memory = new Memory(1024);
        assertEquals(memory.getClass(), Memory.class);
    }

    /**
     * Checks to see if the record information is read at the proper given
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
     * Checks to see if a new record is added to the memory at the proper given
     * address
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
     * Checks to see if the proper record is removed from memory and that the
     * flag byte is deactivated as appropriate
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

    /**
     * Checks to see that a particular address is active/inactive
     */
    public void testIsActived()
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

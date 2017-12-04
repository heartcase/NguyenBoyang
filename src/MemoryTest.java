import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MemoryTest
{
    
    @Test
    public void testRead()
    {
        Memory memory = new Memory(16);
        int i = 0;
        int j = 0;
        i = memory.add(i, "Project 4");
        assertEquals(memory.read(j), "Project 4");
    }

    @Test
    public void testAdd()
    {
        int i = 0;
        Memory memory = new Memory(16);
        i = memory.add(i, "Hello World");
        assertEquals(i, 14);
        i = memory.add(i, "CS 3114");
        assertEquals(i, 24);

    }

    @Test
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

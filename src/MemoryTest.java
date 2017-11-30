import org.junit.Assert.*;

import org.junit.Test;

public class MemoryTest
{
    
    @Test
    public void testRead()
    {
    }

    @Test
    public void testAdd()
    {
        SongSearch.blockSize = 16;
        int i = 0;
        int j = i;
        Memory memory = new Memory(16);
        i = memory.add(i, "Hello World");
        System.out.println("position " + i + ":" + memory.read(j) + "!");
        j = i;
        i = memory.add(i, "CS 3114");
        System.out.println("position " + i + ":" + memory.read(j) + "!");
        j = i;
        i = memory.add(i, "Project 4");
        System.out.println("position " + i + ":" + memory.read(j) + "!");
        j = i;
        i = memory.add(i, "Thanksgiving");
        System.out.println("position " + i + ":" + memory.read(j) + "!");
        j = i;
        i = memory.add(i, "GGWP");
        System.out.println("position " + i + ":" + memory.read(j) + "!");
        j = i;
    }

    @Test
    public void testDelete()
    {

    }

}

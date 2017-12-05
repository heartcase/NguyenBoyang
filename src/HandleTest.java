import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class to ensure that all the methods in the Handle class work as
 * expected and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */
public class HandleTest
{
    /**
     * Tests to see if the Handle object is able to be created
     */
    @Test
    public void testHandle()
    {
        Handle handle = new Handle(0);
        assertEquals(handle.getClass(), Handle.class);
    }

    /**
     * Checks to see if the correct index is returned
     */
    @Test
    public void testGetIndex()
    {
        for (int i = 0; i < 10000; i++)
        {
            Handle handle = new Handle(i);
            assertEquals(handle.getIndex(), i);
        }
    }
}

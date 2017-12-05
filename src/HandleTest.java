import student.TestCase;

/**
 * Tests the methods in the Handle class where the Handle object can hold a
 * (key, value) pair.
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */
public class HandleTest extends TestCase
{
    /**
     * Declares our handle
     */
    private Handle myHandle;

    /**
     * Constructor for the class that creates the Handle object
     */
    public void setUp()
    {
        // Our created handle
        // 1 is the key
        // 2 is the value
        myHandle = new Handle(1, 2);
    }

    /**
     * Tests that the correct key is retrieved
     */
    public void testGetKey()
    {
        assertEquals(1, myHandle.getKey());
    }

    /**
     * Tests that the correct value is retrieved
     */
    public void testGetValue()
    {
        assertEquals(2, myHandle.getValue());
    }

    /**
     * Tests the equal method to see if the handle's key and value pair matches
     * a passed in key and value pair
     */
    public void testEqual()
    {
        assertEquals(true, myHandle.equal(1, 2));
        assertEquals(false, myHandle.equal(0, 0));
    }

    /**
     * Tests the alternate equal method to see if the handle's key matches a
     * passed in key
     */
    public void testEqualKey()
    {
        assertEquals(true, myHandle.equal(1));
        assertEquals(false, myHandle.equal(0));
    }

    /**
     * Tests the compareTo method to see if two handle objects are identical
     */
    public void testCompareTo()
    {
        Handle identicalHandle = new Handle(1, 2);
        assertEquals(0, myHandle.compareTo(identicalHandle));

        Handle notIdenticalHandle = new Handle(0, 0);
        assertEquals(1, myHandle.compareTo(notIdenticalHandle));
    }

}

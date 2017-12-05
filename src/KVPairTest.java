import static org.junit.Assert.*;

/**
 * Test class to ensure that all the methods in the KVPair class work as
 * expected and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */

public class KVPairTest
{
    private KVPair<Integer, Integer> kvPair;

    /**
     * Set up method to create the initial KVPair object
     */
    public void setUp()
    {
        kvPair = new KVPair<>(1, 2);
    }

    /**
     * Checks to see if the correct key is returned
     */
    public void testGetKey()
    {
        assertEquals(1, kvPair.getKey().intValue());
    }

    /**
     * Checks to see if the correct value is returned
     */
    public void testGetValue()
    {
        assertEquals(2, kvPair.getValue().intValue());
    }

    /**
     * Compares two KVPair objects and see if they are equal or not
     */
    public void testKVPair()
    {
        KVPair<Integer, Integer> identicalKVPair = new KVPair<>(1, 2);
        assertEquals(0, kvPair.compareTo(identicalKVPair));

        KVPair<Integer, Integer> notIdenticalKVPair = new KVPair<>(9, 9);
        assertEquals(-1, kvPair.compareTo(notIdenticalKVPair));
    }
}
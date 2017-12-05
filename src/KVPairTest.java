import student.TestCase;

/**
 * Test class to ensure that all the methods in the KVPair class work as
 * expected and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */

public class KVPairTest extends TestCase
{
    @SuppressWarnings("rawtypes")
    private KVPair kvPair;

    /**
     * Set up method to create the initial KVPair object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setUp()
    {
        kvPair = new KVPair(1, 2);
    }

    /**
     * Checks to see if the correct key is returned
     */
    public void testGetKey()
    {
        assertEquals(1, kvPair.getKey());
    }

    /**
     * Checks to see if the correct value is returned
     */
    public void testGetValue()
    {
        assertEquals(2, kvPair.getValue());
    }

    /**
     * Compares two KVPair objects and see if they are equal or not
     */
    @SuppressWarnings("unchecked")
    public void testKVPair()
    {
        @SuppressWarnings({ "rawtypes" })
        KVPair identicalKVPair = new KVPair(1, 2);
        assertEquals(0, kvPair.compareTo(identicalKVPair));

        @SuppressWarnings("rawtypes")
        KVPair notIdenticalKVPair = new KVPair(9, 9);
        assertEquals(-1, kvPair.compareTo(notIdenticalKVPair));
    }
}

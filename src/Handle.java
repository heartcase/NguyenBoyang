/**
 * Class that contains the Handle object, which has a key and value pair
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class Handle implements Comparable<Handle>
{
    /**
     * The key
     */
    private int key;

    /**
     * The value
     */
    private int value;

    /**
     * Constructor for the Handle object
     * 
     * @param k
     *            - key
     * @param v
     *            - value
     */
    public Handle(int k, int v)
    {
        key = k;
        value = v;
    }

    /**
     * Gets the key of the handle
     * 
     * @return - key
     */
    public int getKey()
    {
        return key;
    }

    /**
     * Gets the value of the handle
     * 
     * @return - handle
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Checks to see if the Handle's key, value pair is equal to the one passed
     * in
     * 
     * @param k
     *            - passed in key
     * @param v
     *            - passed in value
     * @return - true if they have identical key and value
     */
    public boolean equal(int k, int v)
    {
        return k == key && v == value;
    }

    /**
     * Checks to see if the Handle's key is equal to the one passed in
     * 
     * @param k
     *            - passed in key
     * @return - true if they have identical key
     */
    public boolean equal(int k)
    {
        return k == key;
    }

    /**
     * Compares two handle objects together
     * 
     * @param h
     *            - Handle to be compared
     * @return key, if they are equal objects
     */
    @Override
    public int compareTo(Handle h)
    {
        int compareKey = Integer.compare(key, h.key);
        int compareValue = Integer.compare(value, h.value);
        if (compareKey != 0)
        {
            return compareKey;
        }
        else
        {
            return compareValue;
        }
    }
}

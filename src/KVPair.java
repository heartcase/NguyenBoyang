/**
 * KVPair object that holds a key and value
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 * @param <K>
 *            - key
 * @param <V>
 *            - value
 */
public class KVPair<K extends Comparable<K>, V extends Comparable<V>>
        implements Comparable<KVPair<K, V>>
{
    private K key;
    private V value;

    /**
     * Getter method for the key
     * 
     * @return - the key
     */
    protected K getKey()
    {
        return key;
    }

    /**
     * Getter method for the value
     * 
     * @return - the value
     */
    protected V getValue()
    {
        return value;
    }

    /**
     * Constructor for the KVPair which holds a key and a value
     * 
     * @param k
     *            - key
     * @param v
     *            - value
     */
    public KVPair(K k, V v)
    {
        key = k;
        value = v;
    }

    /**
     * Method to compare two KVPair objects
     * 
     * @return compareValue if they are equal
     */
    @Override
    public int compareTo(KVPair<K, V> p)
    {
        int compareKey = key.compareTo(p.key);
        int compareValue = value.compareTo(p.value);
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

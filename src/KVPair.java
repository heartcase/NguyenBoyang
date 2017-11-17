/**
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class KVPair<K extends Comparable<K>, V extends Comparable<V>>
        implements ComparablePair<K, V>
{
    private K key;
    private V value;

    public KVPair(K k, V v)
    {
        key = k;
        value = v;
    }

    @Override
    public int compareTo(ComparablePair<K, V> kp)
    {
        int compareKey = getKey().compareTo(kp.getKey());
        int compareValue = getValue().compareTo(kp.getValue());
        if (compareKey != 0)
        {
            return compareKey;
        }
        else
        {
            return compareValue;
        }
    }

    @Override
    public K getKey()
    {
        return key;
    }

    @Override
    public V getValue()
    {
        return value;
    }

}

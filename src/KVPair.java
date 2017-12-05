
public class KVPair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<KVPair<K, V>>
{
    private K key;
    private V value;
    
    /**
     * @return the key
     */
    protected K getKey()
    {
        return key;
    }

    /**
     * @return the value
     */
    protected V getValue()
    {
        return value;
    }

    public KVPair(K k, V v)
    {
        key = k;
        value = v;
    }
    
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

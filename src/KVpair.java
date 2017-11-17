public class KVpair<K extends Comparable<K>, V extends Comparable<V>>
        implements ComparablePair<K, V>
{
    private K key;
    private V value;

    public KVpair(K k, V v)
    {
        key = k;
        value = v;
    }
    
    @Override
    public int compareTo(ComparablePair<K, V> kp)
    {
        int compareKey = getKey().compareTo(kp.getKey());
        int compareValue = getValue().compareTo(kp.getValue());
        if(compareKey != 0) {
            return compareKey;
        }else {
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

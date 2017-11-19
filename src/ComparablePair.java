/**
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public interface ComparablePair<K extends Comparable<K>, V extends Comparable<V>>
        extends Comparable<ComparablePair<K, V>>
{
    public int compareTo(ComparablePair<K, V> kp);

    public K getKey();

    public V getValue();
}

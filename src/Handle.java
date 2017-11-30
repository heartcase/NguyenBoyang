
public class Handle implements Comparable<Handle>
{
    int key;
    int value;
    public Handle(int k, int v)
    {
        key = k;
        value = v;
    }
    
    public int getKey() {
        return key;
    }
    
    public int getValue() {
        return value;
    }
    
    public boolean equal(int k, int v) {
        return k == key && v == value;
    }
    
    public boolean equal(int k) {
        return k == key;
    }

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

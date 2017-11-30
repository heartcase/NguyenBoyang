
public class Handle
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
}   

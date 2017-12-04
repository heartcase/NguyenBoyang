/**
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class HashTable
{
    private Handle[] hashArray;
    private int size;

    /**
     * 
     */
    public HashTable(int hashSize)
    {
        hashArray = new Handle[hashSize];
        size = 0;
    }

    /**
     * 
     * @param s
     * @param m
     * @return
     */
    public int hash(String s, int m)
    {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++)
        {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++)
            {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++)
        {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int) (Math.abs(sum) % m);
    }

    public void insert(Handle h, Memory m)
    {
        int k = h.getKey();
        String key = m.read(k);
        int hashValue;
        for (int i = 0; i < hashArray.length; i++)
        {
            hashValue = quadraticProbing(key, i, hashArray.length);
            if (hashArray[hashValue] == null)
            {
                if (size >= hashArray.length / 2)
                {
                    Handle[] temp = new Handle[hashArray.length * 2];
                    for (int j = 0; j < hashArray.length; j++)
                    {
                        if (hashArray[j] != null)
                        {
                            insert(hashArray[j], m, temp);
                        }
                    }
                    hashArray = temp;
                    insert(h, m);
                }else {
                    hashArray[hashValue] = h;
                    size++;
                }
                return;
            }
        }
    }

    public void insert(Handle h, Memory m, Handle[] array)
    {
        int k = h.getKey();
        int hashValue;
        String key = m.read(k);
        for (int i = 0; i < array.length; i++)
        {
            hashValue = quadraticProbing(key, i, array.length);
            if (array[hashValue] == null)
            {
                array[hashValue] = h;
                return;
            }
        }
    }

    /**
     * 
     * @param k
     * @param v
     * @param m
     * @return
     */
    public int search(int k, int v, Memory m)
    {
        String key = m.read(k);
        for (int i = 0; i < hashArray.length; i++)
        {
            int index = quadraticProbing(key, i, hashArray.length);
            if (hashArray[index] == null)
            {
                continue;
            }
            if (hashArray[index].equal(k, v))
            {
                return index;
            }
        }
        return -1;
    }

    public int search(String key, Memory m)
    {
        for (int i = 0; i < hashArray.length; i++)
        {
            int index = quadraticProbing(key, i, hashArray.length);
            if (hashArray[index] == null)
            {
                continue;
            }
            if (m.read(hashArray[index].getKey()).equals(key))
            {
                return index;
            }
        }
        return -1;
    }

    public int search(String key, String value, Memory m)
    {
        for (int i = 0; i < hashArray.length; i++)
        {
            int index = quadraticProbing(key, i, hashArray.length);
            if (hashArray[index] == null)
            {
                continue;
            }
            if (m.read(hashArray[index].getKey()).equals(key)
                    && m.read(hashArray[index].getValue()).equals(value))
            {
                return index;
            }
        }
        return -1;
    }

    /**
     * 
     * @param k
     * @param v
     * @param m
     */
    public void remove(int k, int v, Memory m)
    {
        int index = search(k, v, m);
        if (index != -1)
        {
            hashArray[index] = null;
        }
    }

    /**
     * 
     * @param key
     * @param k
     * @return
     */
    public int quadraticProbing(String key, int k, int length)
    {
        return (hash(key, length) + k * k) % length;
    }

    public Handle getHandle(int index)
    {
        if (index == -1)
        {
            return new Handle(-1, -1);
        }
        return hashArray[index];
    }
    
    public Handle[] getHashArray() {
        return hashArray;
    }
}

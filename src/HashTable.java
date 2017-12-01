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

    public int insert(Handle h, Memory m)
    {
        int k = h.getKey();
        String key = m.read(k);
        int hashValue = hash(key, hashArray.length);
        while (true)
        {
            if (hashArray[hashValue] != null)
            {
                hashValue = quadraticProbing(key, hashValue);
            }
            else
            {
                size++;
                if (size > hashArray.length / 2)
                {
                    Handle[] temp = new Handle[hashArray.length * 2];
                    System.arraycopy(hashArray, 0, temp, 0, hashArray.length);
                    hashArray = temp;
                }
                hashArray[hashValue] = h;
                return 1;
            }
        }
    }
    
    /**
     * 
     * @param k
     * @param v
     * @param m
     */
    public int insert(int k, int v, Memory m)
    {
        String key = m.read(k);
        int hashValue = hash(key, hashArray.length);
        while (true)
        {
            if (hashArray[hashValue] != null)
            {
                Handle temp = hashArray[hashValue];
                if (temp.equal(k, v))
                {
                    return 0;
                }
                else
                {
                    hashValue = quadraticProbing(key, hashValue);
                }
            }
            else
            {
                size++;
                // Not sure if comparison is inclusive
                if (size > hashArray.length / 2)
                {
                    Handle[] temp = new Handle[hashArray.length * 2];
                    System.arraycopy(hashArray, 0, temp, 0, hashArray.length);
                    hashArray = temp;
                }
                hashArray[hashValue] = new Handle(k, v);
                return 1;
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
            int index = quadraticProbing(key, i);
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

    /**
     * 
     * @param k
     * @param m
     * @return
     */
    public int search(int k, Memory m)
    {
        String key = m.read(k);
        for (int i = 0; i < hashArray.length; i++)
        {
            int index = quadraticProbing(key, i);
            if (hashArray[index] == null)
            {
                continue;
            }
            if (hashArray[index].equal(k))
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
            int index = quadraticProbing(key, i);
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
    public int quadraticProbing(String key, int k)
    {
        return (hash(key, hashArray.length) + k * k) % hashArray.length;
    }

}

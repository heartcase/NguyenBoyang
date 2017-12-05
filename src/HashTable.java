/**
 * 
 * Hash Table stores Handles
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 *
 */
public class HashTable
{

    /** handle array */
    private Handle[] hashArray;
    /** the number of the handles */
    private int size;

    /**
     * @return the size
     */
    protected int getSize()
    {
        return size;
    }

    /**
     * Create a new Hashtable instance take initial hash size
     * 
     * @param hashSize
     *            the initial hash size
     */
    public HashTable(int hashSize)
    {
        hashArray = new Handle[hashSize];
        size = 0;
    }

    /**
     * Insert a handle value into the array
     * 
     * @param k
     *            the handle value
     * @param m
     *            the memory
     * @return the hash index
     */
    public int insert(int k, Memory m)
    {
        int hashValue = 0;
        String key = m.read(k);
        for (int i = 0; i < hashArray.length; i++)
        {
            hashValue = quadraticProbing(key, i, hashArray.length);
            // Check if the slot is empty
            if (hashArray[hashValue] == null)
            {
                // create a new handle instance
                hashArray[hashValue] = new Handle(k);
                size++;
                // rehash if the array is half full
                // should be inclusive or exclusive
                if (size > hashArray.length / 2)
                {
                    Handle[] temp = new Handle[hashArray.length * 2];
                    for (int j = 0; j < hashArray.length; j++)
                    {
                        if (hashArray[j] != null)
                        {
                            rehash(hashArray[j], m, temp);
                        }
                    }
                    hashArray = temp;
                }
                break;
            }
        }
        // This line should never be executed
        return hashValue;
    }

    /**
     * Return the hash index of the given string
     * 
     * @param key
     *            the given string
     * @param m
     *            the memory
     * @return the hash index or -1 if not found
     */
    public int search(String key, Memory m)
    {
        for (int i = 0; i < hashArray.length; i++)
        {
            int index = quadraticProbing(key, i, hashArray.length);
            if (hashArray[index] == null)
            {
                continue;
            }
            if (m.read(hashArray[index].getIndex()).equals(key))
            {
                return index;
            }
        }
        return -1;
    }

    /**
     * Remove the handle from the array
     * 
     * @param key
     *            the key string
     * @param m
     *            the memory
     * @return the hash index
     */
    public int remove(String key, Memory m)
    {
        int index = search(key, m);
        if (index != -1)
        {
            hashArray[index] = null;
        }
        return index;
    }

    /**
     * Get the handle object from the array
     * 
     * @param index
     *            - hash array index
     * @return - return the handle object or a NULL handle with index -1
     */
    public Handle getHandle(int index)
    {
        if (index == -1)
        {
            return new Handle(-1);
        }
        return hashArray[index];
    }

    /**
     * Get the hash array
     * 
     * @return - return the hash array
     */
    public Handle[] getHashArray()
    {
        return hashArray;
    }

    // helper functions
    /**
     * reinsert the old hash array value into the new array
     * 
     * @param h
     *            the old hash array value
     * @param m
     *            the memory
     * @param array
     *            the new array
     */
    private void rehash(Handle h, Memory m, Handle[] array)
    {
        int k = h.getIndex();
        int hashValue;
        String key = m.read(k);
        for (int i = 0; i < array.length; i++)
        {
            hashValue = quadraticProbing(key, i, array.length);
            if (array[hashValue] == null)
            {
                array[hashValue] = h;
                break;
            }
        }
    }

    /**
     * Hash function
     * 
     * @param s
     *            string
     * @param m
     *            array size
     * @return hash value
     */
    private static int hash(String s, int m)
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

    /**
     * Quadratic probing function
     * 
     * @param key
     *            string
     * @param k
     *            offset
     * @param length
     *            size of array
     * @return next hash index
     */
    private static int quadraticProbing(String key, int k, int length)
    {
        return (hash(key, length) + k * k) % length;
    }

}

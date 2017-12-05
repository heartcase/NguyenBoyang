import java.util.Arrays;

/**
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class Memory
{
    private byte[] memArray;
    private int size;

    /**
     * Constructor for the class
     * 
     * @param s
     *            - size of memory
     */
    public Memory(int s)
    {
        size = s;
        memArray = new byte[size];
    }

    /**
     * Read the record information in the memory at the given address
     * 
     * @param address
     *            the first byte of the record in the address
     * @return the content
     */
    public String read(int address)
    {
        size = Integer.parseUnsignedInt(new String(
                (Arrays.copyOfRange(memArray, address + 1, address + 3))), 16);

        return new String(
                Arrays.copyOfRange(memArray, address + 3, address + 3 + size));
    }

    /**
     * Add a new record to the memory at the given address
     * 
     * @param address
     *            the first byte of the record in the address
     * @param content
     *            the information
     * @return the next writing position
     */
    public int add(int address, String content)
    {

        byte[] record = ('0' + String.format("%02X", content.length())
                + content).getBytes();
        while (address + record.length >= memArray.length)
        {
            byte[] temp = new byte[memArray.length + size];
            System.arraycopy(memArray, 0, temp, 0, memArray.length);
            memArray = temp;
        }
        record[0] = 1;
        System.arraycopy(record, 0, memArray, address, record.length);
        // System.out.println("Memory :" + new String(memArray));
        return address + record.length;
    }

    /**
     * Remove the record from the memory it will only turn the flag byte into
     * deactivated
     * 
     * @param address
     *            the first byte of the record in the address
     */
    public void delete(int address)
    {
        memArray[address] = 0;
    }

    /**
     * Checks to see if a particular address is active
     * 
     * @param address
     *            - address to be inspected
     * @return - if that slot is active or not
     */
    public boolean isActived(int address)
    {
        return memArray[address] != 0;
    }
}

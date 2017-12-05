/**
 * Handle class that holds an integer that represents the location of the record
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class Handle
{
    private int index;

    /**
     * Creates the Handle object
     * 
     * @param i
     *            - sets the index equal to this
     */
    public Handle(int i)
    {
        index = i;
    }

    /**
     * Getter method the index
     * 
     * @return - the index
     */
    protected int getIndex()
    {
        return index;
    }
}

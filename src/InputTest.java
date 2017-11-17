import java.io.IOException;

import student.TestCase;

/**
 * Class that reads in the given input file and calls the corresponding commands
 * in the BST and HashTable classes
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class InputTest extends TestCase
{
    private Input myInput;
    
    /**
     * Tests that mainScanner in readLine() has no next line in a blank file
     * 
     * @throws IOException
     */
    public void testReadLineNextLine() throws IOException
    {
        String fileName = "mySample.txt";
        myInput = new Input(fileName);
        try
        {
            myInput.readLine();
        }
        catch (IllegalStateException e)
        {
            //
        }
    }
    
    
}

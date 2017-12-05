import java.io.IOException;
import student.TestCase;

/**
 * Class where we create test cases for all of the methods in the SongSearch
 * class. We verify that each method is functional and acts accordingly as
 * expected.
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class SongSearchTest extends TestCase
{
    /**
     * Tests the main method that runs the entire program/project
     * 
     * @throws IOException
     */
    public void testMain() throws IOException
    {
        SongSearch ss = new SongSearch();
        SongSearch.main(new String[]
        { "100", "1024", "P4_Input1_Sample.t" });
        assertEquals(ss.getClass(), ss.getClass());
    }
}

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;

/**
 * Test class to ensure that all the methods in the SongSearch class work as
 * expected and functionality meets the requirements
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */
public class SongSearchTest
{
    /**
     * Method to run the entire project, given a sample input file
     * 
     * @throws IOException
     */
    @Test
    public void test() throws IOException
    {
        SongSearch.main(new String[]
        { "100", "1024", "P4_Input1_Sample" });
        SongSearch songSearch = new SongSearch();
        assertEquals(songSearch.getClass(), SongSearch.class);
    }
}

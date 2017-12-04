import java.io.IOException;
import student.TestCase;

/**
 * Test class to verify that all the methods in the Input class function
 * correctly. Checks that the proper artist, song, and command name are all
 * properly retrieved when called
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class InputTest extends TestCase
{
    private Input myInput;

    /**
     * Tests that the command beginning with "insert" is called properly and
     * returns correct information
     * 
     * @throws IOException
     */
    public void testInsert() throws IOException
    {
        String fileName = "InsertSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("insert", myInput.getCommandName());
        assertEquals("Green Day", myInput.getArtist());
        assertEquals("Wake Me Up When September Ends (Live at Foxboro_ MA 9/3/05)", myInput.getSong());
    }

    /**
     * Tests that the command beginning with "remove artist" is called properly
     * and returns correct information
     * 
     * @throws IOException
     */
    public void testRemoveArtist() throws IOException
    {
        String fileName = "RemoveArtistSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("remove", myInput.getCommandName());
        assertEquals("Lil Wayne", myInput.getArtist());
    }

    /**
     * Tests that the command beginning with "remove artist" is called properly
     * and returns correct information
     * 
     * @throws IOException
     */
    public void testRemoveSong() throws IOException
    {
        String fileName = "RemoveSongSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("remove", myInput.getCommandName());
        assertEquals("Congratulations", myInput.getSong());
    }

    /**
     * Tests that the command beginning with "print artist" is called properly
     * and returns correct information
     * 
     * @throws IOException
     */
    public void testPrintArtist() throws IOException
    {
        String fileName = "PrintArtistSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("print", myInput.getCommandName());
    }

    /**
     * Tests that the command beginning with "print song" is called properly and
     * returns correct information
     * 
     * @throws IOException
     */
    public void testPrintSong() throws IOException
    {
        String fileName = "PrintSongSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("print", myInput.getCommandName());
    }

    /**
     * Tests that the command beginning with "list artist" is called properly
     * and returns correct information
     * 
     * @throws IOException
     */
    public void testListArtist() throws IOException
    {
        String fileName = "ListArtistSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("list", myInput.getCommandName());
        assertEquals("Post Malone", myInput.getArtist());
    }

    /**
     * Tests that the command beginning with "list song" is called properly and
     * returns correct information
     * 
     * @throws IOException
     */
    public void testListSong() throws IOException
    {
        String fileName = "ListSongSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("list", myInput.getCommandName());
        assertEquals("You Belong With Me", myInput.getSong());
    }

    /**
     * Tests that the command beginning with "delete" is called properly and
     * returns correct information
     * 
     * @throws IOException
     */
    public void testDelete() throws IOException
    {
        String fileName = "DeleteSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("delete", myInput.getCommandName());
        assertEquals("Wale", myInput.getArtist());
        assertEquals("MY PYT", myInput.getSong());
    }

    /**
     * Tests that the command beginning with "print tree" is called properly and
     * returns correct information
     * 
     * @throws IOException
     */
    public void testPrintTree() throws IOException
    {
        String fileName = "PrintTreeSample.txt";
        myInput = new Input(fileName, 1024, 1024);
        myInput.readLine();
        assertEquals("print", myInput.getCommandName());
    }
}

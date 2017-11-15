import java.io.IOException;

/**
 * Class that runs the entire project by reading in an input file
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class SongSearch
{
    /**
     * Main method that runs the entire program
     * 
     * @param args
     *            - inputFile argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        // String fileName = "sampleInput.txt";
        // Input programRunner = new Input(fileName);

        @SuppressWarnings("unused")
        Input programRunner = new Input(args[0]);
    }
}

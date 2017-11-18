import java.io.IOException;

/**
 * Class that runs the entire project by reading in an input file
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class SongSearch
{
    public static int hashSize;
    public static int blockSize;
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
        // Usage java SongSearch {initial-hash-size} {block-size} {command-file}
        hashSize = Integer.valueOf(args[0]);
        blockSize = Integer.valueOf(args[1]);
        @SuppressWarnings("unused")        
        Input programRunner = new Input(args[2]);
    }
}

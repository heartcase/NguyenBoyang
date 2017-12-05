import java.io.IOException;

/**
 * Class that runs the entire project by reading in an input file
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class SongSearch
{
    private static int hashSize;
    private static int blockSize;
    /**
     * Main method that runs the entire program
     * 
     * @param args
     *            - inputFile argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {  
        hashSize = Integer.valueOf(args[0]);
        blockSize = Integer.valueOf(args[1]);
        String fileName = args[2];
        Input programRunner = new Input(fileName, blockSize, hashSize);
        programRunner.readLine();

    }
}

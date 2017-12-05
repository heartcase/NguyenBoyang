import java.io.IOException;

/**
 * SongSearch class to run the entire project
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
        int hashSize = Integer.valueOf(args[0]);
        int blockSize = Integer.valueOf(args[1]);
        Memory memory = new Memory(blockSize);
        HashTable songHashTable = new HashTable(hashSize);
        HashTable artistHashTable = new HashTable(hashSize);
        BST<KVPair<Integer, Integer>> songBST = new BST<>();
        BST<KVPair<Integer, Integer>> artistBST = new BST<>();
        String fileName = args[2];
        Input programRunner = new Input(memory, artistBST, songBST,
                artistHashTable, songHashTable);
        programRunner.readLine(fileName);
    }
}

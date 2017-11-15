import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that reads in the given input file and calls the corresponding commands
 * in the BST and HashTable classes
 * 
 * @author Nguyen Ha (nguyen) and Boyang Li (beyongl)
 * @version 11.15.2017
 */

public class Input
{
    private BufferedReader bufferedReader;
    private Scanner scanner;

    private BST artistBST;
    private BST songBST;
    private HashTable artistHashTable;
    private HashTable songHashTable;

    /**
     * Constructor of the class that reads the files and chooses what commands
     * to execute accordingly
     * 
     * @param fileName
     *            - name of the file to be read
     * @throws IOException
     */
    public Input(String fileName) throws IOException
    {
        try
        {
            bufferedReader = new BufferedReader(
                    new FileReader(new File(fileName)));
        }
        catch (IOException e)
        {
            throw new IOException("No input file can be found");
        }
        scanner = new Scanner(fileName);
        artistBST = new BST();
        songBST = new BST();
        artistHashTable = new HashTable();
        songHashTable = new HashTable();
        
        this.readLine();
    }

    /**
     * Reads each line of the input file and calls the corresponding command
     * method, such as input, remove, delete, print, list, etc.
     */
    public void readLine() throws IOException
    {
        while (scanner.hasNextLine())
        {
            // Reads a line of text
            String currentLine = bufferedReader.readLine();

            // In the case that the line has nothing to read
            if (currentLine == null)
            {
                break;
            }

            // Removes leading and trailing white spaces and stores the string
            // called commandName
            String commandName = currentLine.trim();
            //String[] lineInformation = firstWord.split("\\s+");
            //String commandName = lineInformation[0];

            // Different cases for the possible commands
            if (commandName.equals("insert"))
            {
                // Read length of the string until <SEP> and store that as artist
                // Everything after that would be stored as the song name
            }
            else if (commandName.equals("remove"))
            {

            }
            else if (commandName.equals("print"))
            {

            }
            else if (commandName.equals("list"))
            {

            }
            else if (commandName.equals("delete"))
            {

            }
        }
        scanner.close();
    }
}

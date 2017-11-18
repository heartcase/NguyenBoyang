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
    private String artistInformation;
    private String songInformation;
    private String commandName;

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
            currentLine = currentLine.trim();

            // Removes any white spaces in the middle, guaranteeing first
            // element is commandName
            String[] lineInformation = currentLine.split("\\s+");
            commandName = lineInformation[0];

            // Different cases for the possible commands...
            if (commandName.equals("insert"))
            {
                String[] splitString = currentLine.split("insert|<SEP>");

                artistInformation = splitString[1];
                artistInformation = artistInformation.trim();
                songInformation = splitString[2];
                songInformation = songInformation.trim();

                // Do something with the artist/song variable above
            }
            else if (commandName.equals("remove"))
            {
                String[] splitString = currentLine.split("remove|artist|song");

                if (lineInformation[1].equals("artist"))
                {
                    artistInformation = splitString[2].trim();
                    // Do something with artistInformation variable above
                }
                else if (lineInformation[1].equals("song"))
                {
                    songInformation = splitString[2].trim();
                    // Do something with songInformation variable above
                }
            }
            else if (commandName.equals("print"))
            {
                if (lineInformation[1].equals("artist"))
                {
                    // Do something
                }
                else if (lineInformation[1].equals("song"))
                {
                    // Do something
                }
                else if (lineInformation[1].equals("tree"))
                {
                    // Do something
                }
            }
            else if (commandName.equals("list"))
            {
                String[] splitString = currentLine.split("list|artist|song");

                if (lineInformation[1].equals("artist"))
                {
                    artistInformation = splitString[2].trim();
                    // Do something with artistInformation variable above
                }
                else if (lineInformation[1].equals("song"))
                {
                    songInformation = splitString[2].trim();
                    // Do something with songInformation variable above
                }
            }
            else if (commandName.equals("delete"))
            {
                String[] splitString = currentLine.split("delete|<SEP>");

                artistInformation = splitString[1];
                artistInformation = artistInformation.trim();
                songInformation = splitString[2];
                songInformation = songInformation.trim();

                // Do something with the artist/song variable above
            }
        }
        scanner.close();
    }

    /**
     * Returns the artist information
     * 
     * @return - artist information
     */
    public String getArtist()
    {
        return artistInformation;
    }

    /**
     * Returns the song information
     * 
     * @return - song information
     */
    public String getSong()
    {
        return songInformation;
    }

    /**
     * Returns the command name called
     * 
     * @return - the command name
     */
    public String getCommandName()
    {
        return commandName;
    }
}
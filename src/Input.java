import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.sun.corba.se.spi.orbutil.fsm.State;

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

    private BST<Handle> artistBST;
    private BST<Handle> songBST;
    private HashTable artistHashTable;
    private HashTable songHashTable;
    private String artistInformation;
    private String songInformation;
    private String commandName;
    private Memory memory;
    private int memoryAddress;

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
        artistInformation = "";
        songInformation = "";
        scanner = new Scanner(fileName);
        artistBST = new BST();
        songBST = new BST();
        artistHashTable = new HashTable();
        songHashTable = new HashTable();
        memory = new Memory(SongSearch.blockSize);
        memoryAddress = 0;
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
                insert();
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
    // string -> int
    // songHashTable.getHandle(songHashTable.search(k, memory)).getKey();
    private void rangeSearch(int k, BST<Handle> bst, List<Handle> list) {  
        Iterator<Handle> iterator = bst.iterator();
        int stage = 0;
        while (iterator.hasNext()) {
            Handle handle = iterator.next();
            if(handle.getKey() == k) {
                list.add(handle);
                if(stage == 0) {
                    stage++;
                }
            }else {
                if(stage == 1) {
                    return;
                }
            }
        }
    }
    
    private void removeArtist()
    {
        // TODO Unfinished
        Iterator<Handle> iterator = artistBST.iterator();

        while (iterator.hasNext())
        {
            Handle targetHandle = iterator.next();
            int key = targetHandle.getKey();
            int value = targetHandle.getValue();
            if (memory.read(key).equals(artistInformation))
            {
                Handle temp = songBST.find(new Handle(value, key));
                songBST.remove(temp);
                artistBST.remove(targetHandle);
                artistHashTable.remove(key, value, memory);
                songHashTable.remove(value, key, memory);
            }
        }
    }
    private void insert() {
        int artistAddress;
        int songAddress;
        int artistSongHashIndex;  
        // Do something with the artist/song variable above
        artistAddress = artistHashTable.search(artistInformation, memory);
        if(artistAddress == -1) {
            artistAddress = memoryAddress;
            memoryAddress = memory.add(memoryAddress, artistInformation);
            /** TODO  print*/
        }else {
            /** TODO  print*/
        }        
        songAddress = songHashTable.search(songInformation, memory);
        if(songAddress == -1) {
            songAddress = memoryAddress;
            memoryAddress = memory.add(memoryAddress, songInformation);
            /** TODO  print*/
        }else {
            /** TODO  print*/
        }       
        artistSongHashIndex = artistHashTable.search(artistAddress, songAddress, memory);
        if(artistSongHashIndex == -1) {
            Handle h1 = new Handle(artistAddress, songAddress);
            Handle h2 = new Handle(songAddress, artistAddress);
            artistHashTable.insert(h1, memory);
            songHashTable.insert(h2, memory);
            artistBST.insert(h1);
            songBST.insert(h2);
            /** TODO  print*/
        }else {
            /** TODO  print*/
        }
    }
    
    public String getArtist()
    {
        return artistInformation;
    }
    
    public String getSong()
    {
        return songInformation;
    }
    
    public String getCommandName()
    {
        return commandName;
    }
}

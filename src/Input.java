    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Scanner;
    
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
    
    
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
            artistBST = new BST<>();
            songBST = new BST<>();
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
                // System.out.println(currentLine);
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
                    String pattern = "insert\\s+(.+)<SEP>(.+)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(currentLine);
                    m.find();
                    artistInformation = m.group(1).trim();
                    songInformation = m.group(2).trim();
                    insert();
                }
                else if (commandName.equals("remove"))
                { 
                    String[] splitString = currentLine.split("remove|artist|song");
    
                    if (lineInformation[1].equals("artist"))
                    {
                        String pattern = "remove\\s+artist(.+)";
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(currentLine);
                        m.find();
                        artistInformation = m.group(1).trim();
                        removeArtist();
                    }
                    else if (lineInformation[1].equals("song"))
                    {
                        String pattern = "remove\\s+song(.+)";
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(currentLine);
                        m.find();
                        songInformation = m.group(1).trim();
                        removeSong();
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
                        String pattern = "list\\s+artist(.+)";
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(currentLine);
                        m.find();
                        artistInformation = m.group(1).trim();
                        listArtist();
                    }
                    else if (lineInformation[1].equals("song"))
                    {
                        String pattern = "list\\s+song(.+)";
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(currentLine);
                        m.find();
                        songInformation = m.group(1).trim();
                        listSong();
                    }
                }
                else if (commandName.equals("delete"))
                {
                    String pattern = "delete\\s+(.+)<SEP>(.+)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(currentLine);
                    m.find();
                    artistInformation = m.group(1).trim();
                    songInformation = m.group(2).trim();
                    delete();
                }
            }
            scanner.close();
        }
    
        // string -> int
        // songHashTable.getHandle(songHashTable.search(k, memory)).getKey();
        private void rangeSearch(int k, BST<Handle> bst, List<Handle> list)
        {
            Iterator<Handle> iterator = bst.iterator();
            int stage = 0;
            while (iterator.hasNext())
            {
                Handle handle = iterator.next();
/*                if(k == 200) {
                    System.out.println(handle.getKey() + ", " + handle.getValue());
                }*/
                if (handle.getKey() == k)
                {
                    list.add(handle);
                    if (stage == 0)
                    {
                        stage++;
                    }
                }
                else
                {
                }
            }
        }
    
        private boolean checkLastItem(int k, BST<Handle> bst)
        {
            List<Handle> list = new ArrayList<>();
            rangeSearch(k, bst, list);
            if (list.size() <= 1)
            {
                return true;
            }
            return false;
        }
    
        private void delete()
        {
            int k = artistHashTable.getHandle(artistHashTable.search(artistInformation, memory)).getKey();
            int v = songHashTable.getHandle(songHashTable.search(songInformation, memory)).getKey();
            if(k == -1) {
               printNotExists(artistInformation, 0);
               return;
            }
            if(v == -1) {
               printNotExists(songInformation, 1);
               return;
             }
            k = artistHashTable.getHandle(artistHashTable
                    .search(artistInformation, songInformation, memory)).getKey();
            v = songHashTable.getHandle(songHashTable.search(songInformation,
                    artistInformation, memory)).getKey();
            if (k == -1 || v == -1)
            {
                String string = String.format(
                        "The KVPair (|%s|,|%s|) was not found in the database.",
                        artistInformation, songInformation);
                System.out.println(string);
                string = String.format(
                        "The KVPair (|%s|,|%s|) was not found in the database.",
                        songInformation, artistInformation);
                System.out.println(string);
                return;
            }
            Handle temp1 = new Handle(k, v);
            Handle temp2 = new Handle(v, k);
            temp1 = artistBST.find(temp1);
            temp2 = songBST.find(temp2);
            printDeletePair(artistInformation, songInformation);
            if (checkLastItem(k, artistBST))
            {
                memory.delete(k);
                printRemoveFromMemory(artistInformation, 0);
            }
            if (checkLastItem(v, songBST))
            {
                memory.delete(v);
                printRemoveFromMemory(songInformation, 1);
            }
            artistBST.remove(temp1);
            songBST.remove(temp2);
            artistHashTable.remove(k, v, memory);
            songHashTable.remove(v, k, memory);
        }
    
        private void removeArtist()
        {
            List<Handle> list = new ArrayList<>();
            int k = artistHashTable.getHandle(artistHashTable.search(artistInformation, memory)).getKey();
            if (k == -1)
            {
                printNotExists(artistInformation, 0);
                return;
            }
            rangeSearch(k, artistBST, list);
            for (Handle handle : list)
            {
                int v = handle.getValue();
                printDeletePair(artistInformation, memory.read(v));
            }
            memory.delete(k);
            printRemoveFromMemory(artistInformation, 0);
            for (Handle handle : list) {
                int v = handle.getValue();
                if (checkLastItem(v, songBST))
                {
                    String name = memory.read(v);
                    memory.delete(v);
                    printRemoveFromMemory(name, 1);
                }
                artistBST.remove(handle);
                Handle temp = new Handle(v, k);
                temp = songBST.find(temp);
                songBST.remove(temp);
                artistHashTable.remove(k, v, memory);
                songHashTable.remove(v, k, memory);
            }
        }
    
        private void removeSong()
        {
            List<Handle> list = new ArrayList<>();
            int k = songHashTable.getHandle(songHashTable.search(songInformation, memory)).getKey();
            if (k == -1)
            {
                printNotExists(songInformation, 1);
                return;
            }
            rangeSearch(k, songBST, list);
            for (Handle handle : list)
            {
                int v = handle.getValue();
                printDeletePair(songInformation, memory.read(v));
            }
            memory.delete(k);
            printRemoveFromMemory(songInformation, 1);
            for (Handle handle : list) {
                int v = handle.getValue();
                if (checkLastItem(v, artistBST))
                {
                    String name = memory.read(v);
                    memory.delete(v);
                    printRemoveFromMemory(name, 0);
                }
                songBST.remove(handle);
                Handle temp = new Handle(v, k);
                temp = artistBST.find(temp);
                artistBST.remove(temp);
                songHashTable.remove(k, v, memory);
                artistHashTable.remove(v, k, memory);
            }
        }
    
        private void printNotExists(String name, int index)
        {
            String database = new String[]
            { "artist", "song" }[index];
            String string = String.format("|%s| does not exist in the %s database.",
                    name, database);
            System.out.println(string);
        }
    
        private void printDeletePair(String key, String value)
        {
            String string = String.format(
                    "The KVPair (|%s|,|%s|) is deleted from the tree.", key, value);
            System.out.println(string);
            string = String.format(
                    "The KVPair (|%s|,|%s|) is deleted from the tree.", value, key);
            System.out.println(string);
        }
    
        private void printRemoveFromMemory(String name, int index)
        {
            String database = new String[]
            { "artist", "song" }[index];
            String string = String.format("|%s| is deleted from the %s database.",
                    name, database);
            System.out.println(string);
        }
    
        private void listArtist()
        {
            int k = artistHashTable.getHandle(artistHashTable.search(artistInformation, memory)).getKey();
            List<Handle> list = new ArrayList<>();
            rangeSearch(k, artistBST, list);
            if (list.size() == 0)
            {
                printNotExists(artistInformation, 0);
            }
            for (Handle h : list)
            {
                String string = String.format("|%s|", memory.read(h.getValue()));
                System.out.println(string);
            }
        }
    
        private void listSong()
        {
            int k = songHashTable.getHandle(songHashTable.search(songInformation, memory)).getKey();
            List<Handle> list = new ArrayList<>();
            rangeSearch(k, songBST, list);
            if (list.size() == 0)
            {
                printNotExists(songInformation, 1);
            }
            for (Handle h : list)
            {
                String string = String.format("|%s|", memory.read(h.getValue()));
                System.out.println(string);
            }
        }
    
        private void insert()
        {
            int artistAddress;
            int songAddress;
            int artistSongHashIndex;
            // Do something with the artist/song variable above
            
            artistAddress = artistHashTable.getHandle(artistHashTable.search(artistInformation, memory)).getKey();
            if (artistAddress == -1)
            {
                artistAddress = memoryAddress;
                memoryAddress = memory.add(memoryAddress, artistInformation);
                String string = String.format(
                        "|%s| is added to the Artist database.", artistInformation);
                System.out.println(string);
            }
            else
            {
                String string = String.format(
                        "|%s| duplicates a record already in the Artist database.",
                        artistInformation);
                System.out.println(string);
            }
            songAddress = songHashTable.getHandle(songHashTable.search(songInformation, memory)).getKey();
            if (songAddress == -1)
            {
                songAddress = memoryAddress;
                memoryAddress = memory.add(memoryAddress, songInformation);
                String string = String.format("|%s| is added to the Song database.",
                        songInformation);
                System.out.println(string);
            }
            else
            {
                String string = String.format(
                        "|%s| duplicates a record already in the Song database.",
                        songInformation);
                System.out.println(string);
            }
            artistSongHashIndex = artistHashTable.search(artistAddress, songAddress,
                    memory);
            if (artistSongHashIndex == -1)
            {
                Handle h1 = new Handle(artistAddress, songAddress);
                Handle h2 = new Handle(songAddress, artistAddress);
                artistHashTable.insert(h1, memory);
                songHashTable.insert(h2, memory);
                artistBST.insert(h1);
                songBST.insert(h2);
    
                String string = String.format(
                        "The KVPair (|%s|,|%s|),(%d,%d) is added to the tree.",
                        artistInformation, songInformation, artistAddress,
                        songAddress);
                System.out.println(string);
                string = String.format(
                        "The KVPair (|%s|,|%s|),(%d,%d) is added to the tree.",
                        songInformation, artistInformation, songAddress,
                        artistAddress);
                System.out.println(string);
            }
            else
            {
                String string = String.format(
                        "The KVPair (|%s|,|%s|),(%d,%d) duplicates a record already in the tree.",
                        artistInformation, songInformation, artistAddress,
                        songAddress);
                System.out.println(string);
                string = String.format(
                        "The KVPair (|%s|,|%s|),(%d,%d) duplicates a record already in the tree.",
                        songInformation, artistInformation, songAddress,
                        artistAddress);
                System.out.println(string);
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

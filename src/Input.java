import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private Memory memory;
    private int memoryAddress;
    private BST<KVPair<Integer, Integer>> artistBST;
    private BST<KVPair<Integer, Integer>> songBST;
    private HashTable artistHashTable;
    private HashTable songHashTable;

    public Input(Memory m, BST<KVPair<Integer, Integer>> b1,
            BST<KVPair<Integer, Integer>> b2, HashTable h1, HashTable h2)
    {
        memory = m;
        memoryAddress = 0;
        artistBST = b1;
        songBST = b2;
        artistHashTable = h1;
        songHashTable = h2;
    }

    public void readLine(String filename) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File(filename)));
        String currentLine;
        String commandName;
        String artist;
        String song;
        while ((currentLine = bufferedReader.readLine()) != null)
        {
            currentLine = currentLine.trim();
            String[] lineInformation = currentLine.split("\\s+");
            commandName = lineInformation[0];
            if (commandName.equals("insert"))
            {
                String pattern = "insert\\s+(.+)<SEP>(.+)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(currentLine);
                m.find();
                artist = m.group(1).trim();
                song = m.group(2).trim();
                insert(artist, song);
            }
            else if (commandName.equals("remove"))
            {
                if (lineInformation[1].equals("artist"))
                {
                    String pattern = "remove\\s+artist(.+)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(currentLine);
                    m.find();
                    artist = m.group(1).trim();
                    removeArtist(artist);
                }
                else if (lineInformation[1].equals("song"))
                {
                    String pattern = "remove\\s+song(.+)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(currentLine);
                    m.find();
                    song = m.group(1).trim();
                    removeSong(song);
                }
            }
            else if (commandName.equals("print"))
            {
                if (lineInformation[1].equals("artist"))
                {
                    printArtist();
                }
                else if (lineInformation[1].equals("song"))
                {
                    printSong();
                }
                else if (lineInformation[1].equals("tree"))
                {
                    printTree();
                }
            }
            else if (commandName.equals("list"))
            {
                if (lineInformation[1].equals("artist"))
                {
                    String pattern = "list\\s+artist(.+)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(currentLine);
                    m.find();
                    artist = m.group(1).trim();
                    listArtist(artist);
                }
                else if (lineInformation[1].equals("song"))
                {
                    String pattern = "list\\s+song(.+)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(currentLine);
                    m.find();
                    song = m.group(1).trim();
                    listSong(song);
                }
            }
            else if (commandName.equals("delete"))
            {
                String pattern = "delete\\s+(.+)<SEP>(.+)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(currentLine);
                m.find();
                artist = m.group(1).trim();
                song = m.group(2).trim();
                delete(artist, song);
            }
        }
        bufferedReader.close();
    }

    protected void removeArtist(String artist)
    {
        List<KVPair<Integer, Integer>> list = new ArrayList<>();
        int k = artistHashTable
                .getHandle(artistHashTable.search(artist, memory)).getIndex();
        if (k == -1)
        {
            printNotExists(artist, 0);
            return;
        }
        rangeSearch(k, artistBST, list);
        for (KVPair<Integer, Integer> pair : list)
        {
            int v = pair.getValue();
            artistBST.remove(pair);
            KVPair<Integer, Integer> temp = new KVPair<>(v, k);
            temp = songBST.find(temp);
            songBST.remove(temp);
            printDeletePair(artist, memory.read(v));
            if (checkLastItem(k, artistBST) && memory.isActived(k))
            {
                String name = memory.read(k);
                memory.delete(k);
                artistHashTable.remove(name, memory);
                printRemoveFromMemory(name, 0);
            }
            if (checkLastItem(v, songBST))
            {
                String name = memory.read(v);
                memory.delete(v);
                songHashTable.remove(name, memory);
                printRemoveFromMemory(name, 1);
            }
        }
    }

    protected void removeSong(String song)
    {
        List<KVPair<Integer, Integer>> list = new ArrayList<>();
        int k = songHashTable.getHandle(songHashTable.search(song, memory))
                .getIndex();
        if (k == -1)
        {
            printNotExists(song, 1);
            return;
        }
        rangeSearch(k, songBST, list);
        for (KVPair<Integer, Integer> pair : list)
        {
            int v = pair.getValue();
            songBST.remove(pair);
            KVPair<Integer, Integer> temp = new KVPair<>(v, k);
            temp = artistBST.find(temp);
            artistBST.remove(temp);
            printDeletePair(song, memory.read(v));
            if (checkLastItem(k, songBST) && memory.isActived(k))
            {
                String name = memory.read(k);
                memory.delete(k);
                songHashTable.remove(name, memory);
                printRemoveFromMemory(name, 1);
            }
            if (checkLastItem(v, artistBST))
            {
                String name = memory.read(v);
                memory.delete(v);
                artistHashTable.remove(name, memory);
                printRemoveFromMemory(name, 0);
            }
        }

    }

    protected void printArtist()
    {
        List<String> list = new ArrayList<>();
        Handle[] array = artistHashTable.getHashArray();
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == null)
            {
                continue;
            }
            int k = array[i].getIndex();
            String key = memory.read(k);
            String string = String.format("|%s| %d", key, i);
            System.out.println(string);
            list.add(key);
        }
        String string = String.format("total artists: %d", list.size());
        System.out.println(string);

    }

    protected void printSong()
    {
        List<String> list = new ArrayList<>();
        Handle[] array = songHashTable.getHashArray();
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == null)
            {
                continue;
            }
            int k = array[i].getIndex();
            String key = memory.read(k);
            String string = String.format("|%s| %d", key, i);
            System.out.println(string);
            list.add(key);
        }
        String string = String.format("total songs: %d", list.size());
        System.out.println(string);
    }

    protected void printTree()
    {
        System.out.println("Printing artist tree:");
        Iterator<KVPair<Integer, Integer>> iterator = artistBST.iterator();
        while (iterator.hasNext())
        {
            KVPair<Integer, Integer> handle = iterator.next();
            int indentation = artistBST.getDepthFromHandle(handle) * 2;
            if (indentation != 0)
            {
                String string = String.format("%" + indentation + "s(%d,%d)",
                        " ", handle.getKey(), handle.getValue());
                System.out.println(string);
            }
            else
            {
                String string = String.format("(%d,%d)", handle.getKey(),
                        handle.getValue());
                System.out.println(string);
            }

        }
        System.out.println("Printing song tree:");
        iterator = songBST.iterator();
        while (iterator.hasNext())
        {
            KVPair<Integer, Integer> handle = iterator.next();
            int indentation = songBST.getDepthFromHandle(handle) * 2;
            if (indentation != 0)
            {
                String string = String.format("%" + indentation + "s(%d,%d)",
                        " ", handle.getKey(), handle.getValue());
                System.out.println(string);
            }
            else
            {
                String string = String.format("(%d,%d)", handle.getKey(),
                        handle.getValue());
                System.out.println(string);
            }
        }

    }

    protected void listArtist(String artist)
    {
        int k = artistHashTable
                .getHandle(artistHashTable.search(artist, memory))
                .getIndex();
        List<KVPair<Integer, Integer>> list = new ArrayList<>();
        rangeSearch(k, artistBST, list);
        if (list.size() == 0)
        {
            printNotExists(artist, 0);
        }
        for (KVPair<Integer, Integer> h : list)
        {
            String string = String.format("|%s|", memory.read(h.getValue()));
            System.out.println(string);
        }

    }

    protected void listSong(String song)
    {
        int k = songHashTable
                .getHandle(songHashTable.search(song, memory))
                .getIndex();
        List<KVPair<Integer, Integer>> list = new ArrayList<>();
        rangeSearch(k, songBST, list);
        if (list.size() == 0)
        {
            printNotExists(song, 1);
        }
        for (KVPair<Integer, Integer> h : list)
        {
            String string = String.format("|%s|", memory.read(h.getValue()));
            System.out.println(string);
        }

    }

    protected void delete(String artist, String song)
    {
        int k = artistHashTable
                .getHandle(artistHashTable.search(artist, memory)).getIndex();
        int v = songHashTable.getHandle(songHashTable.search(song, memory))
                .getIndex();
        if (k == -1)
        {
            printNotExists(artist, 0);
            return;
        }
        if (v == -1)
        {
            printNotExists(song, 1);
            return;
        }

        KVPair<Integer, Integer> pair = artistBST
                .find(new KVPair<Integer, Integer>(k, v));
        if (pair == null)
        {
            String string = String.format(
                    "The KVPair (|%s|,|%s|) was not found in the database.",
                    artist, song);
            System.out.println(string);
            string = String.format(
                    "The KVPair (|%s|,|%s|) was not found in the database.",
                    song, artist);
            System.out.println(string);
            return;
        }
        KVPair<Integer, Integer> temp1 = new KVPair<Integer, Integer>(k, v);
        KVPair<Integer, Integer> temp2 = new KVPair<Integer, Integer>(v, k);
        temp1 = artistBST.find(temp1);
        temp2 = songBST.find(temp2);
        printDeletePair(artist, song);
        artistBST.remove(temp1);
        songBST.remove(temp2);
        if (checkLastItem(k, artistBST))
        {
            String name = memory.read(k);
            memory.delete(k);
            artistHashTable.remove(name, memory);
            printRemoveFromMemory(name, 0);
        }
        if (checkLastItem(v, songBST))
        {
            String name = memory.read(v);
            memory.delete(v);
            songHashTable.remove(name, memory);
            printRemoveFromMemory(name, 1);
        }

    }

    protected void insert(String artist, String song)
    {
        int artistAddress;
        int songAddress;
        KVPair<Integer, Integer> pair;
        // look up in artist database
        artistAddress = artistHashTable
                .getHandle(artistHashTable.search(artist, memory)).getIndex();
        if (artistAddress == -1)
        {
            artistAddress = memoryAddress;
            memoryAddress = memory.add(memoryAddress, artist);
            artistHashTable.insert(artistAddress, memory);
            String string = String
                    .format("|%s| is added to the Artist database.", artist);
            System.out.println(string);
        }
        else
        {
            String string = String.format(
                    "|%s| duplicates a record already in the Artist database.",
                    artist);
            System.out.println(string);
        }
        // look up in song database
        songAddress = songHashTable
                .getHandle(songHashTable.search(song, memory)).getIndex();
        if (songAddress == -1)
        {
            songAddress = memoryAddress;
            memoryAddress = memory.add(memoryAddress, song);
            songHashTable.insert(songAddress, memory);
            String string = String.format("|%s| is added to the Song database.",
                    song);
            System.out.println(string);
        }
        else
        {
            String string = String.format(
                    "|%s| duplicates a record already in the Song database.",
                    song);
            System.out.println(string);
        }
        // look up in pairs database
        pair = artistBST
                .find(new KVPair<Integer, Integer>(artistAddress, songAddress));
        if (pair == null)
        {
            KVPair<Integer, Integer> pair1 = new KVPair<>(artistAddress,
                    songAddress);
            KVPair<Integer, Integer> pair2 = new KVPair<>(songAddress,
                    artistAddress);
            artistBST.insert(pair1);
            songBST.insert(pair2);
            String string = String.format(
                    "The KVPair (|%s|,|%s|),(%d,%d) is added to the tree.",
                    artist, song, artistAddress, songAddress);
            System.out.println(string);
            string = String.format(
                    "The KVPair (|%s|,|%s|),(%d,%d) is added to the tree.",
                    song, artist, songAddress, artistAddress);
            System.out.println(string);
        }
        else
        {
            String string = String.format(
                    "The KVPair (|%s|,|%s|),(%d,%d) duplicates "
                            + "a record already in the tree.",
                    artist, song, artistAddress, songAddress);
            System.out.println(string);
            string = String.format(
                    "The KVPair (|%s|,|%s|),(%d,%d) duplicates "
                            + "a record already in the tree.",
                    song, artist, songAddress, artistAddress);
            System.out.println(string);
        }
    }

    private void rangeSearch(int k, BST<KVPair<Integer, Integer>> bst,
            List<KVPair<Integer, Integer>> list)
    {
        Iterator<KVPair<Integer, Integer>> iterator = bst.iterator();
        int stage = 0;
        while (iterator.hasNext())
        {
            KVPair<Integer, Integer> pair = iterator.next();
            if (pair.getKey() == k)
            {
                list.add(pair);
                if (stage == 0)
                {
                    stage++;
                }
            }
            else
            {
                if (stage == 1)
                {
                    return;
                }
            }
        }
    }

    /**
     * Checks to see if it is the last item in the BST. If it is the last
     * instance of artist/song, then it will be removed from the hashTable and
     * memory pool accordingly
     * 
     * @param k
     *            - starting position
     * @param bst
     *            - binary search tree to be inspected
     * @return
     */
    private boolean checkLastItem(int k, BST<KVPair<Integer, Integer>> bst)
    {
        List<KVPair<Integer, Integer>> list = new ArrayList<>();
        rangeSearch(k, bst, list);
        return list.size() <= 0;
    }

    /**
     * System-out function that reports that a pair has been deleted from the
     * tree
     * 
     * @param key
     *            - key
     * @param value
     *            - value
     */
    private void printDeletePair(String key, String value)
    {
        String string = String.format(
                "The KVPair (|%s|,|%s|) is deleted from the tree.", key, value);
        System.out.println(string);
        string = String.format(
                "The KVPair (|%s|,|%s|) is deleted from the tree.", value, key);
        System.out.println(string);
    }

    /**
     * System-out function that reports a pair has been removed from the memory
     * 
     * @param name
     *            - name of artist/song
     * @param index
     *            - index to be removed from
     */
    private void printRemoveFromMemory(String name, int index)
    {
        String database = new String[]
        { "Artist", "Song", "artist", "song" }[index];
        String string = String.format("|%s| is deleted from the %s database.",
                name, database);
        System.out.println(string);
    }

    /**
     * System-out function where a artist/song does not exist in the database
     * 
     * @param name
     *            - name of artist/song
     * @param index
     *            - index to be checked
     */
    private void printNotExists(String name, int index)
    {
        String database = new String[]
        { "artist", "song" }[index];
        String string = String.format("|%s| does not exist in the %s database.",
                name, database);
        System.out.println(string);
    }
}

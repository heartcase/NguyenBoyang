import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class SongSearchTest
{

    @Test
    public void test() throws IOException
    {
        SongSearch.main(new String[]
        { "100", "1024", "P4_Input1_Sample" });
        SongSearch songSearch = new SongSearch();
        assertEquals(songSearch.getClass(), SongSearch.class);
    }

}

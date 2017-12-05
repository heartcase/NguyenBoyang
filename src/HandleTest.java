import static org.junit.Assert.*;

import org.junit.Test;

public class HandleTest
{

    @Test
    public void testHandle()
    {
        Handle handle = new Handle(0);
        assertEquals(handle.getClass(), Handle.class);
    }

    @Test
    public void testGetIndex()
    {
        for(int i = 0; i < 10000; i++) {
            Handle handle = new Handle(i);
            assertEquals(handle.getIndex(), i);
        }
    }

}

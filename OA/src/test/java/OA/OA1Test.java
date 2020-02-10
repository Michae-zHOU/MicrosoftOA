package OA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class OA1Test 
{
    OA1 oa = new OA1();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testRemoveSmallestCharacter() 
    {
        assertEquals(oa.removeSmallestCharacter("abc"), "ab");
    }

    @Test
    public void testGetLongestNonRepeatSubString() 
    {
        assertEquals(oa.getLongestNonRepeatSubString("baaabbabbb"), 7);
    }
    
    @Test
    public void testGetLargestAlphabet() 
    {
        assertEquals(oa.getLargestAlphabet("aAbBzZ"), 'Z');
        assertEquals(oa.getLargestAlphabet("aAbBZz"), 'Z');
        assertEquals(oa.getLargestAlphabet("aAbBb"), 'B');
    }
}

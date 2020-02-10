package OA;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class OA2Test 
{
    OA2 oa = new OA2();

    @Test
    public void testQuestionMarkConverter() 
    { 
        boolean isContiguousCharacterDifferent = true;
        String result = oa.questionMarkConverter("??????????");
        
        for(int i=1; i<result.length(); ++i) {
            if(result.charAt(i) == result.charAt(i-1)) {
                isContiguousCharacterDifferent = false;
                break;
            }
        }

        assertTrue(isContiguousCharacterDifferent);
    }
}

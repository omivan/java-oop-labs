import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @Before
    public void init(){
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testAddEmptyString() throws Exception {
        int actualSum = stringCalculator.add("");
        int expectedSum = 0;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddOneNumber() throws Exception {
        int actualSum = stringCalculator.add("4");
        int expectedSum = 4;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddMultipleNumbersComma() throws Exception {
        int actualSum = stringCalculator.add("1,2,3,4");
        int expectedSum = 10;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddMultipleNumbersEnter() throws Exception {
        int actualSum = stringCalculator.add("1\n2\n3\n4");
        int expectedSum = 10;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddMultipleNumbersDelimiterCombination() throws Exception {
        int actualSum = stringCalculator.add("1,2\n3,4");
        int expectedSum = 10;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddInvalidFormatNA(){
        Exception exception = assertThrows(Exception.class, () ->
            stringCalculator.add("qwerty"));
        String expectedMessage = "Invalid input, expected format: num[,][\\n]num..";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testAddInvalidFormatDelimiter() {
        Exception exception = assertThrows(Exception.class, () ->
            stringCalculator.add("1, 2"));
        String expectedMessage = "Invalid input, expected format: num[,][\\n]num..";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testAddCustomDelimiter() throws Exception {

        int actualSum = stringCalculator.add("//&&\n1&&2&&3&&4");
        int expectedSum = 10;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddCustomDelimiterRepetition() throws Exception {

        int actualSum = stringCalculator.add("//&&\n1&&2&&3&&4");
        int expectedSum = 10;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddCustomInvalidDelimiterFormat() {

        Exception exception = assertThrows(Exception.class, () ->
            stringCalculator.add("//&&\n1&g&&2&&3&&&&4"));
        String expectedMessage = "Invalid input, expected format: //delimiter\nnum[delimiter]num..";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void arraySum() throws Exception {
        int actualSum = stringCalculator.arraySum("1,h2,h3", ",h",
                "Invalid input, expected format: num[,][\\n]num..");
        int expectedSum = 6;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void arraySumInvalidFormat() {
        Exception exception = assertThrows(Exception.class, () ->
            stringCalculator.arraySum("1,h2,h3y,h", ",h",
                    "Invalid input, expected format: num[,][\\n]num.."));
        String expectedMessage = "Invalid input, expected format: num[,][\\n]num..";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void delimiterCheckerValid(){
        boolean actualSum = stringCalculator.delimiterChecker("[eew][ew][gdfd]");
        assertTrue("delimiterCheckerValid failed", actualSum);
    }
    @Test
    public void delimiterCheckerInValid1(){
        boolean actualSum = stringCalculator.delimiterChecker("[eew][ew]gdfd]");
        assertFalse("delimiterCheckerInValid1 failed", actualSum);
    }
    @Test
    public void delimiterCheckerInValid2(){
        boolean actualSum = stringCalculator.delimiterChecker("[eeewgdfd");
        assertFalse("delimiterCheckerInValid2 failed", actualSum);
    }
    @Test
    public void delimiterCheckerValid3(){
        boolean actualSum = stringCalculator.delimiterChecker("[eew][ew][gdfd");
        assertFalse("delimiterCheckerValid3 invalid", actualSum);
    }
    @Test
    public void testAddCustomMultipleDelimiter() throws Exception {

        int actualSum = stringCalculator.add("//[$$][.]\n1$$2.3$$4");
        int expectedSum = 10;
        Assert.assertEquals(expectedSum, actualSum);
    }
    @Test
    public void testAddMoreThanThousand() throws Exception {
        int actualSum = stringCalculator.add("1000,999,1001");
        Assert.assertEquals(1999, actualSum);
    }
    


}

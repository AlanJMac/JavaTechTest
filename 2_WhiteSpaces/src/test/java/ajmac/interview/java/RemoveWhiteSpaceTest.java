package ajmac.interview.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveWhiteSpaceTest {

    @Test
    public void testEmptyString() {
        String input = "";

        RemoveWhiteSpace removeWhiteSpace = new RemoveWhiteSpace();
        String result = removeWhiteSpace.removeWhiteSpace(input);

        assertEquals("", result);
    }

    @Test
    public void testStringWithoutWhiteSpaces() {
        String input = "NoWhiteSpacesHere";

        RemoveWhiteSpace removeWhiteSpace = new RemoveWhiteSpace();
        String result = removeWhiteSpace.removeWhiteSpace(input);

        assertEquals("NoWhiteSpacesHere", result);
    }

    @Test
    public void testMultipleSpaces() {
        String input = "  This had    several spa ces     .";

        RemoveWhiteSpace removeWhiteSpace = new RemoveWhiteSpace();
        String result = removeWhiteSpace.removeWhiteSpace(input);

        assertEquals("Thishadseveralspaces.", result);
    }

    @Test
    public void testStringWithTabs() {
        String input = "Test\tTab\tSpaced\tString";

        RemoveWhiteSpace removeWhiteSpace = new RemoveWhiteSpace();
        String result = removeWhiteSpace.removeWhiteSpace(input);

        assertEquals("TestTabSpacedString", result);
    }

    @Test
    public void testStringWithLineFeedReturn() {
        String input = "Unix\nWindows\r\nReturn\r";

        RemoveWhiteSpace removeWhiteSpace = new RemoveWhiteSpace();
        String result = removeWhiteSpace.removeWhiteSpace(input);

        assertEquals("UnixWindowsReturn", result);
    }

    @Test
    public void testMixedString() {
        String input = " Space \tTabbed\nNewLine\r\nEnd . ";

        RemoveWhiteSpace removeWhiteSpace = new RemoveWhiteSpace();
        String result = removeWhiteSpace.removeWhiteSpace(input);

        assertEquals("SpaceTabbedNewLineEnd.", result);
    }
}

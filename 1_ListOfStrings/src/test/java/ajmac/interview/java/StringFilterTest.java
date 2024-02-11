package ajmac.interview.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StringFilterTest {

    @Test()
    public void testFilterNoMatch() {
        StringFilter filter = new StringFilter();

        List<String> results = filter.filterList(Arrays.asList("b", "abcd", "ABC"));
        assertTrue(results.isEmpty());
    }

    @Test()
    public void testFilterMatchAll() {
        StringFilter filter = new StringFilter();

        List<String> results = filter.filterList(Arrays.asList("abc", "all"));
        assertEquals(2, results.size());
    }

    @Test()
    public void testFilterMatchOne() {
        StringFilter filter = new StringFilter();

        List<String> results = filter.filterList(Arrays.asList("abc", "not"));
        assertEquals(1, results.size());
    }

    @Test()
    public void testFilterNoMatchWhiteSpace() {
        StringFilter filter = new StringFilter();

        List<String> results = filter.filterList(Arrays.asList(" abc", "all"));
        assertEquals(1, results.size());
    }
}

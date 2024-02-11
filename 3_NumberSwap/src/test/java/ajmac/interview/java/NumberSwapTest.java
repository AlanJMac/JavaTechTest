package ajmac.interview.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberSwapTest {

    @Test
    public void testSwap() {
        int input1 = 1;
        int input2 = 2;

        NumberSwap numberSwap = new NumberSwap();
        double[] swapped = numberSwap.swap(input1, input2);

        assertEquals(input2, swapped[0], 0);
        assertEquals(input1, swapped[1], 0);
    }

    @Test
    public void testSwapNegative() {
        double input1 = 1.5;
        double input2 = -2.7;

        NumberSwap numberSwap = new NumberSwap();
        double[] swapped = numberSwap.swap(input1, input2);

        assertEquals(input2, swapped[0], 0);
        assertEquals(input1, swapped[1], 0);
    }

    @Test
    public void testSwapBothNegative() {
        double input1 = -1.5;
        double input2 = -2.7;

        NumberSwap numberSwap = new NumberSwap();
        double[] swapped = numberSwap.swap(input1, input2);

        assertEquals(input2, swapped[0], 0);
        assertEquals(input1, swapped[1], 0);
    }

    @Test
    public void testSwapZero() {
        double input1 = 0;
        double input2 = -2.7;

        NumberSwap numberSwap = new NumberSwap();
        double[] swapped = numberSwap.swap(input1, input2);

        assertEquals(input2, swapped[0], 0);
        assertEquals(input1, swapped[1], 0);
    }
}

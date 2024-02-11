package ajmac.interview.java;

import java.util.logging.Logger;

public class NumberSwap {

    private static final Logger LOGGER = Logger.getLogger(NumberSwap.class.getSimpleName());

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            throw new Exception("Please provide two numbers as arguments.");
        }

        double first = Double.parseDouble(args[0]);
        double second = Double.parseDouble(args[1]);

        NumberSwap numberSwap = new NumberSwap();
        numberSwap.swap(first, second);
    }

    /**
     * Method created for the purposes of running unit tests.
     * Although the return value means there is a third variable involved.
     * 
     * @param first
     * @param second
     * @return
     */
    public double[] swap(double first, double second) {

        LOGGER.info("Before swap, First: [" + first + "], Second: [" + second + "].");

        first = first + second;
        second = first - second;
        first = first - second;

        LOGGER.info("After swap, First: [" + first + "], Second: [" + second + "].");
        return new double[] { first, second };
    }
}
package ajmac.interview.java;

import java.util.Arrays;
import java.util.logging.Logger;

public class RemoveWhiteSpace {
    private static final Logger LOGGER = Logger.getLogger(RemoveWhiteSpace.class.getSimpleName());

    /**
     * 
     * @param args Arguments assumed to be quoted, otherwise whitespace is ignored
     *             on input anyway
     */
    public static void main(String[] args) {

        String input = String.join("", Arrays.asList(args));

        RemoveWhiteSpace action = new RemoveWhiteSpace();
        String result = action.removeWhiteSpace(input);

        LOGGER.info("Result: " + result);
    }

    public String removeWhiteSpace(String inputString) {
        String[] splitOnWhiteSpace = inputString.split("\\s+");
        return String.join("", splitOnWhiteSpace);
    }
}
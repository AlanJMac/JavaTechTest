package ajmac.interview.java;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StringFilter {

    private static final Logger LOGGER = Logger.getLogger(StringFilter.class.getSimpleName());

    /**
     * Program main entry point
     * 
     * @param args An array of Strings
     */
    public static void main(String[] args) {
        List<String> stringsToFilter = Arrays.asList(args);

        StringFilter filter = new StringFilter();
        List<String> filteredStrings = filter.filterList(stringsToFilter);

        LOGGER.info("Filtered Result: " + filteredStrings);
    }

    /**
     * Filter input strings to return only those that start with the letter ‘a’
     * (lower case) and have exactly 3 letters.
     * 
     * @param inputStrings
     * @return
     */
    public List<String> filterList(List<String> inputStrings) {
        return inputStrings.stream().filter(s -> s.startsWith("a") && s.length() == 3).collect(Collectors.toList());
    }
}
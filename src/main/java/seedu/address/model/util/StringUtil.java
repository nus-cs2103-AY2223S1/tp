package seedu.address.model.util;

/**
 * Contains utility methods for handling Strings.
 */
public class StringUtil {
    /**
     * Returns a given name String in proper case format.
     * @param s Input address String to be converted to proper case.
     * @return String in proper case format.
     */
    public static String toProperCase(String s) {
        String[] strArr = s.split(" ");
        StringBuilder properCaseString = new StringBuilder();
        int count = 0;
        for (String str : strArr) {
            String word = str;
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            if (count == strArr.length - 1) {
                properCaseString.append(word);
            } else {
                properCaseString.append(word).append(" ");
            }
            count++;
        }
        return properCaseString.toString();
    }
}

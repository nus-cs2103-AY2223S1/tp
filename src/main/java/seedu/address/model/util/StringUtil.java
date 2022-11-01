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
        StringBuilder properCaseAddress = new StringBuilder();
        int count = 0;
        for (String str : strArr) {
            String word = str;
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            if (count == strArr.length - 1) {
                properCaseAddress.append(word);
            } else {
                properCaseAddress.append(word).append(" ");
            }
            count++;
        }
        return properCaseAddress.toString();
    }
}

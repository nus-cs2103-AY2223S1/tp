package seedu.address.testutil;

/**
 * A utitlity class for Person's Contact attributes
 */
public class ContactUtil {

    // Reference: https://stackoverflow.com/a/8234912
    public static final String URL_REGEX =
            "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|"
            + "(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?"
            + "\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)";

    /**
     * Check whether the given string is a valid url
     * @param url string to check
     * @return boolean to indicate whether is a valid url
     */
    public static boolean isValidUrl(String url) {
        return url.matches(URL_REGEX);
    }
}

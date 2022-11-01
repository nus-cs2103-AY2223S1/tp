package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.util.AppUtil.checkArgument;

/**
 * Represents the website of the company of the internship.
 */
public class Website {
    public static final int MAX_LENGTH = 2048;
    public static final String VALIDATION_WITHOUT_PATH_REGEX =
            "^https?://([a-zA-Z0-9]+[-.])*([a-zA-Z0-9]+[.])([a-zA-Z0-9]+[a-zA-Z])$";

    public static final String VALIDATION_WITH_PATH_REGEX =
            "^https?://([a-zA-Z0-9]+[-.])*([a-zA-Z0-9]+[.])([a-zA-Z0-9]+[a-zA-Z]/)[-a-zA-Z0-9+;,/?:@&=$_.!~*'()#]*$";

    public static final String DEFAULT_VALUE = "NA";
    private static final String SPECIAL_CHARACTERS = ";,/?:@&=+$-_.!~*'()#";
    public static final String MESSAGE_CONSTRAINTS =
            "Website must be of the format PROTOCOL://DOMAIN-NAME/PATH-QUERY-FRAGMENT"
            + " and adhere to the following constraints:\n"
            + "1. The protocol must either be \"https\" or \"http\".\n"
            + "2. The domain name is made up of domain labels separated by periods.\n"
            + "The domain name must:\n"
            + "    - have at least 2 domain labels, with the last label being the top level domain label.\n"
            + "    - have the top level domain label ends with an alphabet.\n"
            + "    - have the top level domain label at least 2 characters long.\n"
            + "    - have the top level domain label separated from the other domain labels by a period.\n"
            + "    - have each domain label start and end with alphanumeric characters.\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.\n"
            + "3. The path-query-fragment should only contain alphanumeric characters and these special "
            + "characters, excluding the outer parentheses, ( " + SPECIAL_CHARACTERS + ").\n"
            + "4. Website must not exceed 2048 characters.";

    public final String value;

    /**
     * Constructs an {@code Website}.
     *
     * @param website A valid website internship.
     */
    public Website(String website) {
        requireNonNull(website);
        checkArgument(isValidWebsite(website), MESSAGE_CONSTRAINTS);
        value = website;
    }

    /**
     * Returns true if a given string is a valid website.
     */
    public static boolean isValidWebsite(String test) {
        return test.length() <= MAX_LENGTH && (test.matches(VALIDATION_WITHOUT_PATH_REGEX)
                || test.matches(VALIDATION_WITH_PATH_REGEX) || test.equals(DEFAULT_VALUE));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Website // instanceof handles nulls
                && value.equals(((Website) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

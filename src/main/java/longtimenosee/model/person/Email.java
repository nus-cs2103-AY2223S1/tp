package longtimenosee.model.person;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

import java.util.Comparator;



/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {


    public static final Comparator<Person> EMAIL_COMPARATOR = new Comparator<Person>() {
        public int compare(Person p1, Person p2) {
            int platformCompare = p1.getEmail().getPlatform().compareTo(p2.getEmail().getPlatform());
            int nameCompare = p1.getEmail().getEmailName().toLowerCase()
                    .compareTo(p2.getEmail().getEmailName().toLowerCase());
            if (platformCompare == 0) { // same email platform used
                return nameCompare;
            }
            return platformCompare;
        }
    };
    public static final String SORT_EMAIL = "email";
    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";

    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String AT_SYMBOL = "@";
    private static final String DOT = ".";
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the email name before the @ symbol
     */
    private String getEmailName() {
        return this.value.substring(0, value.indexOf(AT_SYMBOL));
    }

    /**
     * Returns the email platform which the email is using
     * eg. example@gmail.com will return gmail
     */
    private String getPlatform() {
        int index = value.indexOf(AT_SYMBOL);
        String temp = value.substring(index + 1, value.length());
        return temp.substring(0, temp.indexOf(DOT));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

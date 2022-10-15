package seedu.address.model.person.github;

/**
 * Represents a GitHub's User
 */
public class User {

    private static final String GITHUB_PREFIX = "https://github.com/";
    private static final String VALIDATION_REGEX = "^@(?=.{5,32}$)(?!.*__)[A-Za-z][A-Za-z0-9_]*[A-Za-z0-9]$";
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

    private String username = "";

    /**
     * Constructs a GitHub's user
     *
     * @param username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Returns true if a given string is a valid GitHub's username
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return GITHUB_PREFIX + username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof User)
                && username.equals(((User) other).username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}

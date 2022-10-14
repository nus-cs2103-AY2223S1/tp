package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's location in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUsername(String)} (String)}
 */
public class GithubUsername {

    public static final String MESSAGE_CONSTRAINTS = "Github username must follow the following rules: \n"
            + "Github username cannot have multiple consecutive hyphens.\n"
            + "Github username cannot begin or end with a hyphen.\n"
            + "Maximum is 39 characters.";

    public static final String DEFAULT_USERNAME = "";

    /*
     * Github username may only contain alphanumeric characters or hyphens.
     * Github username cannot have multiple consecutive hyphens.
     * Github username cannot begin or end with a hyphen.
     * Maximum is 39 characters.
     */
    public static final String VALIDATION_REGEX = "^(?i)[a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38}$";

    public final String value;

    /**
     * Constructs a Location.
     *
     * @param username A valid github username.
     * @param isPresent Whether prefix was present in user input
     */
    public GithubUsername(String username, Boolean isPresent) {
        requireNonNull(username);
        if (isPresent) {
            checkArgument(isValidUsername(username), MESSAGE_CONSTRAINTS);
            value = username;
        } else {
            value = DEFAULT_USERNAME;
        }
    }

    /**
     * Returns true if a given string is a valid username.
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static GithubUsername getGithubUsername(ArgumentMultimap argMultimap) throws ParseException {
        GithubUsername username;
        if (argMultimap.getValue(PREFIX_GITHUBUSERNAME).isEmpty()) {
            username = new GithubUsername("", false);
        } else {
            username = ParserUtil.parseGitHubUsername(argMultimap.getValue(PREFIX_GITHUBUSERNAME).get());
        }
        return username;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubUsername // instanceof handles nulls
                && value.equals(((GithubUsername) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.person.github;
//@@author Vshnv2001- unused
// Unused as class was rewritten to account for Github API.
// This class (unused) did not include avatar, repolist, or avatar image file path.

/**
 * Class to store Github username of Person.
 */
public class GithubUser {
    private String username = "";
    private final String GITHUB_PREFIX = "https://github.com/";
    public static final String VALIDATION_REGEX = "^@(?=.{5,32}$)(?!.*__)[A-Za-z][A-Za-z0-9_]*[A-Za-z0-9]$";
    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Github Usernames should be of the format @githubusername "
            + "and adhere to the following constraints:\n"
            + "1. The username should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The username may not start or end with any special "
            + "characters.\n";

    public GithubUser(String username) {
        this.username = username;
    }

    public void setGithub(String newUsername) {
        this.username = newUsername;
    }

    public String getGithub() {
        return this.username;
    }

    public String getLink() {
        return this.GITHUB_PREFIX + this.username;
    }

    public static boolean isValidGithub(String username) {
        return username.matches(VALIDATION_REGEX);
    }

    public String ifPresent(Object object) {
        return null;
    }
}
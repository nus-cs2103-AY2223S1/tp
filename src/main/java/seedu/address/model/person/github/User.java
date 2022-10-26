package seedu.address.model.person.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import seedu.address.wrapper.UserInfoWrapper;
import seedu.address.wrapper.UserReposWrapper;

/**
 * Represents a GitHub's User
 */
public class User {

    public static final String MESSAGE_CONSTRAINTS = "GitHub usernames should be of the format @username "
            + "and adhere to the following constraints:\n"
            + "1. Username may only contain alphanumeric characters or hyphens\n"
            + "2. Username cannot have multiple consecutive hyphens\n"
            + "3. Username cannot begin or end with a hyphen\n"
            + "4. Username can have a maximum of 39 characters";
    private static final String GITHUB_PREFIX = "https://github.com/";
    private static final String VALIDATION_REGEX = "^[a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38}$";
    private final String username;
    private final String avatarUrl;
    private final String url;
    private final String name;
    private final String email;
    private final String address;
    private final UserReposWrapper userReposWrapper;

    /**
     * Constructs a GitHub's user
     *
     * @param username Username corresponding to user to be added
     */
    public User(String username, UserInfoWrapper userInfoWrapper, UserReposWrapper userReposWrapper) {
        requireAllNonNull(username);
        this.userReposWrapper = userReposWrapper;
        this.username = userInfoWrapper.getUsername();
        this.avatarUrl = userInfoWrapper.getAvatarUrl();
        this.url = userInfoWrapper.getUrl();
        this.name = userInfoWrapper.getName().orElse(this.username);
        this.email = userInfoWrapper.getEmail().orElse("");
        this.address = userInfoWrapper.getLocation().orElse("");
    }

    /**
     * Returns true if a given string is a valid GitHub's username
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(this.email);
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(this.address);
    }

    //TODO/open: NEED TO UPDATE
    public ArrayList<Integer> getRepoIds() {
        return userReposWrapper.getIDs();
    }

    public String getRepoName(int id) {
        return userReposWrapper.getRepoName(id);
    }

    public String getRepoUrl(int id) {
        return userReposWrapper.getRepoUrl(id);
    }

    //TODO/close: NEED TO UPDATE

    @Override
    public String toString() {
        return GITHUB_PREFIX + username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof User)
                && username.equals(((User) other).username)
                && avatarUrl.equals(((User) other).avatarUrl)
                && url.equals(((User) other).url)
                && name.equals(((User) other).name)
                && email.equals(((User) other).email)
                && address.equals(((User) other).address)
                && userReposWrapper.equals(((User) other).userReposWrapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, avatarUrl, url, name, email, address, userReposWrapper);
    }
}

package seedu.address.model.person.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import seedu.address.github.UserInfoWrapper;
import seedu.address.github.UserReposWrapper;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.contact.Email;
import seedu.address.model.person.github.repo.Repo;
import seedu.address.model.person.github.repo.RepoList;

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
    private final String url;
    private final Name name;
    private final Email email;
    private final Address address;
    private final RepoList repoList;

    /**
     * Constructs a GitHub's user
     *
     * @param username Username corresponding to user to be added
     */
    public User(String username, UserInfoWrapper userInfoWrapper, UserReposWrapper userReposWrapper) {
        requireAllNonNull(username);
        this.username = userInfoWrapper.getUsername();
        this.url = userInfoWrapper.getUrl();
        this.name = new Name(userInfoWrapper.getName().orElse(this.username));
        this.email = userInfoWrapper.getEmail().isPresent() ? new Email(userInfoWrapper.getEmail().get()) : null;
        this.address =
                userInfoWrapper.getLocation().isPresent() ? new Address(userInfoWrapper.getLocation().get()) : null;
        userInfoWrapper.downloadAvatar();
        this.repoList = getUpdatedRepoList(userReposWrapper);
    }

    /**
     * Returns true if a given string is a valid GitHub's username
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Name getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUrl() {
        return this.url;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(this.email);
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(this.address);
    }

    public RepoList getRepoList() {
        return this.repoList;
    }

    private RepoList getUpdatedRepoList(UserReposWrapper userReposWrapper) {
        RepoList repoList = new RepoList();
        for (int repoId : getRepoIds(userReposWrapper)) {
            repoList.add(new Repo(
                    userReposWrapper.getRepoName(repoId),
                    userReposWrapper.getRepoUrl(repoId),
                    userReposWrapper.getRepoForkCount(repoId),
                    userReposWrapper.getLastUpdated(repoId)
            ));
        }
        return repoList;
    }

    public ArrayList<Integer> getRepoIds(UserReposWrapper userReposWrapper) {
        return userReposWrapper.getIDs();
    }

    @Override
    public String toString() {
        return GITHUB_PREFIX + username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof User)
                && username.equals(((User) other).username)
                && url.equals(((User) other).url)
                && name.equals(((User) other).name)
                && email.equals(((User) other).email)
                && address.equals(((User) other).address)
                && repoList.equals(((User) other).repoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, url, name, email, address);
    }
}

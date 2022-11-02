package seedu.address.model.person.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.github.UserInfoWrapper;
import seedu.address.github.UserReposWrapper;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.contact.Email;

/**
 * Represents a GitHub's User
 */
public class User {

    public static final String MESSAGE_CONSTRAINTS =
        "GitHub usernames should contain only alphanumeric characters or dashes and adhere to the following "
            + "constraints:\n"
            + "1. Username may only contain alphanumeric characters or hyphens\n"
            + "2. Username cannot have multiple consecutive hyphens\n"
            + "3. Username cannot begin or end with a hyphen\n"
            + "4. Username can have a maximum of 39 characters";
    private static final String BASE_GITHUB_URL = "https://github.com/";
    private static final String VALIDATION_REGEX = "^[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}$";
    private final String username;
    private final String url;
    private final Name name;
    private final Email email;
    private final Address address;
    private final Path avatarImageFilePath;
    private final List<Repo> repoList = new ArrayList<>();

    /**
     * Constructs a GitHub's user
     *
     * @param username Username corresponding to user to be added
     */
    public User(String username, UserInfoWrapper userInfoWrapper, UserReposWrapper userReposWrapper) {
        requireAllNonNull(username, userInfoWrapper, userReposWrapper);
        this.username = userInfoWrapper.getUsername();
        this.url = userInfoWrapper.getUrl();
        this.name = new Name(userInfoWrapper.getName().orElse(this.username));
        this.email = userInfoWrapper.getEmail().isPresent() ? new Email(userInfoWrapper.getEmail().get()) : null;
        this.address =
            userInfoWrapper.getLocation().isPresent() ? new Address(userInfoWrapper.getLocation().get()) : null;

        userInfoWrapper.downloadAvatar();
        updateRepoList(userReposWrapper);
        this.avatarImageFilePath = getAvatarPathIfExists(userInfoWrapper.getAvatarImageFilePath());
    }

    /**
     * @param username Username for the GitHub User class to be initiated with
     * @param repoList RepoList for the GitHub User class to be initiated with
     */
    public User(String username, List<Repo> repoList) {
        requireAllNonNull(username, repoList);
        this.username = username;
        this.name = new Name(username);
        this.url = BASE_GITHUB_URL + username;
        this.repoList.addAll(repoList);
        this.email = null;
        this.address = null;
        this.avatarImageFilePath = getAvatarFilePathFromUsername(username);
    }

    /**
     * Returns true if a given string is a valid GitHub's username
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private Path getAvatarFilePathFromUsername(String username) {
        String imageFileName = username + ".png";
        UserPrefs userPrefs = new UserPrefs();
        Path avatarImageFilePath =
            Paths.get(userPrefs.getAddressBookFilePath().getParent().toString(), "images", imageFileName);
        return getAvatarPathIfExists(avatarImageFilePath);
    }

    private Path getAvatarPathIfExists(Path avatarImageFilePath) {
        return avatarImageFilePath.toFile().exists() ? avatarImageFilePath : null;
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

    public List<Repo> getRepoList() {
        return Collections.unmodifiableList(this.repoList);
    }

    public Optional<Path> getAvatarImageFilePath() {
        return Optional.ofNullable(avatarImageFilePath);
    }

    private void updateRepoList(UserReposWrapper userReposWrapper) {
        for (int repoId : getRepoIds(userReposWrapper)) {
            repoList.add(new Repo(
                userReposWrapper.getRepoName(repoId),
                userReposWrapper.getRepoUrl(repoId),
                userReposWrapper.getDescription(repoId).orElse(null),
                userReposWrapper.getLastUpdated(repoId)
            ));
        }
    }

    public ArrayList<Integer> getRepoIds(UserReposWrapper userReposWrapper) {
        return userReposWrapper.getIDs();
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) { //this handles null as well.
            return false;
        }

        User other = (User) obj;

        return username.equals(other.getUsername())
            && url.equals(other.getUrl())
            && name.equals(other.getName())
            && Optional.ofNullable(email).equals(other.getEmail())
            && Optional.ofNullable(address).equals(other.getAddress())
            && repoList.equals(other.getRepoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, url, name, email, address);
    }
}

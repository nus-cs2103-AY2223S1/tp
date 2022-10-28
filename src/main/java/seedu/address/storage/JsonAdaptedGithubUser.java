package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.github.Repo;
import seedu.address.model.person.github.User;

/**
 * Jackson-friendly version of {@link User}.
 */
public class JsonAdaptedGithubUser {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Github user's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Github user's %s field is invalid!";
    private final String username;
    private final List<JsonAdaptedGithubRepo> repoList = new ArrayList<>();


    /**
     * Converts to JsonAdaptedContact with given {@code String} and  {@code List<JsonAdaptedGithubRepo>} values.
     */
    @JsonCreator
    public JsonAdaptedGithubUser(@JsonProperty("Username") String username,
                                 @JsonProperty("Repositories") List<JsonAdaptedGithubRepo> repoList) {
        this.username = username;
        if (repoList != null) {
            this.repoList.addAll(repoList);
        }
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     */
    public JsonAdaptedGithubUser(User source) {
        username = source.getUsername();
        repoList.addAll(source.getRepoList().stream()
            .map(JsonAdaptedGithubRepo::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code User} object
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact
     */
    public User toModelType() throws IllegalValueException {
        final List<Repo> repoList = new ArrayList<>();
        for (JsonAdaptedGithubRepo repo : this.repoList) {
            repoList.add(repo.toModelType());
        }

        if (username == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "username"));
        } else if (!User.isValidUsername(username)) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT, "username"));
        }

        return new User(username, repoList);
    }
}

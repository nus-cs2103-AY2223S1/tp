package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.github.Repo;

/**
 * Jackson-friendly version of {@link Repo}.
 */
public class JsonAdaptedGithubRepo {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Repo's %s field is missing!";
    private final String name;
    private final String url;
    private final String description;
    private final LocalDateTime lastUpdated;


    /**
     * Converts to JsonAdaptedContact with given {@code String} and {@code int} and {@code LocalDateTime} values.
     */
    @JsonCreator
    public JsonAdaptedGithubRepo(@JsonProperty("Name") String name, @JsonProperty("URL") String url,
                                 @JsonProperty("Description") String description,
                                 @JsonProperty("lastUpdated") LocalDateTime lastUpdated) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Converts a given {@code Repo} into this class for Jackson use.
     */
    public JsonAdaptedGithubRepo(Repo source) {
        name = source.getRepoName();
        url = source.getRepoUrl();
        description = source.getDescription().orElse(null);
        lastUpdated = source.getLastUpdated();
    }


    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Repo} object
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact
     */
    public Repo toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (url == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "url"));
        }
        if (lastUpdated == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "last updated"));
        }
        return new Repo(name, url, description, lastUpdated);
    }
}

package seedu.address.model.person.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Optional;

//@@author Vshnv2001
/**
 * Class for each individual repository for a specified GitHub.
 */
public class Repo {
    private final String repoName;
    private final String repoUrl;
    private final String description;
    private final LocalDateTime lastUpdated;

    /**
     * Constructor for the Repo Class, initializes all the fields.
     *
     * @param repoName    the name of the GitHub repository
     * @param repoUrl     the predominant language used in the repository
     * @param description the description of the GitHub repository
     * @param lastUpdated A String containing the date/time the repo was last updated.
     */
    public Repo(String repoName, String repoUrl,
                String description, LocalDateTime lastUpdated) {
        requireAllNonNull(repoName, repoUrl, lastUpdated);
        this.repoName = repoName;
        this.repoUrl = repoUrl;
        this.description = description;
        this.lastUpdated = lastUpdated;
    }

    public String getRepoUrl() {
        return this.repoUrl;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public String getRepoName() {
        return this.repoName;
    }

}

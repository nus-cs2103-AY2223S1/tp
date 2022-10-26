package seedu.address.model.person.github.repo;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

/**
 * Class for each individual repository for a specified GitHub.
 */
public class Repo {
    private final String repoName;
    private final String repoUrl;
    private final int numberForks;
    private final LocalDateTime lastUpdated;

    /**
     * Constructor for the Repo Class, initializes all the fields.
     *
     * @param repoName    the name of the GitHub repository
     * @param repoUrl     the predominant language used in the repository
     * @param numberForks the number of times the repo has been forked
     * @param lastUpdated A String containing the date/time the repo was last updated.
     */
    public Repo(String repoName, String repoUrl,
                int numberForks, LocalDateTime lastUpdated) {
        requireAllNonNull(repoName, repoUrl, numberForks, lastUpdated);
        this.repoName = repoName;
        this.repoUrl = repoUrl;
        this.numberForks = numberForks;
        this.lastUpdated = lastUpdated;
    }

    public String getRepoUrl() {
        return this.repoUrl;
    }

    public int getNumberForks() {
        return this.numberForks;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public String getRepoName() {
        return this.repoName;
    }

}

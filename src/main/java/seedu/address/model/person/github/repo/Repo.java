package seedu.address.model.person.github.repo;

/**
 * Class for each individual repository for a specified GitHub.
 */
public class Repo {
    private final String repoName;
    private final String language;
    private final int numberForks;
    private final String license;
    private final String lastUpdated;

    /**
     * Constructor for the Repo Class, initializes all the fields.
     * @param repoName the name of the GitHub repository
     * @param language the predominant language used in the repository
     * @param numberForks the number of times the repo has been forked
     * @param license The license of the repo
     * @param lastUpdated A String containing the date/time the repo was last updated.
     */
    public Repo(String repoName, String language,
                int numberForks, String license, String lastUpdated) {
        this.repoName = repoName;
        this.language = language;
        this.numberForks = numberForks;
        this.license = license;
        this.lastUpdated = lastUpdated;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getNumberForks() {
        return this.numberForks;
    }

    public String getLicense() {
        return this.license;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public String getRepoName() {
        return this.repoName;
    }

}

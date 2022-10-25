package seedu.address.model.person.github.repo;

/**
 * Class for each individual repository for a specified GitHub.
 */
public class Repo {
    private String repoName;
    private String language = "";
    private int numberForks = 0;
    private String license = "";
    private String lastUpdated = "";

    public Repo(String repoName) {
        this.repoName = repoName;
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

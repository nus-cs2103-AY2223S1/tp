package seedu.address.model.person.github.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// @@author Vshnv2001-unused
// This class was unused as the List of Repos was accessed directly
// from the User class after this class was made.

/**
 * Container for all the repositories of the class.
 */
public class RepoList {
    private List<Repo> repoList = new ArrayList<>();

    /**
     * Constructor for RepoList Class.
     */
    public RepoList() {}

    public RepoList(List<Repo> repoList) {
        this.repoList = repoList;
    }

    public List<String> getRepoNames() {
        return repoList.stream().map(repo -> repo.getRepoName()).collect(Collectors.toList());
    }

    public int getNumberOfRepos() {
        return repoList.size();
    }

    /**
     * Method to add new Repo to Existing RepoList.
     * @param nextRepo the next Repo Object to be added.
     * @return the same repolist with updated new repository.
     */
    public RepoList addRepo(Repo nextRepo) {
        this.repoList.add(nextRepo);
        return this;
    }

    /**
     * Empties all the repositories in the given repository list.
     * @return Same Repolist after clearing out all the repositories.
     */
    public RepoList clearRepoList() {
        this.repoList.clear();
        return this;
    }

}
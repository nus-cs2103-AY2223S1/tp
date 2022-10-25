package seedu.address.model.person.github.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Container for all the repositories of the class.
 */
public class RepoList {
    private List<Repo> repoList = new ArrayList<>();

    /**
     * Constructor for RepoList Class.
     */
    public RepoList() {}

    public List<String> getRepoNames() {
        return repoList.stream().map(repo -> repo.getRepoName()).collect(Collectors.toList());
    }

    public int getNumberOfRepos() {
        return repoList.size();
    }
}

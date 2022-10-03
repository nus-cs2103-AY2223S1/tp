package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's repository.
 */
public class Repository {

    public String projectRepository;

    /**
     * Construct's a project Repository.
     *
     * @param repository A valid repository name.
     */
    public Repository(String repository) {
        requireNonNull(repository);
        this.projectRepository = repository;
    }

    @Override
    public String toString() {
        return projectRepository;
    }
}

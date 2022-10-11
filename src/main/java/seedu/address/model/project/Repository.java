package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's repository.
 */
public class Repository {

    /**
     * Represents an empty Project repository.
     */
    public static class EmptyRepository extends Repository {
        public static final Repository EMPTY_REPOSITORY = new EmptyRepository();

        private EmptyRepository() {
            super("conrad/tp");
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Repository should be entered in <Username/RepoName> format";

    /*
     * Username should have a maximum of 39 characters with 1 or more alphanumeric characters.
     * Dashes are allowed.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]([a-zA-Z0-9-]{0,38})/[a-zA-Z0-9-_.]+";

    private String projectRepository;

    /**
     * Construct's a project Repository.
     *
     * @param repository A valid repository name.
     */
    public Repository(String repository) {
        requireNonNull(repository);
        checkArgument(isValidRepository(repository), MESSAGE_CONSTRAINTS);
        this.projectRepository = repository;
    }

    /**
     * Returns true if a given string is a valid repository.
     */
    public static boolean isValidRepository(String repository) {
        return repository.matches(VALIDATION_REGEX);
    }

    public String getRepositoryUrl() {
        return "https://github.com/" + projectRepository;
    }
    @Override
    public String toString() {
        return projectRepository;
    }
}

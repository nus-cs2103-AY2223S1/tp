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

        /**
         * Checks if this Repository is empty.
         * @return true since the Repository is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "";
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Repository should be entered in <Username/RepoName> format \n"
            + "Username: Maximum of 39 characters with 1 or more alphanumeric characters \n"
            + "Repository: Maximum of 39 characters with 1 or more alphanumeric characters \n"
            + "Dashes are allowed";

    /*
     * Username and repository name should have a maximum of 39 characters with 1 or more alphanumeric characters.
     * Dashes are allowed.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]([a-zA-Z0-9-]{0,38})/[a-zA-Z0-9]([a-zA-Z0-9-]{0,38})";

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
     * Checks if this Repository is empty.
     * @return false since the Repository is not empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns true if a given string is a valid repository.
     */
    public static boolean isValidRepository(String repository) {
        return repository.matches(VALIDATION_REGEX);
    }

    public String getUiRepresentation() {
        return "https://github.com/" + projectRepository;
    }

    @Override
    public String toString() {
        return projectRepository;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Repository // instanceof handles nulls
                && projectRepository.equals(((Repository) other).projectRepository)); // state check
    }
}

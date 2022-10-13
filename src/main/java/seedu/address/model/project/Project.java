package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;

/**
 * Represents a Project.
 */
public class Project {

    //todo: refine message
    public static final String MESSAGE_INVALID = "Project does not exist.";
    // Components of a project
    private Name name;
    private Repository repository;
    private Deadline deadline;
    private Client client;
    private ProjectId projectId;
    private List<Issue> issueList;

    /**
     * Name field must be present and not null and other fields may be optional.
     */
    public Project(Name name, Repository repository, Deadline deadline,
                   Client client, List<Issue> issueList, ProjectId projectId) {
        requireAllNonNull(name, repository, deadline, client);
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.client = client;
        this.issueList = issueList;
        this.projectId = projectId;
    }

    /**
     * Name field must be present and not null .
     */
    public Project(Name name) {
        requireAllNonNull(name);
        this.name = name;
        //todo: set other fields to emptyOptionals post-merge
    }

    /**
     * Represents an Empty Project.
     */
    public static class EmptyProject extends Project {
        public static final Project EMPTY_PROJECT = new EmptyProject();
        private EmptyProject() {
            super(Name.EmptyName.EMPTY_NAME);
        }

        /**
         * Checks if this Project is empty.
         * @return true if the Project is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

    }

    public ProjectId getId() {
        return projectId;
    }
    public Name getProjectName() {
        return name;
    }

    public Repository getRepository() {
        return repository;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Client getClient() {
        return client;
    }

    public List<Issue> getIssueList() {
        return issueList;
    }

    public void removeClient() {
        this.client = Client.EmptyClient.EMPTY_CLIENT;
    }

    /**
     * Checks if this Project is empty.
     * @return true if the Project is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    //todo: create and replace with emptyProject inner class
    public static boolean isValidProject(Project p) {
        return !p.equals(null);
    }
    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getProjectName().equals(getProjectName())
                && otherProject.getRepository().equals(getRepository())
                && otherProject.getDeadline().equals(getDeadline())
                && otherProject.getClient().equals(getClient())
                && otherProject.getIssueList().equals(getIssueList());
    }

    /**
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getProjectName().equals(getProjectName());
    }

    @Override
    public String toString() {
        return this.getProjectName().toString();
    }
}

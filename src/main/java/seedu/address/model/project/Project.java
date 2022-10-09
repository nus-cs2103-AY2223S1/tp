package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.address.model.issue.Issue;
import seedu.address.model.person.client.Client;

/**
 * Represents a Project.
 */
public class Project {

    // Components of a project
    private Name name;
    private Repository repository;
    private Deadline deadline;
    private ArrayList<Client> clientList;
    private ArrayList<Issue> issueList;

    /**
     * Name field must be present and not null and other fields may be optional.
     */
    public Project(Name name, Repository repository, Deadline deadline) {
        requireAllNonNull(name, repository, deadline);
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.clientList = new ArrayList<>();
        this.issueList = new ArrayList<>();
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

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Issue> getIssueList() {
        return issueList;
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
                && otherProject.getClientList().equals(getClientList())
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
}

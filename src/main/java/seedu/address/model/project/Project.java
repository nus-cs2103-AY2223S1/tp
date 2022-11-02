package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.interfaces.ComparableByName;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.issue.Issue;

/**
 * Represents a Project.
 */
public class Project implements ComparableByName<Project>, HasIntegerIdentifier<Project> {

    public static final String MESSAGE_INVALID_DEADLINE_SORT_KEY =
            "Enter either a 0 to sort by chronological order or a 1 to sort by reverse chronological order";

    public static final String MESSAGE_INVALID_ISSUE_COUNT_SORT_KEY =
            "Enter either a 0 to sort by incomplete issue count or a 1 to sort by completed issue count";

    public static final String MESSAGE_INVALID_NAME_SORT_KEY =
            "Enter either a 0 to sort by alphabetical order or a 1 to sort by reverse alphabetical order";

    public static final String MESSAGE_INVALID_PROJECT_ID_SORT_KEY =
            "Enter either a 0 to sort in ascending order or a 1 to sort in descending order";

    // Components of a project
    private Name name;
    private Repository repository;
    private Deadline deadline;
    private Client client;
    private ProjectId projectId;
    private List<Issue> issueList;
    private Pin pin;

    /**
     * Name field must be present and not null and other fields may be optional.
     */
    public Project(Name name, Repository repository, Deadline deadline,
                   Client client, List<Issue> issueList, ProjectId projectId, Pin pin) {
        requireAllNonNull(name);
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.client = client;
        this.issueList = issueList;
        this.projectId = projectId;
        this.pin = pin;
    }

    /**
     * Name field must be present and not null .
     */
    public Project(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.repository = Repository.EmptyRepository.EMPTY_REPOSITORY;
        this.deadline = Deadline.EmptyDeadline.EMPTY_DEADLINE;
        this.client = Client.EmptyClient.EMPTY_CLIENT;
        this.issueList = new ArrayList<>();
        this.projectId = ProjectId.EmptyProjectId.EMPTY_PROJECT_ID;
        this.pin = new Pin(false);
    }

    public void setClient(Client toAddClient) {
        this.client = toAddClient;
    }

    @Override
    public int getId() {
        return this.projectId.getIdInt();
    }

    public void addIssue(Issue toAddIssue) {
        this.issueList.add(toAddIssue);
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
         *
         * @return true if the Project is empty.
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

    public ProjectId getProjectId() {
        return projectId;
    }

    public int getProjectIdInInt() {
        return getProjectId().getIdInt();
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

    public void setName(Name name) {
        this.name = name;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public void removeClient() {
        this.client = Client.EmptyClient.EMPTY_CLIENT;
    }

    public void removeIssue(Issue i) {
        getIssueList().remove(i);
    }

    public void togglePin() {
        this.pin.togglePinned();
    }

    public boolean isPinned() {
        return this.pin.isPinned();
    }

    /**
     * Checks if input is a valid deadline sort key.
     *
     * 0 for chronological order and 1 for reverse chronological order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidDeadlineSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is a valid issue count sort key.
     *
     * 0 for incomplete issue count order and 1 for completed issue count order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidIssueCountSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is a valid name sort key.
     *
     * 0 for alphabetical order and 1 for reverse alphabetical order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidNameSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is a valid project id sort key.
     *
     * 0 for ascending and 1 for descending order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidProjectIdSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if this Project is empty.
     *
     * @return true if the Project is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Counts number of completed issues in Project Issue List
     * @return number of completed issues
     */
    public int getCompletedIssueCount() {
        int count = 0;
        for (Issue i: issueList) {
            if (i.getStatus().getStatus() == true) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Counts number of incomplete issues in Project Issue List
     * @return number of incomplete issues;
     */
    public int getIncompleteIssueCount() {
        int count = 0;
        for (Issue i: issueList) {
            if (i.getStatus().getStatus() == false) {
                count += 1;
            }
        }
        return count;
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
                && otherProject.getIssueList().equals(getIssueList())
                && otherProject.getProjectId().equals(getProjectId())
                && otherProject.getPin().equals(getPin());
    }

    private Pin getPin() {
        return this.pin;
    }

    /**
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two projects.
     */
    @Override
    public boolean hasSameName(Project otherProject) {
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

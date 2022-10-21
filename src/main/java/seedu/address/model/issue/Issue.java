package seedu.address.model.issue;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Deadline;
import seedu.address.model.interfaces.ComparableByName;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.project.Project;

/**
 * Represents an Issue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Issue implements ComparableByName<Issue>, HasIntegerIdentifier<Issue> {

    public static final String MESSAGE_INVALID_DEADLINE_SORT_KEY =
            "Enter either a 0 to sort by chronological order or a 1 to sort by reverse chronological order";

    public static final String MESSAGE_INVALID_PRIORITY_SORT_KEY =
            "Enter either a 0 to sort by lowest priority or a 1 to sort by highest priority";

    // Components of an issue
    private Description description;
    private Deadline deadline;
    private Priority priority;
    private Status status;
    private Project project;
    private IssueId issueId;

    /**
     * Description field and project field must be present and not null, but all other fields are optional.
     */
    public Issue(Description description, Deadline deadline, Priority priority,
                 Status status, Project project, IssueId issueId) {
        requireAllNonNull(description, deadline, priority, status, project, issueId);
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
        this.project = project;
        this.issueId = issueId;
        this.project.getIssueList().add(this);
    }

    /**
     * Description field and project field must be present and not null.
     */
    public Issue(Description description, Project project) {
        requireAllNonNull(description, project);
        this.description = description;
        this.project = project;
        //todo: set other fields to emptyOptionals post-merge
    }

    /**
     * Represents an Empty Issue.
     */
    public static class EmptyIssue extends Issue {
        public static final Issue EMPTY_ISSUE = new EmptyIssue();
        private EmptyIssue() {
            super(Description.EmptyDescription.EMPTY_DESCRIPTION, Project.EmptyProject.EMPTY_PROJECT);
        }

        /**
         * Checks if this Project is empty.
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

    public IssueId getIssueId() {
        return this.issueId;
    }

    public int getIssueIdInInt() {
        return getIssueId().getIdInt();
    }

    public Description getDescription() {
        return this.description;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Project getProject() {
        return this.project;
    }

    public void deleteProjectIssue(Issue i) {
        getProject().removeIssue(i);
    }

    /**
     * Returns true if both issues have the same description.
     * This defines a weaker notion of equality between two issues.
     */
    @Override
    public boolean hasSameName(Issue otherIssue) {
        return otherIssue.description == this.description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Checks if this Issue is empty.
     * @return true if the Issue is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    public String uiRepresentation() {
        return this.description + " " + this.issueId.uiRepresentation();
    }

    //To modify based on format to be saved in json file
    @Override
    public String toString() {
        return this.description.toString();
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
     * Checks if input is a valid priority sort key.
     *
     * 0 for lowest priority order and 1 for highest priority order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidPrioritySortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if both issues have the same description.
     * This defines a weaker notion of equality between two issues.
     */
    public boolean isSameIssue(Issue otherIssue) {
        if (otherIssue == this) {
            return true;
        }

        return otherIssue != null
                && otherIssue.getDescription().equals(getDescription());
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

        if (!(other instanceof Issue)) {
            return false;
        }

        Issue otherIssue = (Issue) other;
        return otherIssue.getDescription().equals(getDescription())
                && otherIssue.getProject().equals(getProject())
                && otherIssue.getDeadline().equals(getDeadline())
                && otherIssue.getStatus().equals(getStatus())
                && otherIssue.getPriority().equals(getPriority())
                && otherIssue.getIssueId().equals(getIssueId());
    }

    @Override
    public int getID() {
        return this.issueId.getIdInt();
    }

}

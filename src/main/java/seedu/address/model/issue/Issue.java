package seedu.address.model.issue;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Deadline;
import seedu.address.model.Pin;
import seedu.address.model.SortCategory;
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

    public static final String MESSAGE_INVALID_URGENCY_SORT_KEY =
            "Enter either a 0 to sort by lowest urgency or a 1 to sort by highest urgency";

    public static final String MESSAGE_INVALID_ISSUE_ID_SORT_KEY =
            "Enter either a 0 to sort in ascending order or a 1 to sort in descending order";

    private static SortCategory sortCategory = SortCategory.ID;
    private static int sortOrder = 0;

    // Components of an issue
    private Title title;
    private Deadline deadline;
    private Urgency urgency;
    private Status status;
    private Project project;
    private IssueId issueId;
    private Pin pin;

    /**
     * Title field and project field must be present and not null, but all other fields are optional.
     */
    public Issue(Title title, Deadline deadline, Urgency urgency,
                 Status status, Project project, IssueId issueId, Pin pin) {
        requireAllNonNull(title, deadline, urgency, status, project, issueId, pin);
        this.title = title;
        this.deadline = deadline;
        this.urgency = urgency;
        this.status = status;
        this.project = project;
        this.issueId = issueId;
        this.project.getIssueList().add(this);
        this.pin = pin;
    }

    /**
     * Title field and project field must be present and not null.
     */
    public Issue(Title title, Project project) {
        requireAllNonNull(title, project);
        this.title = title;
        this.project = project;
        //todo: set other fields to emptyOptionals post-merge
    }

    /**
     * Represents an Empty Issue.
     */
    public static class EmptyIssue extends Issue {
        public static final Issue EMPTY_ISSUE = new EmptyIssue();
        private EmptyIssue() {
            super(Title.EmptyTitle.EMPTY_TITLE, Project.EmptyProject.EMPTY_PROJECT);
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

    public Title getTitle() {
        return this.title;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public Urgency getUrgency() {
        return this.urgency;
    }

    public Project getProject() {
        return this.project;
    }

    public void deleteProjectIssue(Issue i) {
        getProject().removeIssue(i);
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns true if both issues have the same title.
     * This defines a weaker notion of equality between two issues.
     */
    @Override
    public boolean hasSameName(Issue otherIssue) {
        return otherIssue.title == this.title;
    }



    public void setTitle(Title title) {
        this.title = title;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
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

    public void togglePin() {
        this.pin.togglePinned();
    }

    public boolean isPinned() {
        return this.pin.isPinned();
    }

    public static SortCategory getSortCategory() {
        return sortCategory;
    }

    public static int getSortOrder() {
        return sortOrder;
    }

    public static void setSortCategory(SortCategory newSortCategory) {
        sortCategory = newSortCategory;
    }

    public static void setSortOrder(int newSortOrder) {
        sortOrder = newSortOrder;
    }

    public String uiRepresentation() {
        return this.title + " " + this.issueId.uiRepresentation();
    }

    //To modify based on format to be saved in json file
    @Override
    public String toString() {
        return this.title.toString();
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
     * Checks if input is a valid urgency sort key.
     *
     * 0 for lowest urgency order and 1 for highest urgency order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidUrgencySortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is a valid issue id sort key.
     *
     * 0 for ascending and 1 for descending order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidIssueIdSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if both issues have the same title.
     * This defines a weaker notion of equality between two issues.
     */
    public boolean isSameIssue(Issue otherIssue) {
        if (otherIssue == this) {
            return true;
        }

        return otherIssue != null
                && otherIssue.getTitle().equals(getTitle());
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
        return otherIssue.getTitle().equals(getTitle())
                && otherIssue.getProject().equals(getProject())
                && otherIssue.getDeadline().equals(getDeadline())
                && otherIssue.getStatus().equals(getStatus())
                && otherIssue.getUrgency().equals(getUrgency())
                && otherIssue.getIssueId().equals(getIssueId())
                && otherIssue.getPin().equals(getPin());
    }

    private Pin getPin() {
        return this.pin;
    }

    @Override
    public int getID() {
        return this.issueId.getIdInt();
    }

}

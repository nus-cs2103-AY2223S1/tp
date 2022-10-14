package seedu.address.model.issue;



import static java.util.Objects.requireNonNull;

/**
 * Represents an Issue's id.
 */
public class IssueId {

    /**
     * Represents an empty issue id.
     */
    public static class EmptyIssueId extends IssueId {
        public static final IssueId EMPTY_ISSUE_ID = new EmptyIssueId();

        private EmptyIssueId() {
            super(0);
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Issue ID must be a valid integer";

    private int issueId;

    /**
     * Construct's an issue's id.
     *
     * @param id A valid issue id.
     */
    public IssueId(int id) {
        requireNonNull(id);
        this.issueId = id;
    }

    public int getIdInt() {
        return this.issueId;
    }

    /**
     * Checks if the issue ID string is valid.
     * @param issueId
     * @return Boolean value denoting whether the id string is valid.
     */
    public static boolean isValidIssueId(String issueId) {
        try {
            Integer.parseInt(issueId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public String uiRepresentation() {
        return "(#" + toString() + ")";
    }
    @Override
    public String toString() {
        return String.valueOf(this.issueId);
    }

}

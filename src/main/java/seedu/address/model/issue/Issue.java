package seedu.address.model.issue;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.interfaces.ComparableByName;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.project.Project;

import java.util.function.Function;

/**
 * Represents an Issue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Issue implements ComparableByName<Issue>, HasIntegerIdentifier<Issue> {

    // Components of an issue
    private Description description;
    private Deadline deadline;
    private Priority priority;
    private Status status;
    private Project project;
    private IssueId issueId;

    /**
     * Description field must be present and not null, but all other fields are optional.
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

    public IssueId getIssueId() {
        return this.issueId;
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

    /**
     * Returns true if both issues have the same description.
     * This defines a weaker notion of equality between two issues.
     */
    @Override
    public boolean hasSameName(Issue otherIssue) {
        if (otherIssue == this) {
            return true;
        }

        return otherIssue != null
                && otherIssue.getDescription().equals(getDescription());
    }

    public Status getStatus() {
        return this.status;
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


    /**
     * Create a function that creates a Client when given a ClientId object
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     */
    public static Function<IssueId, Issue> issueWithoutIdSupplier(Name name, ClientPhone phone, ClientEmail email) {
        return (id) -> new Client(name, phone, email, id);
    }
}

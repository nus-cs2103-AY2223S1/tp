package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Priority;
import seedu.address.model.issue.Status;
import seedu.address.model.list.UniqueEntityList;
import seedu.address.model.project.Project;
import seedu.address.model.tag.exceptions.IllegalValueException;

/**
 * Jackson-friendly version of {@link Issue}.
 */
class JsonAdaptedIssue {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Issue's %s field is missing!";

    private final String description;
    private final String priority;
    private final String deadline;
    private final String status;
    private final String issueId;

    private final String project;

    /**
     * Constructs a {@code JsonAdaptedIssue} with the given issue details.
     */
    @JsonCreator
    public JsonAdaptedIssue(@JsonProperty("description") String description,
                            @JsonProperty("priority") String priority,
                             @JsonProperty("deadline") String deadline,
                            @JsonProperty("status") String status,
                            @JsonProperty("issueId") String issueId,
                            @JsonProperty("project") String project) {
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.project = project;
        this.issueId = issueId;
    }

    /**
     * Converts a given {@code Issue} into this class for Jackson use.
     */
    public JsonAdaptedIssue(Issue source) {
        description = source.getDescription().toString();
        priority = source.getPriority().toString();
        deadline = source.getDeadline().toString();
        status = source.getStatus().toString();
        issueId = source.getIssueId().toString();
        project = source.getProject().getProjectId().toString();
    }

    /**
     * Converts this Jackson-friendly adapted issue object into the model's {@code Issue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted issue.
     */
    public Issue toModelType(AddressBook addressBook) throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        //        if (!Priority.isValidPriority(priority)) {
        //            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        //        }
        final Priority modelPriority = Priority.valueOf(priority);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(Boolean.valueOf(status));

        //        if (project == null) {
        //            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
        //            Project.class.getSimpleName()));
        //        }
        //
        final Project modelProject = HasIntegerIdentifier.getElementById(
                AddressBook.get().getProjectList(), Integer.parseInt(project));


        if (issueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IssueId.class.getSimpleName()));
        }
        if (!IssueId.isValidIssueId(issueId)) {
            throw new IllegalValueException(IssueId.MESSAGE_CONSTRAINTS);
        }
        final IssueId modelIssueId = new IssueId(Integer.parseInt(issueId));

        return new Issue(modelDescription, modelDeadline, modelPriority, modelStatus, modelProject, modelIssueId);
    }

}

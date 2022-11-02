package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.Pin;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.list.NotFoundException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;

/**
 * Jackson-friendly version of {@link Issue}.
 */
class JsonAdaptedIssue {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Issue's %s field is missing!";

    private final String title;
    private final String urgency;
    private final String deadline;
    private final String status;
    private final String issueId;
    private final String project;
    private final String pin;

    /**
     * Constructs a {@code JsonAdaptedIssue} with the given issue details.
     */
    @JsonCreator
    public JsonAdaptedIssue(@JsonProperty("title") String title,
                            @JsonProperty("urgency") String urgency,
                             @JsonProperty("deadline") String deadline,
                            @JsonProperty("status") String status,
                            @JsonProperty("issueId") String issueId,
                            @JsonProperty("project") String project,
                            @JsonProperty("pin") String pin) {
        this.title = title;
        this.urgency = urgency;
        this.deadline = deadline;
        this.status = status;
        this.project = project;
        this.pin = pin;
        this.issueId = issueId;
    }

    /**
     * Converts a given {@code Issue} into this class for Jackson use.
     */
    public JsonAdaptedIssue(Issue source) {
        title = source.getTitle().toString();
        urgency = source.getUrgency().toString();
        deadline = source.getDeadline().toString();
        status = source.getStatus().toString();
        issueId = source.getIssueId().toString();
        project = source.getProject().getProjectId().toString();
        pin = String.valueOf(source.isPinned());
    }

    /**
     * Converts this Jackson-friendly adapted issue object into the model's {@code Issue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted issue.
     */
    public Issue toModelType(AddressBook addressBook) throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (urgency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Urgency.class.getSimpleName()));
        }

        if (!Urgency.isValidUrgencyString(urgency)) {
            throw new IllegalValueException(Urgency.MESSAGE_CONSTRAINTS);
        }

        final Urgency modelUrgency = Urgency.valueOf(urgency);

        Deadline modelDeadline;

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }

        if (deadline.isEmpty()) {
            modelDeadline = Deadline.EmptyDeadline.EMPTY_DEADLINE;
        } else {
            if (!Deadline.isValidDeadline(deadline)) {
                throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
            }
            modelDeadline = new Deadline(deadline);
        }

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(Boolean.valueOf(status));

        if (project == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
            Project.class.getSimpleName()));
        }
        final Project modelProject;
        try {
            modelProject = HasIntegerIdentifier.getElementById(
                    addressBook.getProjectList(), Integer.parseInt(project));
        } catch (NotFoundException e) {
            throw new IllegalValueException(ProjectId.MESSAGE_CONSTRAINTS);
        }

        Pin modelPin;
        if (pin == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Pin.class.getSimpleName()));
        }
        if (!Pin.isValidPin(pin)) {
            throw new IllegalValueException(Pin.MESSAGE_CONSTRAINTS);
        }
        modelPin = new Pin(Boolean.parseBoolean(pin));

        if (issueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IssueId.class.getSimpleName()));
        }
        if (!IssueId.isValidIssueId(issueId)) {
            throw new IllegalValueException(IssueId.MESSAGE_CONSTRAINTS);
        }
        final IssueId modelIssueId = new IssueId(Integer.parseInt(issueId));

        assert modelIssueId.getIdInt() >= 0 : "Issue ID should be positive";

        return new Issue(modelTitle, modelDeadline, modelUrgency, modelStatus,
                modelProject, modelIssueId, modelPin);
    }

}

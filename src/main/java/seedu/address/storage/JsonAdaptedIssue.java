package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.Pin;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;

/**
 * Jackson-friendly version of {@link Issue}.
 */
class JsonAdaptedIssue {

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

        final Title modelTitle = StorageUtil.readTitleFromStorage(title);
        final Urgency modelUrgency = StorageUtil.readUrgencyFromStorage(urgency);
        final Deadline modelDeadline = StorageUtil.readDeadlineFromStorage(deadline, Issue.class.getSimpleName());
        final Status modelStatus = StorageUtil.readStatusFromStorage(status);
        final Project modelProject = StorageUtil.readProjectFromStorage(project, addressBook);
        final Pin modelPin = StorageUtil.readPinFromStorage(pin, Issue.class.getSimpleName());
        final IssueId modelIssueId = StorageUtil.readIssueIdFromStorage(issueId);
        return new Issue(modelTitle, modelDeadline, modelUrgency, modelStatus,
                modelProject, modelIssueId, modelPin);
    }

}

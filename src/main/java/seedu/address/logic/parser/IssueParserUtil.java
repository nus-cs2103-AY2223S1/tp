package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Priority;
import seedu.address.model.issue.Status;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.UniqueProjectList;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class IssueParserUtil {

    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim().toUpperCase();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return Priority.valueOf(trimmedPriority);
    }

    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(Boolean.getBoolean(status));
    }

    public static Project parseProject(String projectId) throws ParseException {
        requireNonNull(projectId);
        String trimmedId = projectId.trim();
        int trimmedIdInt = Integer.valueOf(trimmedId);
        if (!ProjectId.isValidProjectId(trimmedId)) {
            throw new ParseException(ProjectId.MESSAGE_CONSTRAINTS);
        }
        // TODO: to retrieve project through a project getter (modify based on getter)
        if (UniqueProjectList.getProject(trimmedIdInt) == null) {
            throw new ParseException("No project with this project Id");
        }
        return UniqueProjectList.getProject(trimmedIdInt);
    }
}

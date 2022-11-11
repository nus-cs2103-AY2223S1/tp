package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Project;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private final String title;
    private final String isCompleted;
    private final String deadline;
    private final String project;
    private final List<JsonAdaptedContact> assigned = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title,
                           @JsonProperty("isCompleted") String isCompleted,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("project") String project,
                           @JsonProperty("assigned") List<JsonAdaptedContact> assigned) {
        this.title = title;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
        this.project = project;
        if (assigned != null) {
            this.assigned.addAll(assigned);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle().toString();
        isCompleted = String.valueOf(source.getCompleted());
        deadline = source.getDeadline().toString();
        project = source.getProject().toString();

        assigned.addAll(source.getAssignedContacts().stream()
            .map(JsonAdaptedContact::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted teammate object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted teammate.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Contact> assignedContacts = new ArrayList<>();
        for (JsonAdaptedContact contact : assigned) {
            assignedContacts.add(contact.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (isCompleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Done"));
        }

        if (!isCompleted.equalsIgnoreCase("false") && !isCompleted.equalsIgnoreCase("true")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Done"));
        }

        final boolean modelIsCompleted = Boolean.parseBoolean(isCompleted);

        final Deadline modelDeadline;
        if (deadline.equals(Deadline.UNSPECIFIED_DEADLINE_IDENTIFIER)) {
            modelDeadline = Deadline.UNSPECIFIED;
        } else {
            modelDeadline = Deadline.of(TaskParserUtil.convertStringToLocalDate(deadline));
        }

        final Project modelProject;
        if (project.equals(Project.UNSPECIFIED_PROJECT_IDENTIFIER)) {
            modelProject = Project.UNSPECIFIED;
        } else {
            modelProject = new Project(project);
        }

        final Set<Contact> modelContacts = new HashSet<>(assignedContacts);

        return new Task(modelTitle, modelIsCompleted, modelDeadline, modelProject, modelContacts);
    }

}

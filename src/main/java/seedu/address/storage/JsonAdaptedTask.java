package seedu.address.storage;

import java.time.LocalDate;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final String name;
    private final String categoryName;
    private final String description;
    private final String priority;
    private final String deadline;
    private final String email;
    private final String isDone;


    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name,
                           @JsonProperty("categoryName") String categoryName,
                           @JsonProperty("description") String description,
                           @JsonProperty("priority") String priority,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("email") String email,
                           @JsonProperty("isDone") String isDone) {
        this.name = name;
        this.categoryName = categoryName;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.email = email;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().getTaskName();
        categoryName = source.getCategory().getTaskCategoryType().toString();
        description = source.getDescription().toString();
        priority = source.getPriority().getPriority().toString();
        deadline = source.getDeadline().toString();
        email = source.getPerson() == null ? null : source.getEmail().toString();
        isDone = Task.covertIsDoneFromBooleanToString(source.isDone());
    }


    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType(ObservableList<Person> personList) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(name)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(name);

        if (categoryName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskCategory.class.getSimpleName()));
        }
        if (!TaskCategory.isValidTaskCategoryName(categoryName)) {
            throw new IllegalValueException(TaskCategory.MESSAGE_CONSTRAINTS);
        }
        final TaskCategory modelCategory = new TaskCategory(TaskCategoryType.getFromString(categoryName).get());

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidTaskDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidTaskPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(PriorityEnum.getFromString(priority).get());

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidTaskDeadline(deadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelDeadline = new TaskDeadline(LocalDate.parse(deadline));

        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Boolean.class.getSimpleName()));
        }
        if (!Task.isValidIsDone(isDone)) {
            throw new IllegalValueException(Task.IS_DONE_MESSAGE_CONSTRAINT);
        }
        final Boolean modelIsDone = Task.covertIsDoneFromStringToBoolean(isDone);

        Person modelPerson = null;
        if (email != null) {
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            final Email modelEmail = new Email(email);

            for (Person person : personList) {
                if (person.emailMatches(modelEmail)) {
                    modelPerson = person;
                    break;
                }
            }
        }

        return new Task(modelName, modelDescription, modelPriority, modelCategory,
                modelDeadline, modelPerson, modelIsDone);
    }
}

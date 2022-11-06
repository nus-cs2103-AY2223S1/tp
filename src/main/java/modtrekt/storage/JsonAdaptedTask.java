package modtrekt.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import modtrekt.commons.exceptions.IllegalValueException;
import modtrekt.logic.parser.converters.PriorityConverter;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final String modCode;
    private final String dueDate;
    private final String priority;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String name, @JsonProperty("module code") String modCode,
                           @JsonProperty("dueDate") String dueDate,
                           @JsonProperty("priority") String priority,
                           @JsonProperty("isDone") boolean isDone) {
        this.description = name;
        this.modCode = modCode;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task task) {
        this.description = task.getDescription().toString();
        this.modCode = task.getModule().toString();
        if (task instanceof Deadline) {
            dueDate = ((Deadline) task).getDueDate().toString();
        } else {
            dueDate = null;
        }
        this.priority = task.getPriority().toString();
        this.isDone = task.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (modCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModCode.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);
        final ModCode modCode = new ModCode(this.modCode);

        Task.Priority modelPriority = new PriorityConverter().convert(priority);
        if (dueDate == null) {
            return new Task(modelDescription, modCode, isDone, modelPriority);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDate dueDateObj = LocalDate.parse(dueDate, formatter);
        return new Deadline(modelDescription, modCode, dueDateObj, isDone, modelPriority);
    }
}

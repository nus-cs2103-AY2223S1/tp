package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;

/**
 * This class represents a Jackson friendly version of the Task.
 */
public class JsonAdaptedTask {
    public static final String MISSING_TASK_DESCRIPTION = "Task description is not present";
    private final String description;
    private final String moduleCode;
    private final String status;
    private final String priority;
    private final String deadline;

    /**
     * Builds a {@code JsonAdaptedTask} with the description and module code.
     *
     * @param description The description of the task.
     * @param moduleCode The module code of the task.
     */
    public JsonAdaptedTask(@JsonProperty("description") String description,
            @JsonProperty("modCode") String moduleCode, @JsonProperty("status") String status,
            @JsonProperty("priority") String priority, @JsonProperty("deadline") String deadline) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
    }

    /**
     * Converts an existing task into {@code JsonAdaptedTask} object
     *
     * @param task The task object being converted.
     */
    public JsonAdaptedTask(Task task) {
        description = task.getDescription().description;
        moduleCode = task.getModule().getModuleCode().moduleCode;
        status = task.getStatus().status;
        priority = task.getPriorityTag() == null ? null : task.getPriorityTag().status;
        deadline = task.getDeadlineTag() == null ? null : task.getDeadlineTag().toString();
    }

    /**
     * Converts this Jackson-friendly task object into a Task object for the model.
     *
     * @return The Task object which has been created.
     * @throws IllegalValueException if the task has invalid fields.
     */
    public Task toModelType() throws IllegalValueException {
        if (description == null || moduleCode == null || status == null) {
            throw new IllegalValueException(MISSING_TASK_DESCRIPTION);
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.DESCRIPTION_CONSTRAINTS);
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }
        if (!TaskStatus.isValidStatus(status)) {
            throw new IllegalValueException(TaskStatus.STATUS_CONSTRAINTS);
        }
        if (!PriorityTag.isValidTag(priority)) {
            throw new IllegalValueException(PriorityTag.PRIORITY_TAG_CONSTRAINTS);
        }
        final LocalDate date;
        try {
            if (deadline != null) {
                //@@author dlimyy-reused
                //Reused from https://stackoverflow.com/questions/32823368/
                //with minor modifications.
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);
                //@@author
                date = LocalDate.parse(deadline, dtf);
            } else {
                date = null;
            }
        } catch (DateTimeParseException dtp) {
            throw new IllegalValueException(DeadlineTag.DEADLINE_TAG_CONSTRAINTS);
        }
        final DeadlineTag deadlineTag = date == null ? null : new DeadlineTag(date);
        final TaskDescription taskDescription = new TaskDescription(description);
        final ModuleCode modCode = new ModuleCode(moduleCode);
        final Module module = new Module(modCode);
        final TaskStatus taskStatus = TaskStatus.of(status);
        final PriorityTag priorityTag = priority == null ? null : new PriorityTag(priority);
        return new Task(module, taskDescription, taskStatus, priorityTag, deadlineTag);
    }

}

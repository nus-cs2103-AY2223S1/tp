package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * JsonAdaptedLinkedTask represents a Jackson friendly version of a linked task.
 */
public class JsonAdaptedLinkedTask {

    public static final String LINKED_TASK_MISSING =
            "The description and module code for the linked task cannot be missing.";
    private final String description;
    private final String moduleCode;

    /**
     * Builds a {@code JsonAdaptedLinkedTask} with the description of the task
     * and the module code of the module.
     *
     * @param description The description of the task.
     * @param moduleCode The module code of the module.
     */
    public JsonAdaptedLinkedTask(@JsonProperty("description") String description,
                                 @JsonProperty("moduleCode") String moduleCode) {
        this.description = description;
        this.moduleCode = moduleCode;
    }

    /**
     * Converts an existing {@code Task} object into a {@code JsonAdaptedLinkedTask} object.
     *
     * @param task The task that is being converted.
     */
    public JsonAdaptedLinkedTask(Task task) {
        description = task.getDescription().description;
        moduleCode = task.getModule().getModuleCode().moduleCode;
    }

    /**
     * Converts the Jackson-friendly linked Task to a Task object for the model.
     *
     * @return Task Object which is created.
     * @throws IllegalValueException if the linked task has invalid fields.
     */
    public Task toModelType() throws IllegalValueException {
        if (description == null || moduleCode == null) {
            throw new IllegalValueException(LINKED_TASK_MISSING);
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.DESCRIPTION_CONSTRAINTS);
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }
        final TaskDescription taskDescription = new TaskDescription(description);
        final ModuleCode modCode = new ModuleCode(moduleCode);
        final Module module = new Module(modCode);
        return new Task(module, taskDescription);
    }


}

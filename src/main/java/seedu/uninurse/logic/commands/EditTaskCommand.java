package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Edits the details of an existing Task for a patient.
 */
public class EditTaskCommand extends EditGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specified task associated with a patient "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PATIENT INDEX (must be a positive integer) TASK INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_DESCRIPTION + "TASK]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + " 2 "
            + PREFIX_TASK_DESCRIPTION + "change bandage";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s -> %2$s";
    public static final String MESSAGE_NOT_EDITED = "Task to edit must be provided.";

    public static final CommandType EDIT_TASK_COMMAND_TYPE = CommandType.TASK;

    private final Index patientIndex;
    private final Index taskIndex;
    private final Task updatedTask;

    /**
     * @param patientIndex of the patient in the filtered patient list to edit.
     * @param taskIndex of the task to be edited.
     * @param updatedTask details to edit for the task.
     */
    public EditTaskCommand(Index patientIndex, Index taskIndex, Task updatedTask) {
        requireNonNull(patientIndex);
        requireNonNull(taskIndex);
        requireNonNull(updatedTask);

        this.patientIndex = patientIndex;
        this.taskIndex = taskIndex;
        this.updatedTask = updatedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());

        if (taskIndex.getZeroBased() >= patientToEdit.getTasks().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

        Task initialTask = patientToEdit.getTasks().get(taskIndex.getZeroBased());
        TaskList updatedTaskList = patientToEdit.getTasks().edit(taskIndex.getZeroBased(), updatedTask);

        Patient editedPatient = new Patient(patientToEdit, updatedTaskList);

        model.setPerson(patientToEdit, editedPatient);
        model.updateFilteredPersonList(patient -> patient.equals(editedPatient));
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, initialTask, updatedTask),
                EDIT_TASK_COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return patientIndex.equals(e.patientIndex)
                && taskIndex.equals(e.taskIndex)
                && updatedTask.equals(e.updatedTask);
    }
}

package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TASK_INDEX;
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
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_TASK_INDEX
            + ": Edit a task of a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_TASK_INDEX + " TASK_INDEX "
            + PREFIX_TASK_DESCRIPTION + "<TASK_DESCRIPTION> | <DATE TIME> | <INTERVAL TIME_PERIOD>\n"
            + "Examples:\n" + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1 "
            + PREFIX_TASK_DESCRIPTION + "| 22-4-22 1345\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1 "
            + PREFIX_TASK_DESCRIPTION + "| | 3 weeks\n";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited task %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
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
        TaskList initialTaskList = patientToEdit.getTasks();

        if (taskIndex.getZeroBased() >= initialTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

        if (patientToEdit.getTasks().hasTask(updatedTask)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK);
        }

        Task initialTask = initialTaskList.get(taskIndex.getZeroBased());
        TaskList updatedTaskList = initialTaskList.edit(taskIndex.getZeroBased(), updatedTask);

        Patient editedPatient = new Patient(patientToEdit, updatedTaskList);

        model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS,
                taskIndex.getOneBased(), editedPatient.getName(), initialTask, updatedTask), EDIT_TASK_COMMAND_TYPE);
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

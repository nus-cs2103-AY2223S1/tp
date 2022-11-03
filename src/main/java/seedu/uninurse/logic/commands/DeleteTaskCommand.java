package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Deletes a task from a person identified using its displayed index from the person list.
 */
public class DeleteTaskCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number in the task list of the person "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + "TASK_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task %1$d from %2$s: %3$s";

    public static final CommandType DELETE_TASK_COMMAND_TYPE = CommandType.TASK;

    private final Index patientIndex;
    private final Index taskIndex;

    /**
     * Creates an DeleteTaskCommand to delete a {@code Task} from the specified person.
     *
     * @param patientIndex index of the person in the filtered person list to delete the task
     * @param taskIndex    index of the task in the person's task list
     */
    public DeleteTaskCommand(Index patientIndex, Index taskIndex) {
        requireAllNonNull(patientIndex, taskIndex);

        this.patientIndex = patientIndex;
        this.taskIndex = taskIndex;
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

        Task deletedTask = initialTaskList.get(taskIndex.getZeroBased());
        TaskList updatedTaskList = initialTaskList.delete(taskIndex.getZeroBased());

        Patient editedPerson = new Patient(patientToEdit, updatedTaskList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPerson);
        model.setPatientOfInterest(editedPerson);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskIndex.getOneBased(),
                editedPerson.getName(), deletedTask), DELETE_TASK_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return patientIndex.equals(e.patientIndex) && taskIndex.equals((e.taskIndex));
    }
}

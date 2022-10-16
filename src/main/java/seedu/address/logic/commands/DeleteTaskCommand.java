package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

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

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task from %1$s: %2$s";

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

        TaskList updatedTaskList = patientToEdit.getTasks().delete(taskIndex.getZeroBased());
        Task deletedTask = initialTaskList.get(taskIndex.getZeroBased());

        Patient editedPerson = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), updatedTaskList, patientToEdit.getTags());

        model.setPerson(patientToEdit, editedPerson);
        model.updateFilteredPersonList(patient -> patient.equals(editedPerson));
        model.setPatientOfInterest(editedPerson);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, editedPerson.getName(), deletedTask),
                CommandType.TASK);
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

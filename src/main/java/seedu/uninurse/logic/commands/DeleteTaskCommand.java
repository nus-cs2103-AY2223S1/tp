package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TASK_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Deletes a task from a person identified using its displayed index from the person list.
 */
public class DeleteTaskCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_TASK_INDEX
            + ": Deletes a task from a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_TASK_INDEX + " TASK_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted task %1$d from %2$s: %3$s";
    public static final CommandType COMMAND_TYPE = CommandType.TASK;

    private final Index patientIndex;
    private final Index taskIndex;

    /**
     * Creates an DeleteTaskCommand to delete a Task from the specified person.
     *
     * @param patientIndex index of the person in the filtered person list to delete the task.
     * @param taskIndex index of the task in the person's task list.
     */
    public DeleteTaskCommand(Index patientIndex, Index taskIndex) {
        requireAllNonNull(patientIndex, taskIndex);

        this.patientIndex = patientIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(patientIndex.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        TaskList initialTaskList = patientToEdit.getTasks();

        if (taskIndex.getZeroBased() >= initialTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

        Task deletedTask = initialTaskList.get(taskIndex.getZeroBased());
        TaskList updatedTaskList = initialTaskList.delete(taskIndex.getZeroBased());

        Patient editedPerson = new Patient(patientToEdit, updatedTaskList);

        PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPerson);
        model.setPatientOfInterest(editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskIndex.getOneBased(),
                editedPerson.getName(), deletedTask), COMMAND_TYPE, personListTracker);
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
        DeleteTaskCommand o = (DeleteTaskCommand) other;
        return patientIndex.equals(o.patientIndex) && taskIndex.equals((o.taskIndex));
    }
}

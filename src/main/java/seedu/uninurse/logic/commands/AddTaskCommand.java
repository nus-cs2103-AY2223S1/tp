package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

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
import seedu.uninurse.model.task.exceptions.DuplicateTaskException;

/**
 * Add a task to an existing person in the patient list.
 */
public class AddTaskCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + ": Adds a task to a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION | <DATE TIME> | <INTERVAL TIME_PERIOD>\n"
            + "Examples:\n" + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing | 22-4-22 1345\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing | 22-4-22 1345 | 3 weeks\n";
    public static final String MESSAGE_SUCCESS = "New task added to %1$s: %2$s";
    public static final CommandType COMMAND_TYPE = CommandType.TASK;

    private final Index index;
    private final Task task;

    /**
     * Creates an AddTaskCommand to add a Task to the specified person.
     *
     * @param index of the person in the filtered person list to add the task.
     * @param task of the person to be added to.
     */
    public AddTaskCommand(Index index, Task task) {
        requireAllNonNull(index, task);

        this.index = index;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(index.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        try {
            TaskList updatedTaskList = patientToEdit.getTasks().add(task);
            Patient editedPatient = new Patient(patientToEdit, updatedTaskList);
            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient.getName().toString(), task),
                    COMMAND_TYPE, personListTracker);
        } catch (DuplicateTaskException dte) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        // state check
        AddTaskCommand o = (AddTaskCommand) other;
        return index.equals(o.index) && task.equals((o.task));
    }
}

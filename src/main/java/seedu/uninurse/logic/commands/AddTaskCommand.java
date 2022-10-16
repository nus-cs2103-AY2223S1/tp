package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
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
 * Add a task to an existing person in the patient list.
 */
public class AddTaskCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task to the person identified "
            + "by the index number used in the last patient listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASK_DESCRIPTION + " [TASK_DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing on left arm";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added to %1$s: %2$s";

    private final Index index;
    private final Task task;

    /**
     * Creates an AddTaskCommand to add a {@code Task} to the specified person.
     * @param index index of the person in the filtered person list to add the task
     * @param task task of the person to be added to
     */
    public AddTaskCommand(Index index, Task task) {
        requireAllNonNull(index, task);

        this.index = index;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient personToEdit = lastShownList.get(index.getZeroBased());
        TaskList updatedTaskList = personToEdit.getTasks().add(task);
        Patient editedPerson = new Patient(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), updatedTaskList, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, editedPerson.getName().toString(), task));
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
        AddTaskCommand e = (AddTaskCommand) other;
        return index.equals(e.index) && task.equals((e.task));
    }
}

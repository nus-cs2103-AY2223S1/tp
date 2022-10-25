package swift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.model.Model;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Assigns a task to a contact identified using their displayed index from the
 * address book.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the task to the contact identified by the index numbers used in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX (must be a positive integer) TASK_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_ASSIGN_SUCCESS = "Task %1$s assigned to contact %2$s!";

    private final Index contactIndex;
    private final Index taskIndex;

    /**
     * Creates an AssignCommand to add the specified {@code Task} to the specified
     * {@code Person}
     *
     * @param contactIndex Index of the contact to assign the task to.
     * @param taskIndex    Index of the task to be assigned.
     */
    public AssignCommand(Index contactIndex, Index taskIndex) {
        this.contactIndex = contactIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (contactIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person selectedPerson = lastShownPersonList.get(contactIndex.getZeroBased());
        Task selectedTask = lastShownTaskList.get(taskIndex.getZeroBased());

        model.addBridge(selectedPerson, selectedTask);

        return new CommandResult(String.format(MESSAGE_ASSIGN_SUCCESS, selectedTask, selectedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectContactCommand // instanceof handles nulls
                        && contactIndex.equals(((AssignCommand) other).contactIndex)
                        && taskIndex.equals(((AssignCommand) other).taskIndex)); // state check
    }
}

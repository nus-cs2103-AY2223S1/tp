package seedu.address.logic.commands.task;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;

/**
 * Assigns person(s) to a task.
 */
public class AssignTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "assign";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Assigns person(s) to a task.\n"
            + "Parameters: "
            + "TASK_INDEX (must be a positive integer) "
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "1 "
            + PREFIX_CONTACT + "2 "
            + PREFIX_CONTACT + "3 ";

    public static final String MESSAGE_SUCCESS = "Assigned persons to task: %1$s";

    public static final String MESSAGE_RESET_SUCCESS = "Removed all assigned persons from task: %1$s";

    private final Index taskIndex;
    private final Set<Index> personIndexes;

    /**
     * Creates AssignTaskCommand to the specific task and persons.
     * @param taskIndex of the task to be updated.
     * @param personsIndexes of all persons to be assigned to task.
     */
    public AssignTaskCommand(Index taskIndex, Set<Index> personsIndexes) {
        requireNonNull(taskIndex);
        requireNonNull(personsIndexes);
        this.taskIndex = taskIndex;
        this.personIndexes = personsIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Task> lastShownTaskList = model.getFilteredTaskList();
        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToAssignTo = lastShownTaskList.get(taskIndex.getZeroBased());

        List<Person> lastShownPersonsList = model.getFilteredPersonList();
        Set<Person> assignedPersons = new HashSet<>();
        for (Index personIndex : personIndexes) {
            if (personIndex.getZeroBased() >= lastShownPersonsList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToAssign = lastShownPersonsList.get(personIndex.getZeroBased());
            assignedPersons.add(personToAssign);
        }

        Task editedTask = new Task(taskToAssignTo.getTitle(), taskToAssignTo.getMarkStatus(), assignedPersons);

        model.setTask(taskToAssignTo, editedTask);

        if (personIndexes.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_RESET_SUCCESS, taskIndex.getOneBased()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignTaskCommand)) {
            return false;
        }

        // state check
        AssignTaskCommand e = (AssignTaskCommand) other;
        return taskIndex.equals(e.taskIndex) && personIndexes.equals(e.personIndexes);
    }

}

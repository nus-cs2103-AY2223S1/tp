package swift.logic.commands;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.model.Model;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static swift.model.Model.*;

/**
 * Command to display all tasks using its specified index from address book.
 */
public class SelectTaskCommand extends Command {
    public static final String COMMAND_WORD = "select_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_TASK_SUCCESS = "Task Selected!";

    private final Index targetIndex;

    public SelectTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        // Display only selected task
        Task selectedTask = lastShownTaskList.get(targetIndex.getZeroBased());
        model.updateFilteredTaskList(task -> task.equals(selectedTask));

        model.updateFilteredBridgeList(PREDICATE_SHOW_ALL_BRIDGE);
        model.updateFilteredBridgeList(bridge -> bridge.getTaskId().equals(selectedTask.getId()));

        List<PersonTaskBridge> bridgeList = model.getFilteredBridgeList();

        Predicate<Person> isAssociatedContact = contact -> bridgeList.stream()
                .filter((bridge) -> bridge.getTaskId().equals(contact.getId()))
                .collect(Collectors.toList())
                .size() != 0;

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PEOPLE);
        model.updateFilteredPersonList(isAssociatedContact);

        return new CommandResult(Messages.MESSAGE_PERSONS_SELECTED_OVERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectTaskCommand // instanceof handles nulls
                && targetIndex.equals(((SelectTaskCommand) other).targetIndex)); // state check
    }
}

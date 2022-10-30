// @@author jasonchristopher21
package seedu.address.logic.commands.attributes;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.exceptions.AttributeException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskOutOfBoundException;

/**
 * Adds a task attribute to the address book.
 */
public class RemoveTaskAttributeCommand extends RemoveAttributeCommand {

    public static final String MESSAGE_SUCCESS = "Field removed successfully: %s";

    private final Index taskIndex; // change this to UUID later

    /**
     * Constructs an EditTaskAttributeCommand instance.
     *
     * @param taskIndex index of the task.
     * @param attributeName the name of the attribute to be added.
     */
    public RemoveTaskAttributeCommand(Index taskIndex, String attributeName) {
        super(attributeName);
        requireNonNull(taskIndex);
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Task task = model.getFromFilteredTasks(taskIndex);
            task.removeAttribute(attributeName);
        } catch (TaskOutOfBoundException | AttributeException ae) {
            throw new CommandException(ae.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, attributeName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (super.equals(other)
                && (other instanceof RemoveTaskAttributeCommand
                    && taskIndex.equals(((RemoveTaskAttributeCommand) other).taskIndex)));
    }
}

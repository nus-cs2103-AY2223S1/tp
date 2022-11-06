package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.NEW_COMMAND;

import seedu.foodrem.commons.exceptions.ItemStorageFullException;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Creates a new an item in FoodRem.
 */
public class NewCommand extends Command {
    private final Item newItem;

    /**
     * Creates a NewCommand to add the specified {@code Item}
     */
    public NewCommand(Item item) {
        requireNonNull(item);
        newItem = item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(newItem)) {
            throw new CommandException("This item already exists in FoodRem");
        }

        try {
            model.addItem(newItem);
        } catch (ItemStorageFullException sfe) {
            throw new CommandException(sfe.getMessage());
        }

        return CommandResult.from(new ItemWithMessage(newItem, "New item added as follows:"));
    }

    /**
     * Returns a string representing how to use the command.
     *
     * @return a string representing how to use the command.
     */
    public static String getUsage() {
        return NEW_COMMAND.getUsage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NewCommand
                && newItem.equals(((NewCommand) other).newItem));
    }
}

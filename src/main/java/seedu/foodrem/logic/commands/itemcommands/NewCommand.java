package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.NEW_COMMAND;

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

    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(newItem)) {
            throw new CommandException("This item already exists in FoodRem");
        }

        model.addItem(newItem);
        return CommandResult.from(new ItemWithMessage(newItem, "New item added as follows:"));
    }

    public static String getUsage() {
        return NEW_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewCommand // instanceof handles nulls
                && newItem.equals(((NewCommand) other).newItem));
    }
}

package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandType.VIEW_COMMAND;

import java.util.List;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;

/**
 * Displays information about a specified item.
 */
public class ViewCommand extends Command {
    private final Index index;

    /**
     * Constructor for ViewCommand.
     *
     * @param index of the item to display the information of.
     */
    public ViewCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Item> lastShownList = model.getFilteredItemList();
        if (index.getZeroBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
        }
        Item itemToDisplayInformation = lastShownList.get(index.getZeroBased());

        return new CommandResult(itemToDisplayInformation.toString());
    }

    public static String getUsage() {
        return VIEW_COMMAND.getUsage();
    }
}

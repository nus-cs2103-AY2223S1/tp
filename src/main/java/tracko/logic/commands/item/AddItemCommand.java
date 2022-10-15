package tracko.logic.commands.item;

import static java.util.Objects.requireNonNull;

import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.items.Item;

/**
 * Adds an item to TrackO. The AddItemCommand is a single-level command in which the user initiates the item to be
 * added using the command word.
 */
public class AddItemCommand extends Command {
    public static final String COMMAND_WORD = "addi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Initiates an item to add to TrackO. \n"
            + "Parameters: "
            + CliSyntax.PREFIX_ITEM + "ITEM NAME "
            + CliSyntax.PREFIX_QUANTITY + "QUANTITY "
            + CliSyntax.PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_ITEM + "Paper "
            + CliSyntax.PREFIX_QUANTITY + "1000 "
            + CliSyntax.PREFIX_DESCRIPTION + "White printing paper ";

    public static final String MESSAGE_SUCCESS = "New item and quantity added:\n%1$s";

    private final Item toAdd;

    /**
     * Creates an AddItemCommand that is set to await further input from the user.
     * @param item The base item to be created, includes all item details
     */
    public AddItemCommand(Item item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        model.addItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && toAdd.equals(((AddItemCommand) other).toAdd));
    }
}

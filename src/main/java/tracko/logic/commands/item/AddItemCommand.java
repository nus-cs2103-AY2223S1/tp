package tracko.logic.commands.item;

import static java.util.Objects.requireNonNull;

import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.item.InventoryItem;

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
            + "[" + CliSyntax.PREFIX_TAG + "Tag]... "
            + CliSyntax.PREFIX_SELL_PRICE + "SELL PRICE "
            + CliSyntax.PREFIX_COST_PRICE + "COST PRICE \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_ITEM + "Paper "
            + CliSyntax.PREFIX_QUANTITY + "1000 "
            + CliSyntax.PREFIX_DESCRIPTION + "White printing paper "
            + CliSyntax.PREFIX_TAG + "Limited "
            + CliSyntax.PREFIX_TAG + "New "
            + CliSyntax.PREFIX_SELL_PRICE + "2.00 "
            + CliSyntax.PREFIX_COST_PRICE + "1.98 ";

    public static final String MESSAGE_SUCCESS = "New item and quantity added:\n%1$s";
    public static final String MESSAGE_ITEM_EXISTS = "This item already exists in the inventory list.";

    private final InventoryItem toAdd;

    /**
     * Creates an AddItemCommand that is set to await further input from the user.
     * @param inventoryItem The base item to be created, includes all item details
     */
    public AddItemCommand(InventoryItem inventoryItem) {
        requireNonNull(inventoryItem);
        toAdd = inventoryItem;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        if (model.hasItem(toAdd)) {
            throw new CommandException(MESSAGE_ITEM_EXISTS);
        }
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

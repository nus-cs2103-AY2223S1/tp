package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import javafx.util.Pair;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Order;

/**
 * Adds a person to the address book.
 */
public class AddOrderCommand extends Command {


    public static final String COMMAND_WORD = "addo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to TrackO. "
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_PHONE + "PHONE "
            + CliSyntax.PREFIX_EMAIL + "EMAIL "
            + CliSyntax.PREFIX_ADDRESS + "ADDRESS "
            + "i/ITEM_NAME "
            + "q/ITEM_QTY"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "John Doe "
            + CliSyntax.PREFIX_PHONE + "98765432 "
            + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
            + CliSyntax.PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + "i/keychain"
            + "q/3";


    public static final String MESSAGE_SUCCESS = "New order added: %1$s";

    public static final String MESSAGE_ADDED_ITEM = "New item and quantity added: %1$s";

    public static final String MESSAGE_USAGE_2 = "To add an item and its quantity to the order being created: "
            + "i/ITEM_NAME q/ITEM_QUANTITY, "
            + "enter 'done' or 'cancel' to finish or abort the command accordingly";

    private final Order toAdd;
    private Pair<String, Integer> lastItemQuantityPair;

    public AddOrderCommand(Order order) {
        super(true, false);
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (this.isCancelled()) {
            return new CommandResult("Add Order Command Cancelled");
        }

        if (this.isAwaitingInput() && lastItemQuantityPair != null) {
            return new CommandResult(String.format(MESSAGE_ADDED_ITEM, lastItemQuantityPair));
        } else if (this.isAwaitingInput()) {
            return new CommandResult(MESSAGE_USAGE_2);
        }

        requireNonNull(model);
        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    public void addToItemList(ItemQuantityPair itemQuantityPair) {
        this.toAdd.addToItemList(itemQuantityPair);
        this.lastItemQuantityPair = itemQuantityPair;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof tracko.logic.commands.order.AddOrderCommand // instanceof handles nulls
                && toAdd.equals(((tracko.logic.commands.order.AddOrderCommand) other).toAdd));
    }
}

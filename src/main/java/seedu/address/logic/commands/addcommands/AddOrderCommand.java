package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;

import java.util.Collections;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;

/**
 * Adds an order to the contact.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "add-o";

    public static final String USAGE_COMMON_PARAMETERS =
            PREFIX_ORDER_STATUS + "STATUS "
                    + PREFIX_ORDER_REQUESTS + "REQUEST "
                    + PREFIX_ORDER_PRICE + "PRICE "
                    + PREFIX_ORDER_PRICE_RANGE + "PRICE_RANGE "
                    + PREFIX_ORDER_DATE + "DATE "
                    + "[" + PREFIX_ORDER_ADDITIONAL_REQUESTS + "ADDITIONAL_REQUEST]...\n"

                    + "\nExample: " + COMMAND_WORD + " ";
    public static final String USAGE_COMMON_SAMPLE_PARAMETERS =
            PREFIX_ORDER_STATUS + "Pending "
                    + PREFIX_ORDER_REQUESTS + " (request prefixes and parameters) "
                    + PREFIX_ORDER_PRICE + "6.8 "
                    + PREFIX_ORDER_PRICE_RANGE + "5.4,8.0 "
                    + PREFIX_ORDER_DATE + "2022-09-30 "
                    + PREFIX_ORDER_ADDITIONAL_REQUESTS + "Free delivery "
                    + PREFIX_ORDER_ADDITIONAL_REQUESTS + "Vaccination certified";
    public static final String MESSAGE_USAGE_NEW_BUYER = COMMAND_WORD
            + ": Adds an order when adding a new buyer. "
            + "Parameters: "
            + USAGE_COMMON_PARAMETERS
            + USAGE_COMMON_SAMPLE_PARAMETERS;
    public static final String MESSAGE_USAGE_EXISTING_BUYER = COMMAND_WORD
            + ": Adds an order to an existing buyer. "
            + "Parameters: "
            + "INDEX "
            + USAGE_COMMON_PARAMETERS
            + "1 "
            + USAGE_COMMON_SAMPLE_PARAMETERS;

    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list";
    public static final String MESSAGE_SUCCESS = "Added Order: %1$s";

    private final Order toAdd;
    private final Index index;

    /**
     * Constructs a new AddOrderCommand object.
     * @param toAdd The order to be added
     * @param index The index of buyer that is associated with this order.
     */
    public AddOrderCommand(Order toAdd, Index index) {
        this.toAdd = toAdd;
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Object> lastShownList = model.getFilteredCurrList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Object o = lastShownList.get(index.getZeroBased());
        if (!(o instanceof Buyer)) {
            throw new CommandException(String.format(Messages.INVALID_BUYER, index.getOneBased()));
        }

        Buyer associatedBuyer = (Buyer) o;

        associatedBuyer.addOrders(Collections.singletonList(toAdd.getId()));
        toAdd.setBuyer(associatedBuyer);
        model.addOrder(toAdd);
        model.switchToBuyerList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

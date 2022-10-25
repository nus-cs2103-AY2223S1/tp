package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Checks a selected Buyer/Supplier/Order/Pet.
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = "Check the buyer/supplier of a specific order/pet with.\n"
            + "Example: check order 1";

    public static final String MESSAGE_SUCCESS = "Checking %1$s %2$s";

    public static final String CHECK_BUYER = "BUYER";

    public static final String CHECK_SUPPLIER = "SUPPLIER";
    public static final String CHECK_ORDER = "ORDER";
    public static final String CHECK_PET = "PET";

    private final String checkType;
    private final Index index;

    /**
     * Constructs a CheckCommand with fields specified.
     */
    public CheckCommand(String checkType, Index index) {
        this.checkType = checkType;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        switch (checkType) {
        case CHECK_BUYER:
            invalidIndexThrowException(model.getFilteredBuyerList());
            break;
        case CHECK_SUPPLIER:
            invalidIndexThrowException(model.getFilteredSupplierList());
            break;
        case CHECK_ORDER:
            invalidIndexThrowException(model.getFilteredOrderList());
            break;
        case CHECK_PET:
            invalidIndexThrowException(model.getFilteredPetList());
            break;
        default:
            //Do nothing
        }
        return CommandResult.createCheckCommandResult(String.format(MESSAGE_SUCCESS, checkType, index.getOneBased()),
                checkType, index);
    }

    private void invalidIndexThrowException(ObservableList<? extends Object> list) throws CommandException {
        if (index.getZeroBased() >= list.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}

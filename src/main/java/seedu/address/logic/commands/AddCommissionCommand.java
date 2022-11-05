package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Adds a commission to the selected customer.
 */
public class AddCommissionCommand extends Command {
    public static final String COMMAND_WORD = "addcom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a commission to ArtBuddy. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_FEE + "FEE "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_STATUS + "COMPLETION STATUS "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Tokyo Ghoul Kaneki "
            + PREFIX_FEE + "50 "
            + PREFIX_DEADLINE + "2022-10-10 "
            + PREFIX_STATUS + "False "
            + PREFIX_DESCRIPTION + "Unfamiliar, I will need to do up a reference board first. "
            + PREFIX_TAG + "digital "
            + PREFIX_TAG + "neon ";

    public static final String MESSAGE_SUCCESS = "New commission added: %1$s";
    public static final String MESSAGE_DUPLICATE_COMMISSION = "This commission already exists in art buddy";

    private final Commission.CommissionBuilder toAdd;

    /**
     * Creates an AddCommand to add the specified {@code commission}
     */
    public AddCommissionCommand(Commission.CommissionBuilder commission) {
        requireNonNull(commission);
        toAdd = commission;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedCustomer()) {
            throw new CommandException(Messages.MESSAGE_NO_ACTIVE_CUSTOMER);
        }
        Customer selectedCustomer = model.getSelectedCustomer().getValue();
        Commission newCommission = toAdd.build(selectedCustomer);
        if (selectedCustomer.hasCommission(newCommission)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMMISSION);
        }
        model.addCommission(selectedCustomer, newCommission);
        model.selectTab(GuiTab.COMMISSION);
        model.selectCommission(newCommission);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommissionCommand // instanceof handles nulls
                && toAdd.equals(((AddCommissionCommand) other).toAdd));
    }
}

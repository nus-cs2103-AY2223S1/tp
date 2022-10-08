package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;

/**
 * Adds a commission to the selected customer.
 */
public class AddCommissionCommand extends Command {
    public static final String COMMAND_WORD = "addcom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a commission to the address book. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_FEE + "FEE "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Tokyo Ghoul Kaneki "
            + PREFIX_FEE + "50 "
            + PREFIX_DEADLINE + "2022-10-10 "
            + PREFIX_DESCRIPTION + "Unfamiliar, I will need to do up a reference board first. "
            + PREFIX_TAG + "digital "
            + PREFIX_TAG + "neon ";

    public static final String MESSAGE_SUCCESS = "New commission added: %1$s";
    public static final String MESSAGE_DUPLICATE_COMMISSION = "This commission already exists in art buddy";

    private final Commission toAdd;

    /**
     * Creates an AddCommand to add the specified {@code commission}
     */
    public AddCommissionCommand(Commission commission) {
        requireNonNull(commission);
        toAdd = commission;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedCustomer()) {
            throw new CommandException(Messages.MESSAGE_NO_ACTIVE_CUSTOMER);
        }

        if (model.hasCommission(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMMISSION);
        }

        model.addCommission(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommissionCommand // instanceof handles nulls
                && toAdd.equals(((AddCommissionCommand) other).toAdd));
    }
}

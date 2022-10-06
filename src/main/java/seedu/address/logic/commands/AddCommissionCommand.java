package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;


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
            + PREFIX_DEADLINE + "johnd@example.com "
            + PREFIX_DESCRIPTION + "Unfamiliar, I will need to do up a reference board first. "
            + PREFIX_TAG + "digital "
            + PREFIX_TAG + "neon palette";

    public static final String MESSAGE_SUCCESS = "New commission added: %1$s";
    public static final String MESSAGE_DUPLICATE_commission = "This commission already exists in art buddy";

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

        if (model.hasCommission(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_commission);
        }

        model.addCommission(toAdd);
//        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommissionCommand // instanceof handles nulls
                && toAdd.equals(((AddCommissionCommand) other).toAdd));
    }
}

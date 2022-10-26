package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.bill.Bill;

/**
 * Finds and lists all appointments according to the prefix input by the user.
 */
public class FindBillCommand extends Command {
    public static final CommandWord COMMAND_WORD =
            new CommandWord("findbill", "fb");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bills whose names contains any of "
            + "the specified keywords or if the bill is paid or not and displays them as a list with index"
            + " numbers.\n"
            + "Parameters: prefix, KEYWORD/[MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice \n"
            + "Example: " + COMMAND_WORD + " p/ paid[/unpaid] \n";

    private final Predicate<Bill> predicate;

    public FindBillCommand(Predicate<Bill> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBillList(this.predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BILL_LISTED_OVERVIEW, model.getFilteredBillList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBillCommand // instanceof handles nulls
                && predicate.equals(((FindBillCommand) other).predicate)); // state check
    }
}

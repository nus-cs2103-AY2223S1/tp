package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.PaymentStatus;
import seedu.address.model.patient.Name;

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
            + "Example: " + COMMAND_WORD + " p/paid \n"
            + "Example: " + COMMAND_WORD + " n/Bob p/unpaid \n";

    private final Predicate<Bill> predicate;

    /**
     * Creates a FindBillCommand to find bill(s) according to the prefix input(s).
     *
     * @param namePredicate Optional predicate to filter bills by name.
     * @param paymentStatusPredicate Optional predicate to filter bills by payment status.
     */
    public FindBillCommand(Optional<Predicate<Name>> namePredicate,
                           Optional<Predicate<PaymentStatus>> paymentStatusPredicate) {
        this.predicate = bill -> namePredicate.orElse(x -> true).test(bill.getAppointment().getName())
                && paymentStatusPredicate.orElse(x -> true).test(bill.getPaymentStatus());
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

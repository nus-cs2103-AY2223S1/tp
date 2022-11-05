package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.BillDate;
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
            + "Example: " + COMMAND_WORD + " d/2022 \n"
            + "Example: " + COMMAND_WORD + " a/60 \n"
            + "Example: " + COMMAND_WORD + " n/Bob p/unpaid \n";

    private final Predicate<Bill> predicate;
    private Optional<Predicate<Name>> namePredicate = null;
    private Optional<Predicate<PaymentStatus>> paymentStatusPredicate = null;
    private Optional<Predicate<BillDate>> billDatePredicate = null;
    private Optional<Predicate<Amount>> amountPredicate = null;

    /**
     * Creates a FindBillCommand to find bill(s) according to the prefix input(s).
     *
     * @param namePredicate Optional predicate to filter bills by name.
     * @param paymentStatusPredicate Optional predicate to filter bills by payment status.
     * @param billDatePredicate Optional predicate to filter bills by bill date.
     * @param amountPredicate Optional predicate to filter bills by amount.
     */
    public FindBillCommand(Optional<Predicate<Name>> namePredicate,
                           Optional<Predicate<PaymentStatus>> paymentStatusPredicate,
                           Optional<Predicate<BillDate>> billDatePredicate,
                           Optional<Predicate<Amount>> amountPredicate) {
        this.namePredicate = namePredicate;
        this.paymentStatusPredicate = paymentStatusPredicate;
        this.billDatePredicate = billDatePredicate;
        this.amountPredicate = amountPredicate;
        this.predicate = bill -> namePredicate.orElse(x -> true).test(bill.getAppointment().getName())
                && paymentStatusPredicate.orElse(x -> true).test(bill.getPaymentStatus())
                && billDatePredicate.orElse(x -> true).test(bill.getBillDate())
                && amountPredicate.orElse(x -> true).test(bill.getAmount());
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
                || (other instanceof FindAppointmentCommand // instanceof handles nulls
                && namePredicate.equals(((FindBillCommand) other).namePredicate)
                && paymentStatusPredicate.equals(((FindBillCommand) other).paymentStatusPredicate)
                && billDatePredicate.equals(((FindBillCommand) other).billDatePredicate)
                && amountPredicate.equals(((FindBillCommand) other).amountPredicate)); // state check
    }

    public Predicate<Bill> getPredicate() {
        return predicate;
    }
}

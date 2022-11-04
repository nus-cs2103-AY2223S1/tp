package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bill.Bill;

/**
 * Sorts Bill data in HealthContact.
 */
public class SortBillCommand extends Command {
    public static final CommandWord COMMAND_WORD = new CommandWord("sortbill", "sob");
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Sorts the list of bills according to the specified field"
                    + "by alphabetical order.\n"
                    + "Parameters: c/CRITERIA (name, amount, date, status) o/ORDER (asc, desc)\n"
                    + "Example: " + COMMAND_WORD + " " + "c/name o/asc";

    public static final String MESSAGE_SORT_SUCCESS = "Sorted according to %1$s";

    private final String criteria;
    private final boolean isAscending;

    /**
     * @param criteria to be sorted by
     */
    public SortBillCommand(String criteria, boolean isAscending) {
        requireAllNonNull(criteria);
        this.criteria = criteria;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.criteria.toLowerCase().equals("name")) {
            AppointmentComparator appointmentComparator = new AppointmentComparator();
            model.sortBills(appointmentComparator, this.isAscending);
        } else if (this.criteria.toLowerCase().equals("amount")) {
            AmountComparator amountComparator = new AmountComparator();
            model.sortBills(amountComparator, this.isAscending);
        } else if (this.criteria.toLowerCase().equals("date")) {
            DateComparator dateComparator = new DateComparator();
            model.sortBills(dateComparator, this.isAscending);
        } else if (this.criteria.toLowerCase().equals("status")) {
            StatusComparator statusComparator = new StatusComparator();
            model.sortBills(statusComparator, this.isAscending);
        } else {
            throw new CommandException(MESSAGE_USAGE);
        }

        String message = String.format(MESSAGE_SORT_SUCCESS, this.criteria);
        return new CommandResult(message);
    }

    /**
     * Compares two Bills by appointment.
     */
    public class AppointmentComparator implements Comparator<Bill> {
        @Override
        public int compare(Bill first, Bill second) {
            return first.getAppointment().toString().compareTo(second.getAppointment().toString());
        }
    }

    /**
     * Compares two Bills by amount.
     */
    public class AmountComparator implements Comparator<Bill> {
        @Override
        public int compare(Bill first, Bill second) {
            return first.getAmount().toString().compareToIgnoreCase(second.getAmount().toString());
        }
    }

    /**
     * Compares two Bills by date.
     */
    public class DateComparator implements Comparator<Bill> {
        @Override
        public int compare(Bill first, Bill second) {
            return first.getBillDate().toString().compareTo(second.getBillDate().toString());
        }
    }

    /**
     * Compares two Bills by payment status.
     */
    public class StatusComparator implements Comparator<Bill> {
        @Override
        public int compare(Bill first, Bill second) {
            return first.getPaymentStatus().toString().compareTo(second.getPaymentStatus().toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SortBillCommand)) {
            return false;
        }

        SortBillCommand e = (SortBillCommand) other;
        return criteria.equals(e.criteria)
                && isAscending == e.isAscending;
    }
}

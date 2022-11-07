package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.BillDate;
import seedu.address.model.bill.PaymentStatus;

/**
 * Adds a bill to the HealthContact.
 */
public class AddBillCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("addbill", "ab");

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a bill that corresponds to an appointment to HealthContact.\n"
            + "Parameters: INDEX_OF_APPOINTMENT (must be a positive integer) "
            + PREFIX_BILL_DATE + "DATE<yyyy-MM-dd> "
            + PREFIX_AMOUNT + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_BILL_DATE + "2022-11-12 "
            + PREFIX_AMOUNT + "100";
    public static final String MESSAGE_SUCCESS = "New bill added: %1$s";
    public static final String MESSAGE_DUPLICATE_BILL = "This bill already exists in HealthContact";
    public static final String MESSAGE_APPOINTMENT_NOT_EXIST =
            "This appointment does not exist in HealthContact";
    public static final String MESSAGE_BILL_DATE_EARLIER_THAN_SLOT =
            "The bill date must not be earlier than appointment slot";

    private final Index indexOfAppointment;
    private final BillDate billDate;
    private final Amount amount;
    private final Bill bill;
    private final PaymentStatus defaultPaymentStatus = new PaymentStatus(PaymentStatus.Status.UNPAID.toString());

    /**
     * Creates an AddBillCommand to add the specified {@code Bill}
     */
    public AddBillCommand(Index indexOfAppointment, BillDate billDate, Amount amount) {
        requireAllNonNull(indexOfAppointment, billDate, amount);
        this.indexOfAppointment = indexOfAppointment;
        this.billDate = billDate;
        this.amount = amount;
        this.bill = null;
    }

    /**
     * Creates an AddBillCommand to add the specified {@code Bill}
     */
    public AddBillCommand(Bill bill) {
        requireAllNonNull(bill);
        this.indexOfAppointment = null;
        this.billDate = null;
        this.amount = null;
        this.bill = bill;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (this.bill == null) {
            requireNonNull(model);
            List<Appointment> lastShownAppointmentList = model.getFilteredAppointmentList();

            Appointment appointment;

            if (indexOfAppointment.getZeroBased() >= lastShownAppointmentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
            }

            try {
                appointment = model.getFilteredAppointmentList()
                        .get(indexOfAppointment.getZeroBased());
            } catch (AppointmentNotFoundException e) {
                throw new CommandException(MESSAGE_APPOINTMENT_NOT_EXIST);
            }

            Bill toAdd = new Bill(appointment, amount, billDate, defaultPaymentStatus);

            if (model.hasBill(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_BILL);
            }

            if (toAdd.getBillDate().localDate.isBefore(toAdd.getAppointment().getSlot().localDateTime.toLocalDate())) {
                throw new CommandException(MESSAGE_BILL_DATE_EARLIER_THAN_SLOT);
            }

            model.addBill(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } else {
            if (model.hasBill(this.bill)) {
                throw new CommandException(MESSAGE_DUPLICATE_BILL);
            }

            model.addBill(this.bill);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.bill));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this.bill == null) {
            return other == this // short circuit if same object
                    || (other instanceof AddBillCommand // instanceof handles nulls
                    && indexOfAppointment.equals(((AddBillCommand) other).indexOfAppointment)
                    && billDate.equals(((AddBillCommand) other).billDate)
                    && amount.equals(((AddBillCommand) other).amount)
                    && defaultPaymentStatus.equals(((AddBillCommand) other).defaultPaymentStatus));
        } else {
            return other == this
                    || (other instanceof AddBillCommand && bill.equals(((AddBillCommand) other).bill));
        }
    }
}

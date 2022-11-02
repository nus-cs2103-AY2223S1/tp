package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BILLS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.BillDate;

/**
 * Edits the details of an existing bill in the HealthContact.
 */
public class EditBillCommand extends Command {
    public static final CommandWord COMMAND_WORD = new CommandWord("editbill", "eb");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the bill identified "
            + "by the index number used in the displayed bill list. "
            + "Existing values will be overwritten by the input values. At least one field must be provided.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_BILL_DATE + "BILL_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "10.00 "
            + PREFIX_BILL_DATE + "2019-12-24";

    public static final String MESSAGE_EDIT_BILL_SUCCESS = "Edited Bill: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index indexOfBill;
    private final EditBillDescriptor editBillDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editBillDescriptor details to edit the patient with
     */
    public EditBillCommand(Index index, EditBillDescriptor editBillDescriptor) {
        requireNonNull(index);
        requireNonNull(editBillDescriptor);

        this.indexOfBill = index;
        this.editBillDescriptor = new EditBillDescriptor(editBillDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bill> lastShownList = model.getFilteredBillList();

        if (indexOfBill.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Bill billToEdit = lastShownList.get(indexOfBill.getZeroBased());
        Bill editedBill = createEditedBill(billToEdit, editBillDescriptor);

        model.setBill(billToEdit, editedBill);
        model.updateFilteredBillList(PREDICATE_SHOW_ALL_BILLS);
        return new CommandResult(String.format(MESSAGE_EDIT_BILL_SUCCESS, editedBill));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code billToEdit}
     * edited with {@code editBillDescriptor}.
     */
    private static Bill createEditedBill(Bill billToEdit,
                                         EditBillDescriptor editBillDescriptor) throws CommandException {
        assert billToEdit != null;

        Amount updatedAmount = editBillDescriptor.getAmount().orElse(billToEdit.getAmount());
        BillDate updatedBillDate = editBillDescriptor.getBillDate().orElse(billToEdit.getBillDate());

        return new Bill(billToEdit.getAppointment(), updatedAmount, updatedBillDate, billToEdit.getPaymentStatus());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditBillCommand)) {
            return false;
        }

        EditBillCommand e = (EditBillCommand) other;
        return indexOfBill.equals(e.indexOfBill)
                && editBillDescriptor.equals(e.editBillDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditBillDescriptor {
        private Amount amount;
        private BillDate billDate;

        public EditBillDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBillDescriptor(EditBillDescriptor toCopy) {
            setAmount(toCopy.amount);
            setBillDate(toCopy.billDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount, billDate);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setBillDate(BillDate billDate) {
            this.billDate = billDate;
        }

        public Optional<BillDate> getBillDate() {
            return Optional.ofNullable(billDate);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditBillDescriptor)) {
                return false;
            }

            EditBillDescriptor e = (EditBillDescriptor) other;

            return getAmount().equals(e.getAmount())
                    && getBillDate().equals(e.getBillDate());
        }
    }
}

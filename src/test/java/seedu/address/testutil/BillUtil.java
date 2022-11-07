package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import seedu.address.logic.commands.AddBillCommand;
import seedu.address.logic.commands.EditBillCommand.EditBillDescriptor;
import seedu.address.model.bill.Bill;

/**
 * A utility class for Bill.
 */
public class BillUtil {

    /**
     * Returns an add command string for adding the {@code bill}.
     */
    public static String getAddCommand(Bill bill) {
        return AddBillCommand.COMMAND_WORD + " " + getBillDetails(bill);
    }

    /**
     * Returns the part of command string for the given {@code bill}'s details.
     */
    public static String getBillDetails(Bill bill) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDEX_FIRST_APPOINTMENT.getOneBased() + " ");
        sb.append(PREFIX_AMOUNT + bill.getAmount().toString() + " ");
        sb.append(PREFIX_DOCTOR + bill.getBillDate().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditBillDescriptor}'s details.
     */
    public static String getEditBillDescriptorDetails(EditBillDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT).append(amount.toString()).append(" "));
        descriptor.getBillDate().ifPresent(billDate -> sb.append(PREFIX_BILL_DATE).append(billDate).append(" "));
        return sb.toString();
    }
}

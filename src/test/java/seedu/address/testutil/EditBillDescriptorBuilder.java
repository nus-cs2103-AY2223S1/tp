package seedu.address.testutil;

import seedu.address.logic.commands.EditBillCommand;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.BillDate;

/**
 * A utility class to help with building EditBillDescriptor objects.
 */
public class EditBillDescriptorBuilder {
    private EditBillCommand.EditBillDescriptor descriptor;

    public EditBillDescriptorBuilder() {
        descriptor = new EditBillCommand.EditBillDescriptor();
    }

    public EditBillDescriptorBuilder(EditBillCommand.EditBillDescriptor descriptor) {
        this.descriptor = new EditBillCommand.EditBillDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBillDescriptor} with fields containing {@code appointment}'s details
     */
    public EditBillDescriptorBuilder(Bill bill) {
        descriptor = new EditBillCommand.EditBillDescriptor();
        descriptor.setAmount(bill.getAmount());
        descriptor.setBillDate(bill.getBillDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditBillDescriptor} that we are building.
     */
    public EditBillDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code doctor} of the {@code EditBillDescriptorBuilder} that we are building.
     */
    public EditBillDescriptorBuilder withBillDate(String billDate) {
        descriptor.setBillDate(new BillDate(billDate));
        return this;
    }

    public EditBillCommand.EditBillDescriptor build() {
        return descriptor;
    }
}

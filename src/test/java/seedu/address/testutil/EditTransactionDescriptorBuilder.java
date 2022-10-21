package seedu.address.testutil;

import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.model.transaction.*;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditTransactionDescriptorBuilder {

    private EditTransactionCommand.EditTransactionDescriptor descriptor;

    public EditTransactionDescriptorBuilder() {
        descriptor = new EditTransactionCommand.EditTransactionDescriptor();
    }

    public EditTransactionDescriptorBuilder(EditTransactionCommand.EditTransactionDescriptor descriptor) {
        this.descriptor = new EditTransactionCommand.EditTransactionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTransactionDescriptor} with fields containing {@code transaction}'s details
     */
    public EditTransactionDescriptorBuilder(Transaction transaction) {
        descriptor = new EditTransactionCommand.EditTransactionDescriptor();
        descriptor.setGoods(transaction.getGoods());
        descriptor.setPrice(transaction.getPrice());
        descriptor.setQuantity(transaction.getQuantity());
        descriptor.setDate(transaction.getDate());
    }

    /**
     * Sets the {@code Goods} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withGoods(String goods) {
        descriptor.setGoods(new Goods(goods));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    public EditTransactionCommand.EditTransactionDescriptor build() {
        return descriptor;
    }
}

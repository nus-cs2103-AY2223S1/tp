package seedu.address.testutil;

import seedu.address.logic.commands.EditStockCommand.EditStockDescriptor;
import seedu.address.model.item.SupplyItem;

/**
 * A utility class to help with building EditStockDescriptor objects.
 */
public class EditStockDescriptorBuilder {
    private EditStockDescriptor descriptor;

    public EditStockDescriptorBuilder() {
        this.descriptor = new EditStockDescriptor();
    }

    /**
     * Returns an {@code EditStockDescriptor} with fields containing only {@code supplyItem}'s updated {@currentStock}.
     */
    public EditStockDescriptorBuilder(SupplyItem supplyItem) {
        this.descriptor = new EditStockDescriptor();
        this.descriptor.setCurrentStock(supplyItem.getCurrentStock());
    }

    /**
     * Sets the {@code currentStock} of the {@code EditStockDescriptor} that we are building.
     */
    public EditStockDescriptorBuilder withCurrentStock(int currentStock) {
        this.descriptor.setCurrentStock(currentStock);
        return this;
    }

    public EditStockDescriptor build() {
        return this.descriptor;
    }
}

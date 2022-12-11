package seedu.foodrem.testutil;

import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemPrice;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.model.item.ItemUnit;

/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class EditItemDescriptorBuilder {
    private final EditItemDescriptor descriptor;

    /**
     * Constructs an EditItemDescriptorBuilder.
     */
    public EditItemDescriptorBuilder() {
        descriptor = new EditItemDescriptor();
    }

    /**
     * Constructs an EditItemDescriptorBuilder.
     */
    public EditItemDescriptorBuilder(EditItemDescriptor descriptor) {
        this.descriptor = new EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code item}'s details
     */
    public EditItemDescriptorBuilder(Item item) {
        descriptor = new EditItemDescriptor();
        descriptor.setItemName(item.getName());
        descriptor.setItemQuantity(item.getQuantity());
        descriptor.setItemUnit(item.getUnit());
        descriptor.setItemBoughtDate(item.getBoughtDate());
        descriptor.setItemExpiryDate(item.getExpiryDate());
        descriptor.setItemPrice(item.getPrice());
        descriptor.setItemRemarks(item.getRemarks());
    }

    /**
     * Sets the {@code Name} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemName(String name) {
        descriptor.setItemName(new ItemName(name));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemQuantity(String quantity) {
        descriptor.setItemQuantity(new ItemQuantity(quantity));
        return this;
    }

    /**
     * Sets the {@code Unit} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemUnit(String unit) {
        descriptor.setItemUnit(new ItemUnit(unit));
        return this;
    }

    /**
     * Sets the {@code Bought Date} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemBoughtDate(String boughtDate) {
        descriptor.setItemBoughtDate(ItemBoughtDate.of(boughtDate));
        return this;
    }

    /**
     * Sets the {@code Expiry Date} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemExpiryDate(String boughtDate) {
        descriptor.setItemExpiryDate(ItemExpiryDate.of(boughtDate));
        return this;
    }

    /**
     * Sets the {@code price} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemPrice(String price) {
        descriptor.setItemPrice(new ItemPrice(price));
        return this;
    }

    /**
     * Sets the {@code remarks} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemRemarks(String remarks) {
        descriptor.setItemRemarks(new ItemRemark(remarks));
        return this;
    }

    /**
     * Returns an EditItemDescriptor.
     */
    public EditItemDescriptor build() {
        return descriptor;
    }
}

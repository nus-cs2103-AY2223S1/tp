package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemBoughtDate;
import seedu.address.model.item.ItemExpiryDate;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.ItemQuantity;
import seedu.address.model.item.ItemUnit;

/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class EditItemDescriptorBuilder {

    private final EditItemDescriptor descriptor;

    public EditItemDescriptorBuilder() {
        descriptor = new EditItemDescriptor();
    }

    public EditItemDescriptorBuilder(EditItemDescriptor descriptor) {
        this.descriptor = new EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code person}'s details
     */
    public EditItemDescriptorBuilder(Item person) {
        descriptor = new EditItemDescriptor();
        descriptor.setItemName(person.getName());
        descriptor.setItemQuantity(person.getQuantity());
        descriptor.setItemUnit(person.getUnit());
        descriptor.setItemBoughtDate(person.getBoughtDate());
        descriptor.setItemExpiryDate(person.getExpiryDate());
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
        descriptor.setItemBoughtDate(new ItemBoughtDate(boughtDate));
        return this;
    }

    /**
     * Sets the {@code Expiry Date} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemExpiryDate(String boughtDate) {
        descriptor.setItemExpiryDate(new ItemExpiryDate(boughtDate));
        return this;
    }


    ///**
    // * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditItemDescriptor}
    // * that we are building.
    // */
    //public EditItemDescriptorBuilder withTags(String... tags) {
    //    Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
    //    descriptor.setTags(tagSet);
    //    return this;
    //}

    public EditItemDescriptor build() {
        return descriptor;
    }
}

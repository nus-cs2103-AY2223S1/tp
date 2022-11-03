package seedu.address.testutil;

import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Description;
import seedu.address.model.property.Owner;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;

/**
 * A utility class to help with building EditPropertyDescriptor objects.
 */
public class EditPropertyDescriptorBuilder {

    private EditPropertyDescriptor descriptor;

    public EditPropertyDescriptorBuilder() {
        descriptor = new EditPropertyDescriptor();
    }

    public EditPropertyDescriptorBuilder(EditPropertyDescriptor descriptor) {
        this.descriptor = new EditPropertyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPropertyDescriptor} with fields containing {@code property}'s details
     */
    public EditPropertyDescriptorBuilder(Property property) {
        descriptor = new EditPropertyDescriptor();
        descriptor.setName(property.getPropertyName());
        descriptor.setPrice(property.getPrice());
        descriptor.setAddress(property.getAddress());
        descriptor.setDescription(property.getDescription());
        descriptor.setOwner(property.getOwner());
        descriptor.setOwnerName(property.getOwner().getName());
        descriptor.setOwnerPhone(property.getOwner().getPhone());
    }

    /**
     * Sets the {@code PropertyName} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withName(String name) {
        descriptor.setName(new PropertyName(name));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Characteristics} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withCharacteristics(String characteristics) {
        descriptor.setCharacteristics(new Characteristics(characteristics));
        return this;
    }

    /**
     * Sets the {@code Owner}, {@code Name} and {@code Phone} of the {@code EditPropertyDescriptor}
     * that we are building.
     */
    public EditPropertyDescriptorBuilder withOwner(String ownerName, String ownerPhone) {
        Name newOwnerName = new Name(ownerName);
        Phone newOwnerPhone = new Phone(ownerPhone);
        descriptor.setOwner(new Owner(newOwnerName, newOwnerPhone));
        descriptor.setOwnerName(newOwnerName);
        descriptor.setOwnerPhone(newOwnerPhone);
        return this;
    }

    /**
     * Sets the {@code ownerName} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withOwnerName(String ownerName) {
        descriptor.setOwnerName(new Name(ownerName));
        return this;
    }

    /**
     * Sets the {@code ownerPhone} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withOwnerPhone(String ownerPhone) {
        descriptor.setOwnerPhone(new Phone(ownerPhone));
        return this;
    }



    public EditPropertyCommand.EditPropertyDescriptor build() {
        return descriptor;
    }

}

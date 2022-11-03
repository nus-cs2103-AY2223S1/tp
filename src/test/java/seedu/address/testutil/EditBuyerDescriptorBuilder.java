package seedu.address.testutil;

import seedu.address.logic.commands.buyer.EditBuyerCommand;
import seedu.address.logic.commands.buyer.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;

/**
 * A utility class to help with building EditBuyerDescriptor objects.
 */
public class EditBuyerDescriptorBuilder {

    private EditBuyerCommand.EditBuyerDescriptor descriptor;

    public EditBuyerDescriptorBuilder() {
        descriptor = new EditBuyerDescriptor();
    }

    public EditBuyerDescriptorBuilder(EditBuyerCommand.EditBuyerDescriptor descriptor) {
        this.descriptor = new EditBuyerCommand.EditBuyerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBuyerDescriptor} with fields containing {@code buyer}'s details
     */
    public EditBuyerDescriptorBuilder(Buyer buyer) {
        descriptor = new EditBuyerCommand.EditBuyerDescriptor();
        descriptor.setName(buyer.getName());
        descriptor.setPhone(buyer.getPhone());
        descriptor.setEmail(buyer.getEmail());
        descriptor.setAddress(buyer.getAddress());
        descriptor.setPriceRange(buyer.getPriceRange().orElse(null));
        descriptor.setDesiredCharacteristics(buyer.getDesiredCharacteristics().orElse(null));
        descriptor.setPriority(buyer.getPriority());
    }

    /**
     * Sets the {@code Name} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPriceRange(String priceRange) {
        descriptor.setPriceRange(new PriceRange(priceRange));
        return this;
    }

    /**
     * Sets the {@code DesiredCharacteristics} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withDesiredCharacteristics(String desiredCharacteristics) {
        descriptor.setDesiredCharacteristics(new Characteristics(desiredCharacteristics));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditBuyerCommand.EditBuyerDescriptor build() {
        return descriptor;
    }
}

package seedu.address.testutil;

import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
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
public class EditPersonDescriptorBuilder {

    private EditBuyerCommand.EditBuyerDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditBuyerDescriptor();
    }

    public EditPersonDescriptorBuilder(EditBuyerCommand.EditBuyerDescriptor descriptor) {
        this.descriptor = new EditBuyerCommand.EditBuyerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBuyerDescriptor} with fields containing {@code buyer}'s details
     */
    public EditPersonDescriptorBuilder(Buyer buyer) {
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
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPriceRange(String priceRange) {
        descriptor.setPriceRange(new PriceRange(priceRange));
        return this;
    }

    /**
     * Sets the {@code DesiredCharacteristics} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDesiredCharacteristics(String desiredCharacteristics) {
        descriptor.setDesiredCharacteristics(new Characteristics(desiredCharacteristics));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditBuyerCommand.EditBuyerDescriptor build() {
        return descriptor;
    }
}

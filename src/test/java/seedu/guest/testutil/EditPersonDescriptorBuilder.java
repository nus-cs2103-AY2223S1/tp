package seedu.guest.testutil;

import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditGuestDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditGuestDescriptor();
    }

    public EditPersonDescriptorBuilder(EditGuestDescriptor descriptor) {
        this.descriptor = new EditGuestDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Guest guest) {
        descriptor = new EditGuestDescriptor();
        descriptor.setName(guest.getName());
        descriptor.setPhone(guest.getPhone());
        descriptor.setEmail(guest.getEmail());
        descriptor.setNumberOfGuests(guest.getNumberOfGuests());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code NumberOfGuests} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNumberOfGuests(String numberOfGuests) {
        descriptor.setNumberOfGuests(new NumberOfGuests(numberOfGuests));
        return this;
    }

    public EditGuestDescriptor build() {
        return descriptor;
    }
}

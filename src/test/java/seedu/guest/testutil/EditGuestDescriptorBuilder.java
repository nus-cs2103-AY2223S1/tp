package seedu.guest.testutil;

import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Room;

/**
 * A utility class to help with building EditGuestDescriptor objects.
 */
public class EditGuestDescriptorBuilder {

    private EditGuestDescriptor descriptor;

    public EditGuestDescriptorBuilder() {
        descriptor = new EditGuestDescriptor();
    }

    public EditGuestDescriptorBuilder(EditGuestDescriptor descriptor) {
        this.descriptor = new EditGuestDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditGuestDescriptor} with fields containing {@code guest}'s details
     */
    public EditGuestDescriptorBuilder(Guest guest) {
        descriptor = new EditGuestDescriptor();
        descriptor.setName(guest.getName());
        descriptor.setPhone(guest.getPhone());
        descriptor.setEmail(guest.getEmail());
        descriptor.setRoom(guest.getRoom());
        descriptor.setDateRange(guest.getDateRange());
        descriptor.setNumberOfGuests(guest.getNumberOfGuests());
        descriptor.setIsRoomClean(guest.getIsRoomClean());
    }

    /**
     * Sets the {@code Name} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withRoom(String room) {
        descriptor.setRoom(new Room(room));
        return this;
    }

    /**
     * Sets the {@code DateRange} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withDateRange(String dateRange) {
        descriptor.setDateRange(new DateRange(dateRange));
        return this;
    }

    /**
     * Sets the {@code NumberOfGuests} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withNumberOfGuests(String numberOfGuests) {
        descriptor.setNumberOfGuests(new NumberOfGuests(numberOfGuests));
        return this;
    }

    /**
     * Sets the {@code IsRoomClean} of the {@code EditGuestDescriptor} that we are building.
     */
    public EditGuestDescriptorBuilder withIsRoomClean(String isRoomClean) {
        descriptor.setIsRoomClean(new IsRoomClean(isRoomClean));
        return this;
    }

    public EditGuestDescriptor build() {
        return descriptor;
    }
}

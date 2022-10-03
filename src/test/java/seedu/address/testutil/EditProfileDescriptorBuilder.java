package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditProfileDescriptor;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;

/**
 * A utility class to help with building EditProfileDescriptor objects.
 */
public class EditProfileDescriptorBuilder {

    private EditProfileDescriptor descriptor;

    public EditProfileDescriptorBuilder() {
        descriptor = new EditProfileDescriptor();
    }

    public EditProfileDescriptorBuilder(EditProfileDescriptor descriptor) {
        this.descriptor = new EditProfileDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProfileDescriptor} with fields containing {@code profile}'s details
     */
    public EditProfileDescriptorBuilder(Profile profile) {
        descriptor = new EditProfileDescriptor();
        descriptor.setName(profile.getName());
        descriptor.setPhone(profile.getPhone());
        descriptor.setEmail(profile.getEmail());
    }

    /**
     * Sets the {@code Name} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    public EditProfileDescriptor build() {
        return descriptor;
    }
}

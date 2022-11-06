package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditTeammateDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.teammate.Address;
import seedu.address.model.teammate.Email;
import seedu.address.model.teammate.Name;
import seedu.address.model.teammate.Phone;
import seedu.address.model.teammate.Teammate;

/**
 * A utility class to help with building EditTeammateDescriptor objects.
 */
public class EditTeammateDescriptorBuilder {

    private final EditTeammateDescriptor descriptor;

    public EditTeammateDescriptorBuilder() {
        descriptor = new EditTeammateDescriptor();
    }

    public EditTeammateDescriptorBuilder(EditTeammateDescriptor descriptor) {
        this.descriptor = new EditTeammateDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTeammateDescriptor} with fields containing {@code teammate}'s details
     */
    public EditTeammateDescriptorBuilder(Teammate teammate) {
        descriptor = new EditTeammateDescriptor();
        descriptor.setName(teammate.getName());
        descriptor.setPhone(teammate.getPhone());
        descriptor.setEmail(teammate.getEmail());
        descriptor.setAddress(teammate.getAddress());
        descriptor.setTags(teammate.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditTeammateDescriptor} that we are building.
     */
    public EditTeammateDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTeammateDescriptor}
     * that we are building.
     */
    public EditTeammateDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTeammateDescriptor build() {
        return descriptor;
    }
}

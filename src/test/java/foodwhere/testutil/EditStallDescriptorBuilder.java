package foodwhere.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import foodwhere.logic.commands.EditCommand;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Stall;

/**
 * A utility class to help with building EditStallDescriptor objects.
 */
public class EditStallDescriptorBuilder {

    private EditCommand.EditStallDescriptor descriptor;

    public EditStallDescriptorBuilder() {
        descriptor = new EditCommand.EditStallDescriptor();
    }

    public EditStallDescriptorBuilder(EditCommand.EditStallDescriptor descriptor) {
        this.descriptor = new EditCommand.EditStallDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStallDescriptor} with fields containing {@code stall}'s details
     */
    public EditStallDescriptorBuilder(Stall stall) {
        descriptor = new EditCommand.EditStallDescriptor();
        descriptor.setName(stall.getName());
        descriptor.setAddress(stall.getAddress());
        descriptor.setTags(stall.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStallDescriptor}
     * that we are building.
     */
    public EditStallDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditStallDescriptor build() {
        return descriptor;
    }
}

package seedu.condonery.testutil;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * A utility class to help with building EditPropertyDescriptor objects.
 */
public class EditPropertyDescriptorBuilder {

    private final EditPropertyDescriptor descriptor;

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
        descriptor.setName(property.getName());
        descriptor.setAddress(property.getAddress());
        descriptor.setTags(property.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPropertyDescriptor}
     * that we are building.
     */
    public EditPropertyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }



    @Override
    public int hashCode() {
        return Objects.hash(descriptor);
    }

    public EditPropertyDescriptor build() {
        return descriptor;
    }
}

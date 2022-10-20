package seedu.condonery.testutil;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.condonery.logic.commands.client.EditClientCommand.EditClientDescriptor;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.tag.Tag;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {
    private final EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setAddress(client.getAddress());
        descriptor.setTags(client.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }



    @Override
    public int hashCode() {
        return Objects.hash(descriptor);
    }

    public EditClientDescriptor build() {
        return descriptor;
    }

}

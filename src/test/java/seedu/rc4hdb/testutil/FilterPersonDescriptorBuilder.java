package seedu.rc4hdb.testutil;

import java.util.Set;

import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand.FilterPersonDescriptor;
import seedu.rc4hdb.model.person.Address;
import seedu.rc4hdb.model.person.Email;
import seedu.rc4hdb.model.person.Name;
import seedu.rc4hdb.model.person.Person;
import seedu.rc4hdb.model.person.Phone;
import seedu.rc4hdb.model.tag.Tag;

/**
 * A utility class to help with building FilterPersonDescriptor objects.
 */
public class FilterPersonDescriptorBuilder {

    private FilterPersonDescriptor descriptor;

    public FilterPersonDescriptorBuilder() {
        descriptor = new FilterPersonDescriptor();
    }

    public FilterPersonDescriptorBuilder(FilterPersonDescriptor descriptor) {
        this.descriptor = new FilterPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code FilterPersonDescriptor} with fields containing {@code person}'s details
     */
    public FilterPersonDescriptorBuilder(Person person) {
        descriptor = new FilterPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code FilterPersonDescriptor} that we are building.
     */
    public FilterPersonDescriptorBuilder withName(Name name) {
        descriptor.setName(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code FilterPersonDescriptor} that we are building.
     */
    public FilterPersonDescriptorBuilder withPhone(Phone phone) {
        descriptor.setPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code FilterPersonDescriptor} that we are building.
     */
    public FilterPersonDescriptorBuilder withEmail(Email email) {
        descriptor.setEmail(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code FilterPersonDescriptor} that we are building.
     */
    public FilterPersonDescriptorBuilder withAddress(Address address) {
        descriptor.setAddress(address);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code FilterPersonDescriptor}
     * that we are building.
     */
    public FilterPersonDescriptorBuilder withTags(Set<Tag> tagSet) {
        descriptor.setTags(tagSet);
        return this;
    }

    public FilterPersonDescriptor build() {
        return descriptor;
    }
}

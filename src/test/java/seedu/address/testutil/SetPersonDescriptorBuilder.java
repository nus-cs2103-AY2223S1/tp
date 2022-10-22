package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.SetCommand.SetPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;

/**
 * Utility class to build SetPersonDescriptor objects.
 */
public class SetPersonDescriptorBuilder {
    private final SetPersonDescriptor descriptor;

    public SetPersonDescriptorBuilder() {
        this.descriptor = new SetPersonDescriptor();
    }

    public SetPersonDescriptorBuilder(SetPersonDescriptor descriptor) {
        this.descriptor = new SetPersonDescriptor(descriptor);
    }

    /**
     * Instantiates a SetPersonDescriptor with fields containing a given Person's details.
     */
    public SetPersonDescriptorBuilder(Person person) {
        this.descriptor = new SetPersonDescriptor();
        this.descriptor.setName(person.getName());
        this.descriptor.setAddress(person.getAddress());
        this.descriptor.setRole(person.getRole().orElse(null));
        this.descriptor.setTimezone(person.getTimezone().orElse(null));
        this.descriptor.setTags(person.getTags());

        for (ContactType key : person.getContacts().keySet()) {
            this.descriptor.setContact(key, person.getContacts().get(key));
        }
    }

    /**
     * Sets the name of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the address of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the role of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the timezone of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withTimezone(String timezone) {
        descriptor.setTimezone(new Timezone(timezone));
        return this;
    }

    /**
     * Sets the tags of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the Contacts of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withContact(ContactType type, String value) {
        this.descriptor.setContact(type, Contact.of(type, value));
        return this;
    }

    public SetPersonDescriptor build() {
        return this.descriptor;
    }
}

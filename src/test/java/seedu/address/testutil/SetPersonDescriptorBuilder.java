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
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.person.contact.Email;
import seedu.address.model.person.contact.Phone;
import seedu.address.model.person.contact.Slack;
import seedu.address.model.person.contact.Telegram;
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
        this.descriptor.setRole(person.getRole());
        this.descriptor.setTimezone(person.getTimezone());
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
     * Sets the telegram Contact of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withTelegram(String telegram) {
        Telegram newTelegram = new Telegram(telegram);
        this.descriptor.setContact(ContactType.TELEGRAM, newTelegram);
        return this;
    }

    /**
     * Sets the email Contact of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withEmail(String email) {
        Email newEmail = new Email(email);
        this.descriptor.setContact(ContactType.EMAIL, newEmail);
        return this;
    }

    /**
     * Sets the phone Contact of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withPhone(String phone) {
        Phone newPhone = new Phone(phone);
        this.descriptor.setContact(ContactType.PHONE, newPhone);
        return this;
    }

    /**
     * Sets the slack Contact of the SetPersonDescriptor we are building.
     */
    public SetPersonDescriptorBuilder withSlack(String slack) {
        Slack newSlack = new Slack(slack);
        this.descriptor.setContact(ContactType.SLACK, newSlack);
        return this;
    }

    public SetPersonDescriptor build() {
        return this.descriptor;
    }
}

package paymelah.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.model.person.Address;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;

/**
 * A utility class to help with building PersonDescriptor objects.
 */
public class PersonDescriptorBuilder {

    private PersonDescriptor descriptor;

    public PersonDescriptorBuilder() {
        descriptor = new PersonDescriptor();
    }

    public PersonDescriptorBuilder(PersonDescriptor descriptor) {
        this.descriptor = new PersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code PersonDescriptor} with fields containing {@code person}'s details
     */
    public PersonDescriptorBuilder(Person person) {
        descriptor = new PersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setTelegram(person.getTelegram());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code PersonDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code PersonDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code PersonDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(new Telegram(telegram));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code PersonDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code PersonDescriptor}
     * that we are building.
     */
    public PersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public PersonDescriptor build() {
        return descriptor;
    }
}

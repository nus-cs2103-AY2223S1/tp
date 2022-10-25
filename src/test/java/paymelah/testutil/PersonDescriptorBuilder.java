package paymelah.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.model.person.Address;
import paymelah.model.person.Email;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
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
        descriptor.setEmail(person.getEmail());
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
     * Sets the {@code Email} of the {@code PersonDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
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

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>} and set it to the {@code PersonDescriptor}
     * that we are building.
     */
    public PersonDescriptorBuilder withDescriptions(String... descriptions) {
        Set<Description> descriptionSet = Stream.of(descriptions).map(Description::new).collect(Collectors.toSet());
        descriptor.setDescriptions(descriptionSet);
        return this;
    }

    /**
     * Parses the {@code monies} into a {@code Set<Money>} and set it to the {@code PersonDescriptor}
     * that we are building.
     */
    public PersonDescriptorBuilder withMonies(String... monies) {
        Set<Money> moneySet = Stream.of(monies).map(Money::new).collect(Collectors.toSet());
        descriptor.setMonies(moneySet);
        return this;
    }

    /**
     * Parses the {@code dates} into a {@code Set<DebtDate>} and set it to the {@code PersonDescriptor}
     * that we are building.
     */
    public PersonDescriptorBuilder withDates(String... dates) {
        Set<DebtDate> dateSet = Stream.of(dates).map(DebtDate::new).collect(Collectors.toSet());
        descriptor.setDates(dateSet);
        return this;
    }

    /**
     * Parses the {@code times} into a {@code Set<DebtTime>} and set it to the {@code PersonDescriptor}
     * that we are building.
     */
    public PersonDescriptorBuilder withTimes(String... times) {
        Set<DebtTime> timeSet = Stream.of(times).map(DebtTime::new).collect(Collectors.toSet());
        descriptor.setTimes(timeSet);
        return this;
    }

    public PersonDescriptor build() {
        return descriptor;
    }
}

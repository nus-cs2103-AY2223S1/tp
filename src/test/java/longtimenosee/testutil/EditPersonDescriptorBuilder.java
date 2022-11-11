package longtimenosee.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import longtimenosee.logic.commands.EditCommand.EditPersonDescriptor;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Birthday;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Income;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.Phone;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setBirthday(person.getBirthday());
        descriptor.setTags(person.getTags());
        descriptor.setIncome(person.getIncome());
        descriptor.setRiskAppetite(person.getRiskAppetite());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
    /**
     * Parses the {@code validBirthday} into a {@code Birthday} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withBirthday(String validBirthdayAmy) {
        descriptor.setBirthday(new Birthday(validBirthdayAmy));
        return this;
    }
    /**
     * Parses the {@code validIncome} into a {@code Income} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withIncome(String validIncome) {
        descriptor.setIncome(new Income(validIncome));
        return this;
    }
    /**
     * Parses the {@code validRA} into a {@code RiskAppetite} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withRiskAppetite(String validRA) {
        descriptor.setRiskAppetite(new RiskAppetite(validRA));
        return this;
    }

}

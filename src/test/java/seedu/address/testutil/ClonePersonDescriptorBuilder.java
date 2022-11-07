package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.CloneCommand.ClonePersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building ClonePersonDescriptor objects.
 */
public class ClonePersonDescriptorBuilder {

    private ClonePersonDescriptor descriptor;

    public ClonePersonDescriptorBuilder() {
        descriptor = new ClonePersonDescriptor();
    }

    public ClonePersonDescriptorBuilder(ClonePersonDescriptor descriptor) {
        this.descriptor = new ClonePersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code ClonePersonDescriptor} with fields containing {@code person}'s details
     */
    public ClonePersonDescriptorBuilder(Person person) {
        descriptor = new ClonePersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setGender(person.getGender());
        descriptor.setBirthdate(person.getBirthdate());
        descriptor.setRace(person.getRace());
        descriptor.setReligion(person.getReligion());
        descriptor.setSurveys(person.getSurveys());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code ClonePersonDescriptor} that we are building.
     */
    public ClonePersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code ClonePersonDescriptor} that we are building.
     */
    public ClonePersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code ClonePersonDescriptor} that we are building.
     */
    public ClonePersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code ClonePersonDescriptor} that we are building.
     */
    public ClonePersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code ClonePersonDescriptor}
     * that we are building.
     */
    public ClonePersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code surveys} into a {@code Set<Survey>} and set it to the {@code ClonePersonDescriptor}
     * that we are building.
     */
    public ClonePersonDescriptorBuilder withSurveys(String... surveys) {
        Set<Survey> surveySet = Stream.of(surveys).map(Survey::new).collect(Collectors.toSet());
        descriptor.setSurveys(surveySet);
        return this;
    }

    public ClonePersonDescriptor build() {
        return descriptor;
    }
}

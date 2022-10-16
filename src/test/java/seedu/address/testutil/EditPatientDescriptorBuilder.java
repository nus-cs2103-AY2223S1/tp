package seedu.uninurse.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.uninurse.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.Tag;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code person}'s details
     */
    public EditPatientDescriptorBuilder(Patient person) {
        descriptor = new EditPatientDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}

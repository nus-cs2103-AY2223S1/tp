package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditTutorDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditTutorDescriptor objects.
 */
public class EditTutorDescriptorBuilder {

    private EditTutorDescriptor descriptor;

    public EditTutorDescriptorBuilder() {
        descriptor = new EditTutorDescriptor();
    }

    public EditTutorDescriptorBuilder(EditTutorDescriptor descriptor) {
        this.descriptor = new EditTutorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTutorDescriptor} with fields containing {@code tutor}'s details
     */
    public EditTutorDescriptorBuilder(Tutor tutor) {
        descriptor = new EditTutorDescriptor();
        descriptor.setName(tutor.getName());
        descriptor.setPhone(tutor.getPhone());
        descriptor.setEmail(tutor.getEmail());
        descriptor.setAddress(tutor.getAddress());
        descriptor.setTags(tutor.getTags());
        descriptor.setQualification(tutor.getQualification());
        descriptor.setInstitution(tutor.getInstitution());
    }

    /**
     * Sets the {@code Name} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTutorDescriptor}
     * that we are building.
     */
    public EditTutorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTutorDescriptorBuilder withQualification(String qualification) {
        descriptor.setQualification(new Qualification(qualification));
        return this;
    }

    public EditTutorDescriptorBuilder withInstitution(String institution) {
        descriptor.setInstitution(new Institution(institution));
        return this;
    }

    public EditTutorDescriptor build() {
        return descriptor;
    }
}
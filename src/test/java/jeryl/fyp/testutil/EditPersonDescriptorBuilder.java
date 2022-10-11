package jeryl.fyp.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jeryl.fyp.logic.commands.EditCommand.EditPersonDescriptor;
import jeryl.fyp.model.person.Address;
import jeryl.fyp.model.person.Email;
import jeryl.fyp.model.person.Name;
import jeryl.fyp.model.person.Person;
import jeryl.fyp.model.person.StudentID;
import jeryl.fyp.model.tag.Tag;

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
        descriptor.setStudentID(person.getStudentID());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setProjectName(person.getProjectName());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code StudentID} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStudentID(String id) {
        descriptor.setStudentID(new StudentID(id));
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
     * Sets the {@code Project} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withProjectName(String projectName) {
        descriptor.setProjectName(projectName);
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
}

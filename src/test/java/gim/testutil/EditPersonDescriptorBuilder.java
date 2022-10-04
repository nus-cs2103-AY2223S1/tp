package gim.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.model.person.Address;
import gim.model.person.Email;
import gim.model.person.Name;
import gim.model.person.Exercise;
import gim.model.person.Phone;
import gim.model.tag.Tag;

/**
 * A utility class to help with building EditExerciseDescriptor objects.
 */
public class EditExerciseDescriptorBuilder {

    private EditExerciseDescriptor descriptor;

    public EditExerciseDescriptorBuilder() {
        descriptor = new EditExerciseDescriptor();
    }

    public EditExerciseDescriptorBuilder(EditExerciseDescriptor descriptor) {
        this.descriptor = new EditExerciseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExerciseDescriptor} with fields containing {@code person}'s details
     */
    public EditExerciseDescriptorBuilder(Exercise person) {
        descriptor = new EditExerciseDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditExerciseDescriptor}
     * that we are building.
     */
    public EditExerciseDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditExerciseDescriptor build() {
        return descriptor;
    }
}

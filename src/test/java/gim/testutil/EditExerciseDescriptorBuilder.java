package gim.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Rep;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
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
     * Returns an {@code EditExerciseDescriptor} with fields containing {@code exercise}'s details
     */
    public EditExerciseDescriptorBuilder(Exercise exercise) {
        descriptor = new EditExerciseDescriptor();
        descriptor.setName(exercise.getName());
        descriptor.setWeight(exercise.getWeight());
        descriptor.setSets(exercise.getSets());
        descriptor.setRep(exercise.getRep());
        descriptor.setTags(exercise.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    /**
     * Sets the {@code Sets} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withSets(String sets) {
        descriptor.setSets(new Sets(sets));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withRep(String address) {
        descriptor.setRep(new Rep(address));
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

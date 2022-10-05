package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.address.model.Calorie;
import seedu.address.model.person.Food;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditFoodDescriptorBuilder {

    private EditFoodDescriptor descriptor;

    public EditFoodDescriptorBuilder() {
        descriptor = new EditFoodDescriptor();
    }

    public EditFoodDescriptorBuilder(EditFoodDescriptor descriptor) {
        this.descriptor = new EditFoodDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditFoodDescriptorBuilder(Food food) {
        descriptor = new EditFoodDescriptor();
        descriptor.setName(food.getName());
        descriptor.setTags(food.getTags());
        descriptor.setCalorie(food.getCalorie());
        descriptor.setDateTime(food.getDateTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFoodDescriptor}
     * that we are building.
     */
    public EditFoodDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code calorie} into a {@code Calorie} and set it to the {@code EditFoodDescriptor}
     * that we are building.
     */
    public EditFoodDescriptorBuilder withCalorie(String calorie) {
        descriptor.setCalorie(new Calorie(calorie));
        return this;
    }

    public EditFoodDescriptor build() {
        return descriptor;
    }
}

package eatwhere.foodguide.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import eatwhere.foodguide.logic.commands.EditCommand;
import eatwhere.foodguide.logic.commands.EditCommand.EditEateryDescriptor;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Phone;
import eatwhere.foodguide.model.tag.Tag;

/**
 * A utility class to help with building EditEateryDescriptor objects.
 */
public class EditEateryDescriptorBuilder {

    private EditEateryDescriptor descriptor;

    public EditEateryDescriptorBuilder() {
        descriptor = new EditCommand.EditEateryDescriptor();
    }

    public EditEateryDescriptorBuilder(EditCommand.EditEateryDescriptor descriptor) {
        this.descriptor = new EditEateryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEateryDescriptor} with fields containing {@code eatery}'s details
     */
    public EditEateryDescriptorBuilder(Eatery eatery) {
        descriptor = new EditEateryDescriptor();
        descriptor.setName(eatery.getName());
        descriptor.setPhone(eatery.getPhone());
        descriptor.setCuisine(eatery.getCuisine());
        descriptor.setAddress(eatery.getLocation());
        descriptor.setTags(eatery.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEateryDescriptor} that we are building.
     */
    public EditEateryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEateryDescriptor} that we are building.
     */
    public EditEateryDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Cuisine} of the {@code EditEateryDescriptor} that we are building.
     */
    public EditEateryDescriptorBuilder withEmail(String cuisine) {
        descriptor.setCuisine(new Cuisine(cuisine));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditEateryDescriptor} that we are building.
     */
    public EditEateryDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Location(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEateryDescriptor}
     * that we are building.
     */
    public EditEateryDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditEateryDescriptor build() {
        return descriptor;
    }
}

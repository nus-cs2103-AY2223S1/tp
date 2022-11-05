package seedu.realtime.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.realtime.logic.commands.EditListingCommand.EditListingDescriptor;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.tag.Tag;

/**
 * A utility class to help with building EditListingDescriptor objects.
 */
public class EditListingDescriptorBuilder {

    private EditListingDescriptor descriptor;

    public EditListingDescriptorBuilder() {
        descriptor = new EditListingDescriptor();
    }

    public EditListingDescriptorBuilder(EditListingDescriptor descriptor) {
        this.descriptor = new EditListingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditListingDescriptor} with fields containing {@code listing}'s details
     */
    public EditListingDescriptorBuilder(Listing listing) {
        descriptor = new EditListingDescriptor();
        descriptor.setId(listing.getId());
        descriptor.setName(listing.getName());
        descriptor.setAddress(listing.getAddress());
        descriptor.setAskingPrice(listing.getAskingPrice());
        descriptor.setTags(listing.getTags());
    }
    /**
     * Sets the {@code Name} of the {@code EditListingDescriptor} that we are building.
     */
    public EditListingDescriptorBuilder withId(String id) {
        descriptor.setId(new ListingId(id));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditListingDescriptor} that we are building.
     */
    public EditListingDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditListingDescriptor} that we are building.
     */
    public EditListingDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code EditListingDescriptor} that we are building.
     */
    public EditListingDescriptorBuilder withAskingPrice(String askingPrice) {
        descriptor.setAskingPrice(new Price(askingPrice));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditListingDescriptor}
     * that we are building.
     */
    public EditListingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditListingDescriptor build() {
        return descriptor;
    }
}

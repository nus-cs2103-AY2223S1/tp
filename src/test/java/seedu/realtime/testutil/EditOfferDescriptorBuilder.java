package seedu.realtime.testutil;

import seedu.realtime.logic.commands.EditOfferCommand.EditOfferDescriptor;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Name;

/**
 * A utility class to help with building EditOfferDescriptor objects.
 */
public class EditOfferDescriptorBuilder {

    private EditOfferDescriptor descriptor;

    public EditOfferDescriptorBuilder() {
        descriptor = new EditOfferDescriptor();
    }

    public EditOfferDescriptorBuilder(EditOfferDescriptor descriptor) {
        this.descriptor = new EditOfferDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOfferDescriptor} with fields containing {@code offer}'s details
     */
    public EditOfferDescriptorBuilder(Offer offer) {
        descriptor = new EditOfferDescriptor();
        descriptor.setName(offer.getClient());
        descriptor.setOfferPrice(offer.getOfferPrice());
        descriptor.setListing(offer.getListing());
    }

    /**
     * Sets the {@code Name} of the {@Code EditOfferDescriptor} that we are building.
     */
    public EditOfferDescriptorBuilder withBuyer(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditOfferDescriptor} that we are building.
     */
    public EditOfferDescriptorBuilder withOfferPrice(String price) {
        descriptor.setOfferPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code Listing ID} of the {@code EditOfferDescriptor} that we are building.
     */
    public EditOfferDescriptorBuilder withListing(String listingId) {
        descriptor.setListing(new ListingId(listingId));
        return this;
    }

    public EditOfferDescriptor build() {
        return descriptor;
    }
}

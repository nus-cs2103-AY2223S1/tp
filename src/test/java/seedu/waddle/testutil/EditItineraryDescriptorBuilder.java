package seedu.waddle.testutil;

import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;

/**
 * A utility class to help with building EditItineraryDescriptor objects.
 */
public class EditItineraryDescriptorBuilder {

    private EditItineraryDescriptor descriptor;

    public EditItineraryDescriptorBuilder() {
        descriptor = new EditItineraryDescriptor();
    }

    public EditItineraryDescriptorBuilder(EditItineraryDescriptor descriptor) {
        this.descriptor = new EditItineraryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItineraryDescriptor} with fields containing an {@code itinerary}'s details
     */
    public EditItineraryDescriptorBuilder(Itinerary itinerary) {
        descriptor = new EditItineraryDescriptor();
        descriptor.setName(itinerary.getName());
        descriptor.setCountry(itinerary.getCountry());
        descriptor.setStartDate(itinerary.getStartDate());
        descriptor.setEndDate(itinerary.getEndDate());
        descriptor.setPeople(itinerary.getPeople());
    }

    /**
     * Sets the {@code Name} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Country} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withCountry(String country) {
        descriptor.setCountry(new Country(country));
        return this;
    }

    /**
     * Sets the {@code Start Date} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new Date(startDate));
        return this;
    }

    /**
     * Sets the {@code End Date} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new Date(endDate));
        return this;
    }

    /**
     * Sets the {@code People} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withPeople(String people) {
        descriptor.setPeople(new People(people));
        return this;
    }

    public EditItineraryDescriptor build() {
        return descriptor;
    }
}

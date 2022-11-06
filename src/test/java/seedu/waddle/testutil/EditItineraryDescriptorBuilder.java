package seedu.waddle.testutil;

import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
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
        descriptor.setName(itinerary.getDescription());
        descriptor.setCountry(itinerary.getCountry());
        descriptor.setStartDate(itinerary.getStartDate());
        descriptor.setDuration(itinerary.getDuration());
        descriptor.setPeople(itinerary.getPeople());
        descriptor.setBudget(itinerary.getBudget());
    }

    /**
     * Sets the {@code Name} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withName(String name) {
        descriptor.setName(new Description(name));
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
     * Sets the {@code Duration} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new ItineraryDuration(duration));
        return this;
    }

    /**
     * Sets the {@code People} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withPeople(String people) {
        descriptor.setPeople(new People(people));
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withBudget(String budget) {
        descriptor.setBudget(new Budget(budget));
        return this;
    }

    public EditItineraryDescriptor build() {
        return descriptor;
    }
}

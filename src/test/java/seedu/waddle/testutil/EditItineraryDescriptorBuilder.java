package seedu.waddle.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.Name;

/**
 * A utility class to help with building EditPersonDescriptor objects.
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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
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
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withCountry(String country) {
        descriptor.setCountry(new Country(country));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new Date(startDate));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new Date(endDate));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditItineraryDescriptorBuilder withPeople(String people) {
        descriptor.setPeople(people);
        return this;
    }

    public EditItineraryDescriptor build() {
        return descriptor;
    }
}

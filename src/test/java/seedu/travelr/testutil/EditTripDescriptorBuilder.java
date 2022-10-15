package seedu.travelr.testutil;

import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Description;
import seedu.travelr.model.trip.Title;
import seedu.travelr.model.trip.Trip;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;

/**
 * A utility class to help with building EditTripDescriptor objects.
 */
public class EditTripDescriptorBuilder {

    private EditTripDescriptor descriptor;

    public EditTripDescriptorBuilder() {
        descriptor = new EditTripDescriptor();
    }

    public EditTripDescriptorBuilder(EditTripDescriptor descriptor) {
        this.descriptor = new EditTripDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTripDescriptor} with fields containing {@code trip}'s details
     */
    public EditTripDescriptorBuilder(Trip trip) {
        descriptor = new EditTripDescriptor();
        descriptor.setTitle(trip.getTitle());
        descriptor.setDescription(trip.getDescription());
        descriptor.setEvents(trip.getEvents());
    }

    /**
     * Sets the {@code Title} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code events} into a {@code Set<Event>} and set it to the {@code EditTripDescriptor}
     * that we are building.
     */
    public EditTripDescriptorBuilder withEvents(String... events) {
        Set<Event> eventSet = Stream.of(events).map(title -> new Event(new Title(title), new Description(EVENT_DESCRIPTION_PLACEHOLDER))).collect(Collectors.toSet());
        descriptor.setEvents(eventSet);
        return this;
    }

    public EditTripDescriptor build() {
        return descriptor;
    }
}

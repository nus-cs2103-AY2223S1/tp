package seedu.address.testutil;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;


/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setEventTitle(event.getEventTitle());
        descriptor.setDate(event.getStartDate());
        descriptor.setTime(event.getStartTime());
        descriptor.setPurpose(event.getPurpose());
    }

    /**
     * Sets the {@code EventTitle} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventTitle(String eventTitle) {
        descriptor.setEventTitle(new EventTitle(eventTitle));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withStartTime(String startTime) {
        descriptor.setTime(new StartTime(startTime));
        return this;
    }

    /**
     * Sets the {@code Purpose} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withPurpose(String purpose) {
        descriptor.setPurpose(new Purpose(purpose));
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}

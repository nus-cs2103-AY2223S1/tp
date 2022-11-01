package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {
    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setReason(appointment.getReason());
        descriptor.setDateTime(appointment.getDateTime());
        descriptor.setTimePeriod(appointment.getTimePeriod());
        descriptor.setTags(appointment.getTags());
    }

    /**
     * Sets the reason of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withReason(String reason) {
        descriptor.setReason(reason);
        return this;
    }

    /**
     * Sets the dateTime of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDateTime(LocalDateTime dateTime) {
        descriptor.setDateTime(dateTime);
        return this;
    }

    /**
     * Sets the timePeriod of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withTimePeriod(List<Integer> timePeriod) {
        descriptor.setTimePeriod(timePeriod);
        return this;
    }

    /**
     * Sets the tags of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withTags(String ... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::convertToTag).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}

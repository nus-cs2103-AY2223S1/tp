package seedu.address.testutil;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setLocation(meeting.getLocation());
        descriptor.setDescription(meeting.getDescription());
        descriptor.setDate(meeting.getDateAndTime());
        descriptor.setPeople(meeting.getArrayListPersonToMeet());
    }

    /**
     * Sets the {@code Description} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDescription(String desc) {
        descriptor.setDescription(desc);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(location);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDate(String date) {
        descriptor.setDate(date);
        return this;
    }


    public EditMeetingDescriptor build() {
        return descriptor;
    }
}

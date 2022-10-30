package seedu.address.testutil;

import seedu.address.logic.commands.reminder.EditReminderCommand.EditReminderDescriptor;
import seedu.address.model.datetime.Datetime;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.reminder.ReminderPriority;

/**
 * A utility class to help with building EditReminderDescriptor objects.
 */
public class EditReminderDescriptorBuilder {
    private EditReminderDescriptor descriptor;

    public EditReminderDescriptorBuilder() {
        descriptor = new EditReminderDescriptor();
    }

    public EditReminderDescriptorBuilder(EditReminderDescriptor descriptor) {
        this.descriptor = new EditReminderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditReminderDescriptor} with fields containing {@code reminder}'s details
     */
    public EditReminderDescriptorBuilder(Reminder reminder) {
        descriptor = new EditReminderDescriptor();
        descriptor.setName(reminder.getName());
        descriptor.setDeadline(reminder.getDeadline());
        descriptor.setPriority(reminder.getPriority());
        descriptor.setDescription(reminder.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withName(String name) {
        descriptor.setName(new ReminderName(name));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(Datetime.fromFormattedString(deadline));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new ReminderPriority(priority));
        return this;
    }

    /**
     * Sets the {@code description} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new ReminderDescription(description));
        return this;
    }

    public EditReminderDescriptor build() {
        return descriptor;
    }
}

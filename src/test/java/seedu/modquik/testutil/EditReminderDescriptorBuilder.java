package seedu.modquik.testutil;

import seedu.modquik.logic.commands.reminder.EditReminderCommand.EditReminderDescriptor;
import seedu.modquik.model.datetime.Datetime;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.reminder.ReminderDescription;
import seedu.modquik.model.reminder.ReminderName;
import seedu.modquik.model.reminder.ReminderPriority;

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

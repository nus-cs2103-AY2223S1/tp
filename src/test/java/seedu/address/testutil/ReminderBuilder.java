package seedu.address.testutil;

import seedu.address.model.datetime.Datetime;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.reminder.ReminderPriority;

/**
 * A utility class to help with building Reminder objects.
 */
public class ReminderBuilder {

    public static final String DEFAULT_NAME = "Mark Midterms";
    public static final String DEFAULT_DEADLINE = "2022-10-10 15:00";
    public static final String DEFAULT_PRIORITY = "HIGH";
    public static final String DEFAULT_DESCRIPTION = "300 papers to go";

    private ReminderName reminderName;
    private Datetime reminderDeadline;
    private ReminderPriority reminderPriority;
    private ReminderDescription reminderDescription;

    /**
     * Creates a {@code ReminderBuilder} with the default details.
     */
    public ReminderBuilder() {
        reminderName = new ReminderName(DEFAULT_NAME);
        reminderPriority = new ReminderPriority(DEFAULT_PRIORITY);
        reminderDescription = new ReminderDescription(DEFAULT_DESCRIPTION);
        reminderDeadline = Datetime.fromFormattedString(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the ReminderBuilder with the data of {@code reminderToCopy}.
     */
    public ReminderBuilder(Reminder reminderToCopy) {
        reminderName = reminderToCopy.getName();
        reminderDeadline = reminderToCopy.getDeadline();
        reminderPriority = reminderToCopy.getPriority();
        reminderDescription = reminderToCopy.getDescription();
    }

    /**
     * Sets the {@code ReminderName} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withName(String name) {
        this.reminderName = new ReminderName(name);
        return this;
    }

    /**
     * Sets the {@code Datetime} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withDeadline(String deadline) {
        this.reminderDeadline = Datetime.fromFormattedString(deadline);
        return this;
    }

    /**
     * Sets the {@code ReminderPriority} of the {@code Priority} that we are building.
     */
    public ReminderBuilder withPriority(String priority) {
        this.reminderPriority = new ReminderPriority(priority);
        return this;
    }

    /**
     * Sets the {@code ReminderDescription} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withDescription(String description) {
        this.reminderDescription = new ReminderDescription(description);
        return this;
    }

    public Reminder build() {
        return new Reminder(reminderName, reminderDeadline, reminderPriority, reminderDescription);
    }
}

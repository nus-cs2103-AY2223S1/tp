package seedu.address.testutil;

import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDeadline;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;

/**
 * A utility class to help with building Reminder objects.
 */
public class ReminderBuilder {

    public static final String DEFAULT_NAME = "Mark Midterms";
    public static final String DEFAULT_DEADLINE = "15:00";
    public static final String DEFAULT_DESCRIPTION = "300 papers to go";

    private ReminderName reminderName;
    private ReminderDeadline reminderDeadline;
    private ReminderDescription reminderDescription;

    /**
     * Creates a {@code TutorialBuilder} with the default details.
     */
    public ReminderBuilder() {
        reminderName = new ReminderName(DEFAULT_NAME);
        reminderDeadline = new ReminderDeadline(DEFAULT_DEADLINE);
        reminderDescription = new ReminderDescription(DEFAULT_DESCRIPTION);

    }

    /**
     * Initializes the ReminderBuilder with the data of {@code reminderToCopy}.
     */
    public ReminderBuilder(Reminder reminderToCopy) {
        reminderName = reminderToCopy.getName();
        reminderDeadline = reminderToCopy.getDeadline();
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
     * Sets the {@code ReminderDeadline} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withDeadline(String deadline) {
        this.reminderDeadline = new ReminderDeadline(deadline);
        return this;
    }

    /**
     * Sets the {@code TutorialVenue} of the {@code Tutorial} that we are building.
     */
    public ReminderBuilder withDescription(String description) {
        this.reminderDescription = new ReminderDescription(description);
        return this;
    }

    public Reminder build() {
        return new Reminder(reminderName, reminderDeadline, reminderDescription);
    }
}

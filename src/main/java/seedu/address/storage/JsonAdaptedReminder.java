package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.datetime.Datetime;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.reminder.ReminderPriority;
import seedu.address.model.reminder.ReminderStatus;
import seedu.address.storage.datetime.JsonAdaptedDatetime;

/**
 * Jackson-friendly version of {@link Reminder}.
 */

class JsonAdaptedReminder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder's %s field is missing!";
    private final String name;
    private final String description;
    private final JsonAdaptedDatetime deadline;
    private final String priority;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given reminder details.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("name") String name,
                               @JsonProperty("deadline") JsonAdaptedDatetime deadline,
                               @JsonProperty("priority") String priority,
                               @JsonProperty("description") String description,
                               @JsonProperty("status") boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        name = source.getName().fullName;
        deadline = new JsonAdaptedDatetime(source.getDeadline());
        priority = source.getPriority().priority;
        description = source.getDescription().description;
        isDone = source.getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted reminder object into the model's {@code Reminder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reminder.
     */
    public Reminder toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ReminderName.class.getSimpleName()));
        }
        if (!ReminderName.isValidName(name)) {
            throw new IllegalValueException(ReminderName.MESSAGE_CONSTRAINTS);
        }
        final ReminderName modelName = new ReminderName(name);

        final Datetime modelDeadline = deadline.toModelType();

        final ReminderDescription modelDescription = new ReminderDescription(description);

        if (!ReminderPriority.isValidPriority(priority)) {
            throw new IllegalValueException(ReminderPriority.MESSAGE_CONSTRAINTS);
        }
        final ReminderPriority modelPriority = new ReminderPriority(priority);

        final ReminderStatus modelStatus = new ReminderStatus(isDone);

        return new Reminder(modelName, modelDeadline, modelPriority, modelDescription, modelStatus);

    }
}

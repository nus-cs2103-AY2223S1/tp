package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.reminder.ReadOnlyReminderList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;

/**
 * An Immutable ReminderList that is serializable to JSON format.
 */
@JsonRootName(value = "reminders")
class JsonSerializableReminderList {

    public static final String MESSAGE_DUPLICATE_REMINDER = "Reminder list contains duplicate reminder(s).";

    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableReminderList} with the given reminders.
     */
    @JsonCreator
    public JsonSerializableReminderList(
            @JsonProperty("reminders") List<JsonAdaptedReminder> reminders) {
        this.reminders.addAll(reminders);
    }

    /**
     * Converts a given {@code ReadOnlyReminderList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableReminderList}.
     */
    public JsonSerializableReminderList(ReadOnlyReminderList source) {
        reminders.addAll(source.getAllReminders().stream().map(JsonAdaptedReminder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this reminder list into the model's {@code ReminderList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ReminderList toModelType() throws IllegalValueException {
        ReminderList reminderList = new ReminderList();
        for (JsonAdaptedReminder jsonAdaptedReminder : reminders) {
            Reminder reminder = jsonAdaptedReminder.toModelType();
            if (reminderList.contains(reminder)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REMINDER);
            }
            reminderList.add(reminder);
        }
        return reminderList;
    }

}


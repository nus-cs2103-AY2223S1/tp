package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedReminder {

    private final String reminderTask;
    private final LocalDate reminderDate;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedReminder(String reminderTask, String reminderDate) {
        this.reminderTask = reminderTask;
        LocalDate tempDate;
        try {
            tempDate = LocalDate.parse(reminderDate, DateTimeFormatter.ofPattern("d-MM-yyyy"));
        } catch (DateTimeParseException e) {
            try {
                tempDate = LocalDate.parse(reminderDate, DateTimeFormatter.ofPattern("yyyy-MM-d"));
            } catch (DateTimeParseException e1) {
                throw new RuntimeException("Incorrect Date format given to constructor");
            }
        }
        this.reminderDate = tempDate;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        System.out.println(source);
        this.reminderTask = source.task;
        this.reminderDate = source.date;
    }

    @JsonValue
    public String getReminderTask() {
        return reminderTask;
    }

    @JsonValue
    public String getReminderDate() {
        return reminderDate.format(DateTimeFormatter.ofPattern("d-MM-yyyy"));
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Reminder toModelType() throws IllegalValueException {
        return new Reminder(reminderTask, reminderDate.format(DateTimeFormatter.ofPattern("d-MM-yyyy")));
    }

}


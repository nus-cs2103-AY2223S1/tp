package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javafx.util.Pair;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Reminder;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedReminder {

    private final Pair<String, LocalDate> stringLocalDatePair;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("key") String reminderTask, @JsonProperty("value") String reminderDate) {
        LocalDate tempDate;
        try {
            tempDate = LocalDate.parse(reminderDate, DateTimeFormatter.ofPattern("d-MM-yyyy"));
        } catch (DateTimeParseException e) {
            tempDate = LocalDate.parse(reminderDate, DateTimeFormatter.ofPattern("yyyy-MM-d"));
        }
        this.stringLocalDatePair = new Pair<>(reminderTask, tempDate);
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        this.stringLocalDatePair = new Pair<>(source.task, source.date);
    }

    @JsonValue
    public Pair<String, LocalDate> getReminderPair() {
        return stringLocalDatePair;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Reminder toModelType() throws IllegalValueException {
        return new Reminder(stringLocalDatePair.getKey(),
                stringLocalDatePair.getValue().format(DateTimeFormatter.ofPattern("d-MM-yyyy")));
    }

}


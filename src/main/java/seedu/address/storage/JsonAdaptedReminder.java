package seedu.address.storage;

import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javafx.util.Pair;
import seedu.address.model.person.Reminder;

/**
 * Jackson-friendly version of {@link Reminder}.
 */
class JsonAdaptedReminder {

    private final Pair<String, String> reminderPair;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given {@code reminderTask} and {@code reminderDate}.
     * {@code reminderDate} should be in the format of 'dd-MM-yyyy'.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("key") String reminderTask, @JsonProperty("value") String reminderDate) {
        this.reminderPair = new Pair<>(reminderTask, reminderDate);
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        this.reminderPair = new Pair<>(source.task, source.date.format(
                DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)));
    }

    @JsonValue
    public Pair<String, String> getReminderPair() {
        return reminderPair;
    }

    /**
     * Converts this Jackson-friendly adapted reminder object into the model's {@code Reminder} object.
     */
    public Reminder toModelType() {
        return new Reminder(reminderPair.getKey(),
                reminderPair.getValue());
    }

}


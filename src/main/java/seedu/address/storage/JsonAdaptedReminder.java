package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.DateTime;
import seedu.address.model.reminder.Reminder;

/**
 * Jackson-friendly version of {@link Reminder}.
 */
public class JsonAdaptedReminder {

    private final String description;
    private final String dateTime;
    private final String name;
    private final String phone;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given description and date time string.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("description") String description,
                               @JsonProperty("dateTime") String dateTime,
                               @JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.description = description;
        this.dateTime = dateTime;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        description = source.description;
        dateTime = source.dateTime.getDateTimeString();
        name = source.getName();
        phone = source.getPhone();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Reminder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Reminder toModelType() throws IllegalValueException {
        if (!Reminder.isValidReminder(description, dateTime, name, phone)) {
            throw new IllegalValueException(Reminder.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelDateTime = new DateTime(dateTime);
        final Name modelName = new Name(name);
        final Phone modelPhone = new Phone(phone);
        return new Reminder(description, modelDateTime, modelName, modelPhone);
    }
}

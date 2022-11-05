package seedu.modquik.logic.parser.reminder;

import java.util.Objects;

import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.reminder.ReminderDescription;
import seedu.modquik.model.reminder.ReminderName;
import seedu.modquik.model.reminder.ReminderPriority;

/**
 * Contains utility methods used for parsing Reminder and related subclasses.
 */
public class ReminderParserUtil {
    /**
     * Parses a {@code String name} into a {@code ReminderName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ReminderName} is invalid.
     */
    public static ReminderName parseReminderName(String name) throws ParseException {
        Objects.requireNonNull(name);
        String trimmedName = name.trim();
        if (!ReminderName.isValidName(trimmedName)) {
            throw new ParseException(ReminderName.MESSAGE_CONSTRAINTS);
        }
        return new ReminderName(trimmedName);
    }

    /**
     * Parses a {@code String description} into a {@code ReminderDescription}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ReminderDescription parseReminderDescription(String description) {
        String trimmedDescription = description.trim();
        return new ReminderDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String priority} into a {@code ReminderPriority}.
     * Leading and trailing whitespaces will be trimmed. Input will also be converted to upper case.
     */
    public static ReminderPriority parseReminderPriority(String priority) throws ParseException {
        String trimmedPriority = priority.trim().toUpperCase();
        if (!ReminderPriority.isValidPriority(trimmedPriority)) {
            throw new ParseException(ReminderPriority.MESSAGE_CONSTRAINTS);
        }
        return new ReminderPriority(trimmedPriority);
    }

}

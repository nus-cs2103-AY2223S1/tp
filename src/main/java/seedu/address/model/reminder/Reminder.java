package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Represents reminders that the user can have.
 */
public class Reminder {
    public static final String MESSAGE_CONSTRAINTS = "";
    public final String description;
    public final DateTime dateTime;
    private Optional<Name> name;
    private Optional<Phone> phone;

    /**
     * Constructs a {@code Reminder}
     *
     * @param description The description of the reminder in {@code String} format.
     * @param dateTime The date and time associated with the reminder in {@code String} format.
     */
    public Reminder(String description, DateTime dateTime, Name name, Phone phone) {
        requireNonNull(description);
        requireNonNull(dateTime);
        this.description = description;
        this.dateTime = dateTime;
        this.name = Optional.of(name);
        this.phone = Optional.of(phone);
    }

    public String getName() {
        return name.map(name -> name.fullName).orElse("");
    }

    public String getPhone() {
        return phone.map(phone -> phone.value).orElse("");
    }

    public void setNameAndPhone(Name name, Phone phone) {
        this.name = Optional.of(name);
        this.phone = Optional.of(phone);
    }

    /**
     * Returns true if parameters constitutes a valid reminder.
     */
    public static boolean isValidReminder(String description, String dateTimeString, String name, String phone) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reminder // instanceof handles nulls
                && description.equals(((Reminder) other).description) // state check
                && dateTime.equals(((Reminder) other).dateTime)
                && name.equals(((Reminder) other).name)
                && phone.equals(((Reminder) other).phone));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, dateTime, name, phone);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "";
    }
}

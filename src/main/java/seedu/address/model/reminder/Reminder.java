package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Represents reminders that the user can have.
 */
public class Reminder implements Comparable<Reminder> {
    public static final String MESSAGE_CONSTRAINTS = "";
    private final String description;
    private final DateTime dateTime;
    private Name name;
    private Phone phone;

    /**
     * Constructs a {@code Reminder}
     *
     * @param description The description of the reminder in {@code String} format.
     * @param dateTime The date and time associated with the reminder in {@code String} format.
     */
    public Reminder(String description, DateTime dateTime, Name name, Phone phone) {
        requireAllNonNull(description, dateTime, name, phone);
        this.description = description;
        this.dateTime = dateTime;
        this.name = name;
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getDateTimeString() {
        return dateTime.getDateTimeString();
    }

    public String getNameString() {
        return name.fullName;
    }

    public String getPhoneString() {
        return phone.value;
    }

    /**
     * Returns true if current name and phone matches the name and phone provided.
     */
    public boolean matchesNameAndPhone(Name name, Phone phone) {
        if (name == null || phone == null) {
            return false;
        }
        return getNameString().equals(name.fullName) && getPhoneString().equals(phone.value);
    }

    public void setNameAndPhone(Name name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Returns true if parameters constitutes a valid reminder.
     */
    public static boolean isValidReminder(String description, String dateTimeString, String name, String phone) {
        requireAllNonNull(description, dateTimeString, name, phone);
        if (description.isEmpty() || !DateTime.isValidDateTimeString(dateTimeString, DateTime.DATE_TIME_FORMATTER)
                || !Name.isValidName(name) || !Phone.isValidPhone(phone)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Reminder other) {
        int value = this.dateTime.compareTo(other.dateTime);
        if (value == 0) {
            return this.description.compareTo(other.description);
        }
        return value;
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
        return "Description: " + description + "\nDate and Time: " + dateTime
                + "\nName: " + name.toString() + "\nPhone: " + phone.toString();
    }
}

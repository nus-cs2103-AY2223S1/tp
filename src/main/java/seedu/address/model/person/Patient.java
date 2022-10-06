package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.person.Date;
import seedu.address.model.person.Time;
/**
 * Represents a patient that requires home-visit in Healthcare Xpress book.
 */
public class Patient extends Person {

    private final Date date;
    private final Time time;

    public Patient(Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags, Date date, Time time) {
        super(name, gender, phone, email, address, tags);
        this.date = date;
        this.time = time;
    }

    public String getCategory() {
        return "P";
    }

    public String getDate() {
        return date.toString();
    }

    public String getTime() {
        return time.toString();
    }

    /**
     * Returns true if both patients have the same name and gender.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (!(otherPerson instanceof Patient)) {
            return false;
        } else {
            return super.isSamePerson(otherPerson);
        }
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Patient)) {
            return false;
        } else {
            Patient otherPatient = (Patient) other;
            return super.equals(other) && otherPatient.getDate().equals(getDate())
                    && otherPatient.getTime().equals(getTime());
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode() + Objects.hash(date, time);
    }

    @Override
    public String toString() {
        return "P " + super.toString() + date + time;
    }

}

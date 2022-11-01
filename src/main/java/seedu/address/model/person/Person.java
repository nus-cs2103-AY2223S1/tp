package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Callback<Appointment, Observable[]> extractor = Appointment::getProperties;
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList(extractor);
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, List<Appointment> listOfAppointments,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, listOfAppointments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.appointments.addAll(listOfAppointments);
        for (Appointment appointment : listOfAppointments) {
            if (appointment.getPatient() != this) {
                appointment.setPatient(this);
            }
        }
        this.appointments.addListener((ListChangeListener<Appointment>) c -> {
            while (c.next()) {
                if (c.wasUpdated()) {
                    appointments.sort(Comparator.comparing(Appointment::getDateTime));
                }
                if (c.wasAdded()) {
                    appointments.sort(Comparator.comparing(Appointment::getDateTime));
                }
            }
        });
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone());
    }

    private String getAppointmentsString() {
        String str = "Currently has %s upcoming ";
        int num = 0;
        for (Appointment appts : appointments) {
            if (!appts.isMarked()) {
                num += 1;
            }
        }
        str = String.format(str, num);
        str += num == 1 ? "appointment" : "appointments";
        return str;
    }

    public String getParticulars() {
        String str = phone.value;
        if (!address.value.isEmpty()) {
            str += "\n" + address.value;
        }
        if (!email.value.isEmpty()) {
            str += "\n" + email.value;
        }
        str += "\n" + getAppointmentsString();
        return str;
    }

    public Observable getApptsProperty() {
        return appointments;
    }

    /**
     * Returns -1 if this person appears before the other person, and
     * returns 0 if this person has the same order as the other person, and
     * returns 1 if this person appears after the other person.
     *
     * @param person The other person to compare with.
     */
    public int compareTo(Person person) {
        return this.getName().compareTo(person.getName()) < 0
                ? -1
                : this.getName().compareTo(person.getName()) > 0
                ? 1
                : this.getPhone().compareTo(person.getPhone());
    }

    /**
     * Returns the group number where this person belongs to, which is determined
     * by its tags.
     *
     */
    public int getGroupNumber() {
        Set<Tag> tags = this.getTags();
        if (tags.isEmpty()) {
            return 0;
        }
        int value = 0;
        for (Tag tag: tags) {
            value += tag.ordinal();
        }
        if (tags.size() == 1) {
            return value + 1;
        }
        if (tags.size() == 2) {
            return value + 3;
        }
        return value + 4;
    }

    /**
     * Returns 10 * group difference -1 if this person appears before the other person, and
     * returns 10 * group difference if this person has the same order as the other person, and
     * returns 10 * group difference + 1 if this person appears after the other person. This method
     * makes sure that people with the same tag group are grouped together.
     *
     * @param person The other person to compare with.
     */
    public int groupCompareTo(Person person) {
        int tagWeight = 10;
        int nameWeight = 1;
        return tagWeight * (this.getGroupNumber() - person.getGroupNumber())
            + nameWeight * this.compareTo(person);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getAppointments().equals(getAppointments())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Appointments: ")
                .append(getAppointments());
        return builder.toString();
    }
}

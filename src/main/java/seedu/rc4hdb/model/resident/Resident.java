package seedu.rc4hdb.model.resident;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.resident.fields.Tag;
import seedu.rc4hdb.model.venues.booking.fields.BookingField;

/**
 * Represents a resident in the resident book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Resident implements BookingField {

    public static final String IDENTIFIER = "Resident";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Room room;
    private final Gender gender;
    private final House house;
    private final MatricNumber matricNumber;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Resident(Name name, Phone phone, Email email, Room room, Gender gender,
                    House house, MatricNumber matricNumber, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, room, gender, house, matricNumber, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.room = room;
        this.gender = gender;
        this.house = house;
        this.matricNumber = matricNumber;
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

    public Room getRoom() {
        return room;
    }

    public Gender getGender() {
        return gender;
    }

    public House getHouse() {
        return house;
    }

    public MatricNumber getMatricNumber() {
        return matricNumber;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Residents have the same name.
     * This defines a weaker notion of equality between two Residents.
     */
    public boolean isSameResident(Resident otherResident) {
        if (otherResident == this) {
            return true;
        }

        return otherResident != null
                && otherResident.getName().equals(getName());
    }

    /**
     * Returns true if both Residents have the same identity and data fields.
     * This defines a stronger notion of equality between two Residents.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Resident)) {
            return false;
        }

        Resident otherResident = (Resident) other;
        return otherResident.getName().equals(getName())
                && otherResident.getPhone().equals(getPhone())
                && otherResident.getEmail().equals(getEmail())
                && otherResident.getRoom().equals(getRoom())
                && otherResident.getGender().equals(getGender())
                && otherResident.getHouse().equals(getHouse())
                && otherResident.getMatricNumber().equals(getMatricNumber())
                && otherResident.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, room, gender, house, matricNumber, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Room: ")
                .append(getRoom())
                .append("; Gender: ")
                .append(getGender())
                .append("; House: ")
                .append(getHouse())
                .append("; Matric Number: ")
                .append(getMatricNumber());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            for (Tag t : tags) {
                builder.append(t + ", ");
            }
            builder.delete(builder.length() - 2, builder.length());
        }
        return builder.toString();
    }

}

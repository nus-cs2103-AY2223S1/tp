package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.record.Record;
import seedu.address.model.record.RecordList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Default Fields
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_BIRTHDATE = "06-06-1966";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    // Identity fields
    private final Name name;
    private final Birthdate birthdate;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final RecordList records;
    private final Appointment appointment;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Birthdate birthdate, Phone phone, Email email, Address address, Set<Tag> tags,
                  RecordList records, Appointment appointment) {
        requireAllNonNull(name, birthdate, phone, email, address, tags);
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.records = records;
        this.appointment = appointment;
    }

    public Name getName() {
        return name;
    }

    public Birthdate getBirthdate() {
        return birthdate;
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

    public RecordList getRecordList() {
        return records;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name and address.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        boolean isSameName = otherPerson != null
                && otherPerson.getName().equals(getName());
        boolean isSameAddress = otherPerson != null
                && otherPerson.getAddress().equals(getAddress());

        return isSameName && isSameAddress;
    }

    /**
     * Returns true if both persons have the same name in lower case.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean hasSimilarName(Person otherPerson) {
        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns the age of a Person.
     *
     * @return the age of a Person.
     */
    public int getAge() {
        return this.birthdate.calculateAge();
    }

    /**
     * Returns the String representation of a Person's birthdate, when displayed on the GUI.
     *
     * @return the String representation of a Person's birthdate, when displayed on the GUI.
     */
    public String displayBirthdate() {
        return this.birthdate.toDisplayFormat();
    }

    //======= Record List ========================================

    /**
     * Returns true if a record with the same identity as {@code record} exists in the record list.
     */
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return records.contains(record);
    }

    /**
     * Adds a record to the record list.
     * The record must not already exist in the record list.
     */
    public void addRecord(Record r) {
        records.add(r);
    }

    /**
     * Deletes a record from the record list.
     * The record must already exist in the record list.
     */
    public void deleteRecord(Record record) {
        records.delete(record);
    }

    /**
     * Sets a record in the record list to the new edited record.
     */
    public void setRecord(Record target, Record editedRecord) {
        records.set(target, editedRecord);
    }

    /**
     * Replaces the contents of the record list with {@code records}.
     * {@code records} must not contain duplicate persons.
     */
    public void setRecords(RecordList records) {
        this.records.setRecordList(records);
    }

    /**
     * Clears the contents of the record list.
     */
    public void clearRecords() {
        records.clearRecords();
    }

    /**
     * Retrieve unmodifiable list of records
     */
    public ObservableList<Record> getUnmodifiableRecords() {
        return records.asUnmodifiableObservableList();
    }

    //======================================================================================

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
                && otherPerson.getBirthdate().equals(getBirthdate())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, birthdate, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Birthdate: ")
                .append(getBirthdate())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Record Count: ")
                .append(getRecordList());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

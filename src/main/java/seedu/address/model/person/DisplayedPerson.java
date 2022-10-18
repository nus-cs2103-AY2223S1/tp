package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.record.Record;

/**
 * Represents person whose records are being displayed.
 */
public class DisplayedPerson {
    /** Person to be displayed */
    private Person person;

    /**
     * Constructor for DisplayedPerson
     * @param person person to be displayed.
     */
    public DisplayedPerson(Person person) {
        requireAllNonNull(person);
        this.person = person;
    }

    /**
     * Set the person displayed to the input person parameter.
     *
     * @param person New person to be displayed.
     * @param addressBook Addressbook from which the new person originated from.
     */
    public void setDisplayedPerson(Person person, AddressBook addressBook) {
        requireAllNonNull(person, addressBook);

        if (!addressBook.hasPerson(person)) {
            throw new PersonNotFoundException();
        }

        setPerson(person);
    }

    private void setPerson(Person toSet) {
        this.person = toSet;
    }

    /**
     * Adds a record to the record list.
     * The record must not already exist in the record list.
     */
    public void addRecord(Record record) {
        person.addRecord(record);
    }

    /**
     * Returns true if a record with the same identity as {@code record} exists in the record list.
     */
    public boolean hasRecord(Record record) {
        return person.hasRecord(record);
    }

    /**
     * Deletes a record from the record list.
     * The record must already exist in the record list.
     */
    public void deleteRecord(Record record) {
        person.deleteRecord(record);
    }

    /**
     * Clears the contents of the record list.
     */
    public void clearRecords() {
        person.clearRecords();
    }

    /**
     * Sets the target record to the new edited record.
     */
    public void setRecord(Record target, Record editedRecord) {
        person.setRecord(target, editedRecord);
    }

    /**
     * Retrieve unmodifiable list of records
     */
    public ObservableList<Record> getUnmodifiableRecords() {
        return person.getUnmodifiableRecords();
    }
}

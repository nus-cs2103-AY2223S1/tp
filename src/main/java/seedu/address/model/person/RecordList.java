package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a record list in the address book.
 */
public class RecordList {
    /* Record List variables */
    // count of the record list should be accessed through the ArrayList#size() method.
    private final ObservableList<Record> recordList = FXCollections.observableArrayList();
    private final ObservableList<Record> internalUnmodifiableRecordList =
            FXCollections.unmodifiableObservableList(recordList);

    /**
     * Adds a record to the RecordList.
     *
     * @param toAdd Record to add.
     */
    public void add(Record toAdd) {
        requireNonNull(toAdd);
        recordList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent record as the given argument.
     */
    public boolean contains(Record toCheck) {
        requireNonNull(toCheck);
        return recordList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Getter for list of records.
     *
     * @return List of records.
     */
    public ObservableList<Record> asUnmodifiableObservableList() {
        return internalUnmodifiableRecordList;
    }

    public void setRecordList(RecordList replacement) {
        requireNonNull(replacement);
        recordList.setAll(replacement.recordList);
    }

    /**
     * Getter for size of list.
     *
     * @return Size of list.
     */
    public int getRecordListCount() {
        return this.recordList.size();
    }

    /**
     * Clears the record list.
     */
    public void clearRecords() {
        this.recordList.clear();
    }

    @Override
    public String toString() {
        return "Number of Records: " + recordList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordList // instanceof handles nulls
                && recordList.equals(((RecordList) other).recordList)); // state check
    }

    public void delete(Record record) {
        recordList.remove(record);
    }

    @Override
    public int hashCode() {
        return recordList.hashCode();
    }
}

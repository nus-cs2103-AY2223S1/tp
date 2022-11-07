package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.exceptions.DuplicateRecordException;
import seedu.address.model.person.exceptions.RecordNotFoundException;

/**
 * Represents a record list in the address book.
 */
public class RecordList {
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    /* Record List variables */
    // count of the record list should be accessed through the ArrayList#size() method.
    private final ObservableList<Record> recordList = FXCollections.observableArrayList();
    private final ObservableList<Record> internalUnmodifiableRecordList =
            FXCollections.unmodifiableObservableList(recordList);

    /**
     * Adds a record to the RecordList.
     * The person must not already exist in the list.
     *
     * @param toAdd Record to add.
     */
    public void add(Record toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            logger.warning("Duplicate record detected.");
            throw new DuplicateRecordException();
        }
        recordList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent record as the given argument.
     */
    public boolean contains(Record toCheck) {
        requireNonNull(toCheck);
        return recordList.stream().anyMatch(toCheck::isSameRecord);
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

    public void set(Record target, Record editedRecord) {
        requireAllNonNull(target, editedRecord);

        int index = recordList.indexOf(target);
        if (index == -1) {
            logger.warning("Index input is negative and invalid.");
            throw new RecordNotFoundException();
        }

        if (!target.isSameRecord(editedRecord) && this.contains(editedRecord)) {
            logger.warning("Record is duplicate.");
            throw new DuplicateRecordException();
        }

        recordList.set(index, editedRecord);
    }

    @Override
    public String toString() {
        return Integer.toString(recordList.size());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordList // instanceof handles nulls
                && recordList.equals(((RecordList) other).recordList)); // state check
    }

    /**
     * Removes the equivalent record from the list.
     * The record must exist in the list.
     */
    public void delete(Record record) {
        requireNonNull(record);
        if (!recordList.remove(record)) {
            logger.warning("Record not found");
            throw new RecordNotFoundException();
        }
        recordList.remove(record);
    }

    @Override
    public int hashCode() {
        return recordList.hashCode();
    }
}

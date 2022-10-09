package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.util.Objects.requireNonNull;

/**
 * Represents a record list in the address book.
 */
public class RecordList {
    /* Record List variables */
    // todo: have the commands update the record list through an additional add record command.
    // count of the record list should be accessed through the ArrayList#size() method.
    private final ObservableList<Record> recordList = FXCollections.observableArrayList();
    private final ObservableList<Record> internalUnmodifiableRecordList =
            FXCollections.unmodifiableObservableList(recordList);

    /**
     * Getter for list of records.
     *
     * @return List of records.
     */
    public ObservableList<Record> getRecordList() {
        if (recordList.size() == 0) {
            recordList.add(new Record("10-08-2022 1200", "cold"));
            recordList.add(new Record("01-09-2022 1200", "flu"));
            recordList.add(new Record("05-10-2022 1200", "fever"));
        }
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

    // TODO IMPLEMENT THIS
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

    /**
     * Add a record to the RecordList
     *
     * @param record record
     */
    public void add(Record record) {
        recordList.add(record);
    }

    /**
     * Returns true if the list contains an equivalent record as the given argument.
     */
    public boolean contains(Record toCheck) {
        requireNonNull(toCheck);
        return recordList.stream().anyMatch(toCheck::equals);
    }

    @Override
    public int hashCode() {
        return recordList.hashCode();
    }
}

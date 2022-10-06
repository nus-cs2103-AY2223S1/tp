package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a record list in the address book.
 */
public class RecordList {
    /* Record List variables */
    // todo: have the commands update the record list through an additional add record command.
    // count of the record list should be accessed through the ArrayList#size() method.
    private final List<Record> recordList;

    /**
     * Constructs a {@code Record List}.
     */
    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }

    /**
     * Getter for list of records.
     *
     * @return List of records.
     */
    public List<Record> getRecordList() {
        return this.recordList;
    }

    /**
     * Getter for size of list.
     *
     * @return Size of list.
     */
    public int getRecordListCount() {
        return this.recordList.size();
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

    @Override
    public int hashCode() {
        return recordList.hashCode();
    }
}

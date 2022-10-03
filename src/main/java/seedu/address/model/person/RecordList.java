package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a record list in the address book.
 *
 */
public class RecordList {
    /* Record List variables */
    public final List<Record> recordList;
    public final int count = 0;

    /**
     * Constructs a {@code Record List}.
     */
    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }

    @Override
    public String toString() {
        return "Number of Records: " + String.valueOf(count);
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

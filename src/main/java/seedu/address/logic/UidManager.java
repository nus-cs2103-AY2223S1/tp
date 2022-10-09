package seedu.address.logic;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import seedu.address.model.person.Uid;

/**
 * The main UidManager of the app.
 */
public class UidManager {
    private Queue<Uid> records;
    private Queue<Uid> deletedUids;

    /**
     * Constructs a {@code UidManager}.
     */
    public UidManager() {
        this.records = new PriorityQueue<>(Collections.reverseOrder());
        this.deletedUids = new PriorityQueue<>();
    }

    /**
     * Adds a new Uid to the pool of Uids in use.
     * @param newEntry
     */
    public void addUid(Uid newEntry) {
        this.records.add(newEntry);
    }

    /**
     * Produces a new Uid that is ensured to not be duplicated in the system.
     */
    public Uid produceUid() {
        if (deletedUids.peek() != null) {
            return this.deletedUids.poll();
        }
        if (this.records.peek() != null) {
            return new Uid(this.records.peek().getId() + 1L);
        }
        return new Uid(1L);
    }
}

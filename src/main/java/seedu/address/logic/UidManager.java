package seedu.address.logic;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;

/**
 * The main UidManager of the app.
 */
public class UidManager {
    private Queue<Uid> records;

    /**
     * Constructs a {@code UidManager} that has the record of the uids used in the addressbook.
     */
    public UidManager(ObservableList<Person> currentPersonList) {
        this.records = new PriorityQueue<>(Collections.reverseOrder());
        initialize(currentPersonList);
    }

    /**
     * Constructs an empty record {@code UidManager}.
     */
    public UidManager() {
        this.records = new PriorityQueue<>(Collections.reverseOrder());
    }

    private void initialize(ObservableList<Person> currentPersonList) {
        for (Person person: currentPersonList) {
            addUid(person.getUid());
        }
    }

    /**
     * Adds a new Uid to the pool of Uids in use.
     *
     * @param newEntry
     */
    private void addUid(Uid newEntry) {
        this.records.add(newEntry);
    }

    /**
     * Produces a new Uid that is ensured to not be duplicated in the system.
     */
    public Uid produceUid() {
        Uid newUid = new Uid(1L);
        if (this.records.peek() != null) {
            newUid = new Uid(this.records.peek().getUid() + 1L);
        }
        this.addUid(newUid);
        return newUid;
    }
}

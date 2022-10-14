package seedu.address.model.message;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * A list of messages that do not allow null.
 */
public class MessageList implements Iterable<Message> {
    private final List<Message> internalList = new ArrayList<>();

    /**
     * Adds a message to the list.
     */
    public void add(Message toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the specified message from the list.
     */
    public void remove(Message toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    /**
     * Returns true if the list contains an equivalent message as the given argument.
     */
    public boolean contains(Message toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MessageList // instanceof handles nulls
                && internalList.equals(((MessageList) other).internalList));
    }

    /**
     * Returns the backing list as an unmodifiable {@code UnmodifiableList}.
     */
    public List<Message> asUnmodifiableList() {
        return Collections.unmodifiableList(internalList);
    }

    @Override
    public Iterator<Message> iterator() {
        return internalList.iterator();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
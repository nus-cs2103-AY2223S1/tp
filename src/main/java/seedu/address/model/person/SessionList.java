package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Person's session list in the address book.
 */
public class SessionList {

    public static final String MESSAGE_INVALID_SESSION_INDEX = "The session index provided is invalid!";

    public final List<Session> sessionList;

    /**
     * Constructs a {@code SessionList}.
     */
    public SessionList() {
        sessionList = new ArrayList<>();
    }

    public SessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    /**
     * Adds a session to the session list.
     *
     * @param session The session object to be added.
     */
    public void addSession(Session session) {
        sessionList.add(session);
    }

    /**
     * Returns true if a given {@code Index} is a valid index in the list.
     */
    public boolean isValidIndex(Index index) {
        return index.getZeroBased() < sessionList.size();
    }

    /**
     * Edits the session at the given index with the new given attendance.
     *
     * @param index of session to be edited
     * @param session that replaces the old attendance
     */
    public void editAtIndex(Index index, Session session) {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= sessionList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_SESSION_INDEX);
        }
        sessionList.set(indexToEdit, session);
    }
    /**
     * Returns a description of the next session in the session list.
     */
    public String shortDescription() {
        if (sessionList.isEmpty()) {
            return toString();
        }
        sessionList.sort(Session::compareTo);
        String shortDesc = "NEXT UP: \n"
                + sessionList.get(0).toString() + ".";
        return shortDesc;
    }

    /**
     * clears the session list.
     */
    public void clearList() {
        sessionList.clear();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Session: \n");
        if (sessionList.isEmpty()) {
            description.append("No sessions found!\n");
        }
        for (int i = 0; i < sessionList.size(); i++) {
            description.append(i + 1).append(". ").append(this.sessionList.get(i)).append("\n");
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SessionList
                && this.sessionList.equals(((SessionList) other).sessionList));
    }

    @Override
    public int hashCode() {
        return this.sessionList.hashCode();
    }
}

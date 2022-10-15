package seedu.address.model.person;

import seedu.address.commons.core.index.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's duration list in the address book.
 */
public class DurationList {

    public static final String MESSAGE_INVALID_DURATION_INDEX = "The duration index provided is invalid!";

    public final List<Duration> durationList;

    /**
     * Constructs a {@code DurationList}.
     */
    public DurationList() {
        durationList = new ArrayList<>();
    }

    public DurationList(List<Duration> durationList) {
        this.durationList = durationList;
    }

    /**
     * Adds a duration to the duration list.
     *
     * @param duration The duration object to be added.
     */
    public void addDuration(Duration duration) {
        durationList.add(duration);
    }

    /**
     * Returns true if a given {@code Index} is a valid index in the list.
     */
    public boolean isValidIndex(Index index) {
        return index.getZeroBased() < durationList.size();
    }

    /**
     * Edits the duration at the given index with the new given attendance.
     *
     * @param index of duration to be edited
     * @param duration that replaces the old attendance
     */
    public void editAtIndex(Index index, Duration duration) {
        int indexToEdit = index.getZeroBased();
        if (indexToEdit >= durationList.size()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_DURATION_INDEX);
        }
        durationList.set(indexToEdit, duration);
    }
    /**
     * Returns a description of the next duration in the duration list.
     */
    public String shortDescription() {
        if (durationList.isEmpty()) {
            return toString();
        }
        durationList.sort(Duration::compareTo);
        String shortDesc = "NEXT UP: \n"
                + durationList.get(0).toString() + ".";
        return shortDesc;
    }

    /**
     * clears the duration list.
     */
    public void clearList() {
        durationList.clear();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Duration: \n");
        if (durationList.isEmpty()) {
            description.append("No durations found!\n");
        }
        for (int i = 0; i < durationList.size(); i++) {
            description.append(i + 1).append(". ").append(this.durationList.get(i)).append("\n");
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DurationList
                && this.durationList.equals(((DurationList) other).durationList));
    }

    @Override
    public int hashCode() {
        return this.durationList.hashCode();
    }
}
